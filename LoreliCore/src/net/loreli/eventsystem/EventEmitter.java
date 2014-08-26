package net.loreli.eventsystem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.loreli.logging.ProgramLogSingleton;

public class EventEmitter implements Runnable
{
	private TEventHandler<?>	m_oHandler;
	private Object				m_oSender;
	private Object				m_oArguments;

	EventEmitter(TEventHandler<?> oHandler, Object oSender, Object oArguments)
	{
		m_oHandler = oHandler;
		m_oSender = oSender;
		m_oArguments = oArguments;
	}

	@Override
	public void run()
	{
		try
		{
			Method oMethod = m_oHandler.getHandler().getClass()
					.getMethod(m_oHandler.getMethodName(), Object.class, m_oArguments.getClass());
			oMethod.invoke(m_oHandler.getHandler(), new Object[] { m_oSender, m_oArguments });
		}
		catch (IllegalAccessException e)
		{
			ProgramLogSingleton.getInstance().error("IllegalAccessException", "cann't invoke method");
		}
		catch (IllegalArgumentException e)
		{
			ProgramLogSingleton.getInstance().error("IllegalArgumentException", "wrong arguments for this method");
		}
		catch (InvocationTargetException e)
		{
			ProgramLogSingleton.getInstance().error("InvocationTargetException", "object doesn't have the method.");
		}
		catch (NoSuchMethodException e)
		{
			ProgramLogSingleton.getInstance().error("NoSuchMethodException",
					"Method doesn't exist. (Maybe it's not accessable(public).)");
		}
		catch (SecurityException e)
		{
			ProgramLogSingleton.getInstance().error("SecurityException", "SecurityException");
		}
	}
}
