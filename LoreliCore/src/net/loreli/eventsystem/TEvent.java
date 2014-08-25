package net.loreli.eventsystem;

import java.util.ArrayList;

public class TEvent<Arguments>
{
	private ArrayList<TIEventHandler<Arguments>> m_liEventHandler;
	private Object m_oSender;
	
	public TEvent(Object oSender)
	{
		m_oSender = oSender;
	}
	
	public void raise(Arguments oArguments)
	{
		for(TIEventHandler<Arguments> oEventHandler : m_liEventHandler)
		{
			oEventHandler.handleEvent(m_oSender , oArguments);
		}
	}
	
	public void addHandler(TIEventHandler<Arguments> oHandler)
	{
		m_liEventHandler.add(oHandler);
	}
	
	public void removeHandler(TIEventHandler<Arguments> oHandler)
	{
		m_liEventHandler.remove(oHandler);
	}
}
