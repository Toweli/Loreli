package net.loreli.eventsystem;

import java.util.ArrayList;

import net.loreli.logging.ProgramLogSingleton;

public class TEvent<Arguments >
{
	private ArrayList<TEventHandler<Arguments>> m_liEventHandler = new ArrayList<>();
	private Object m_oSender;
	
	public TEvent(Object oSender)
	{
		m_oSender = oSender;
	}
	
	public void raise(Arguments oArguments)
	{
		for(TEventHandler<Arguments> oEventHandler : m_liEventHandler)
		{
			oEventHandler.handleEvent(m_oSender, oArguments);
		}
	}
	
	public void addHandler(Object oHandler, String strMethodName)
	{
		TEventHandler<Arguments> oEventHandler = new TEventHandler<Arguments>(oHandler, strMethodName);
		if(!m_liEventHandler.contains(oEventHandler))
		{
			m_liEventHandler.add(oEventHandler);
		}
		else
		{
			ProgramLogSingleton.getInstance().warning("Handler already exists", 4);
		}
	}
	
	public void removeHandler(Object oHandler, String strMethodName)
	{
		TEventHandler<Arguments> oEventHandler = new TEventHandler<Arguments>(oHandler, strMethodName);
		if(m_liEventHandler.contains(oEventHandler))
		{
			m_liEventHandler.remove(oEventHandler);
		}
		else
		{
			ProgramLogSingleton.getInstance().warning("Handler doesn't exist", 4);
		}
	}
	
	public void removeAllHandlerOf(Object oHandler)
	{
		ArrayList<TEventHandler<Arguments>> liToRemove = new ArrayList<>();
		for(TEventHandler<Arguments> oEventHandler : m_liEventHandler)
		{
			if(oEventHandler.getHandler().equals(oHandler))
			{
				liToRemove.add(oEventHandler);
			}
		}
		m_liEventHandler.removeAll(liToRemove);
	}
}
