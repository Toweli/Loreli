package net.loreli.eventsystem;

import java.lang.reflect.Method;

public class TEventHandler<Arguments>
{
	private Object	m_oHandler;
	private Method	m_oMethod;
	
	private boolean m_bQueued;

	TEventHandler(Object oHandler, Method oMethod)
	{
		this(oHandler, oMethod, false);
	}
	
	TEventHandler(Object oHandler, Method oMethod, boolean bQueued)
	{
		m_oHandler = oHandler;
		m_oMethod = oMethod;
		m_bQueued = bQueued;
	}
	
	Object getHandler()
	{
		return m_oHandler;
	}
	
	Method getMethod()
	{
		return m_oMethod;
	}

	public void handleEvent(Object oSender, Arguments oArguments)
	{
		EventEmitter oEmitter = new EventEmitter(this, oSender, oArguments);
		if(m_bQueued)
		{
			EventManager.getInstance().putEventEmiter(oEmitter);
		}
		else
		{
			emit(oEmitter);
		}
	}
	
	// yes package private!
	void emit(EventEmitter oEmitter)
	{
		oEmitter.run();
	}
	
	@Override
	public boolean equals(Object oOther)
	{
		if(oOther instanceof TEventHandler<?>)
		{
			TEventHandler<?> oOtherHandler = (TEventHandler<?>) oOther;
			return oOtherHandler.m_oHandler.equals(m_oHandler) && oOtherHandler.m_oMethod.equals(m_oMethod);
		}
		else
		{
			return false;
		}
	}
}
