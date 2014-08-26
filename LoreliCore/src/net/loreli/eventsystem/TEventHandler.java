package net.loreli.eventsystem;

public class TEventHandler<Arguments>
{
	private Object	m_oHandler;
	private String	m_strMethod;
	
	private boolean m_bQueued;

	TEventHandler(Object oHandler, String strMethod)
	{
		this(oHandler, strMethod, false);
	}
	
	TEventHandler(Object oHandler, String strMethod, boolean bQueued)
	{
		m_oHandler = oHandler;
		m_strMethod = strMethod;
		m_bQueued = bQueued;
	}
	
	Object getHandler()
	{
		return m_oHandler;
	}
	
	String getMethodName()
	{
		return m_strMethod;
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
			return oOtherHandler.m_oHandler.equals(m_oHandler) && oOtherHandler.m_strMethod.equals(m_strMethod);
		}
		else
		{
			return false;
		}
	}
}
