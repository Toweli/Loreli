package net.loreli.eventsystem;

import java.util.ArrayList;

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
		m_liEventHandler.add(new TEventHandler<Arguments>(oHandler, strMethodName));
	}
	
	public void removeHandler(TEventHandler<Arguments> oHandler)
	{
		m_liEventHandler.remove(oHandler);
	}
}
