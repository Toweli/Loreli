package net.loreli.eventsystem;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.loreli.logging.ProgramLogSingleton;

public class TEvent<Arguments>
{
	private List<TEventHandler<Arguments>>	m_liEventHandler	= new ArrayList<>();
	private Object							m_oSender;

	private Class<Arguments>				m_oArgumentsClass;

	public TEvent(Object oSender, Class<Arguments> oArgumentsClass)
	{
		m_oSender = oSender;
		m_oArgumentsClass = oArgumentsClass;
	}

	public void raise(Arguments oArguments)
	{
		for (TEventHandler<Arguments> oEventHandler : m_liEventHandler)
		{
			oEventHandler.handleEvent(m_oSender, oArguments);
		}
	}

	public void addHandler(Object oHandler, String strMethodName, boolean bQueued)
	{
		try
		{
			Method oMethod = oHandler.getClass().getMethod(strMethodName, Object.class, m_oArgumentsClass);

			TEventHandler<Arguments> oEventHandler = new TEventHandler<Arguments>(oHandler, oMethod, bQueued);
			if (!m_liEventHandler.contains(oEventHandler))
			{
				m_liEventHandler.add(oEventHandler);
			}
			else
			{
				ProgramLogSingleton.getInstance().warning("Handler already exists", 4);
			}

		}
		catch (NoSuchMethodException e)
		{
			ProgramLogSingleton.getInstance().error("SecurityException",
					"Can't add Handler. Method doesn't exist. (Maybe it's not accessable(public).)");
		}
		catch (SecurityException e)
		{
			ProgramLogSingleton.getInstance().error("NoSuchMethodException",
					"Can't add Handler. Method is not accessable.");
		}
	}

	public void addHandler(Object oHandler, String strMethodName)
	{
		addHandler(oHandler, strMethodName, false);
	}

	public void removeHandler(Object oHandler, String strMethodName)
	{

		Method oMethod;
		try
		{
			oMethod = oHandler.getClass().getMethod(strMethodName, Object.class, m_oArgumentsClass);
			TEventHandler<Arguments> oEventHandler = new TEventHandler<Arguments>(oHandler, oMethod);
			if (m_liEventHandler.contains(oEventHandler))
			{
				m_liEventHandler.remove(oEventHandler);
			}
			else
			{
				ProgramLogSingleton.getInstance().warning("Handler doesn't exist", 4);
			}
		}
		catch (NoSuchMethodException e)
		{
			ProgramLogSingleton.getInstance().error("SecurityException",
					"Can't remove Handler. Method doesn't exist. (Maybe it's not accessable(public).)");
		}
		catch (SecurityException e)
		{
			ProgramLogSingleton.getInstance().error("NoSuchMethodException",
					"Can't remove Handler. Method is not accessable.");
		}
	}

	public void removeAllHandlerOf(Object oHandler)
	{
		ArrayList<TEventHandler<Arguments>> liToRemove = new ArrayList<>();
		for (TEventHandler<Arguments> oEventHandler : m_liEventHandler)
		{
			if (oEventHandler.getHandler().equals(oHandler))
			{
				liToRemove.add(oEventHandler);
			}
		}
		m_liEventHandler.removeAll(liToRemove);
	}
}
