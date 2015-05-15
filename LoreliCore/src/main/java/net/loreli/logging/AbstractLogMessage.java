package net.loreli.logging;

import java.util.Date;

public abstract class AbstractLogMessage
{
	protected Date				m_oDate;
	protected Thread			m_oThread;
	protected StackTraceElement	m_oCaller;

	protected AbstractLogMessage()
	{
		m_oDate = new Date(System.currentTimeMillis());
		m_oThread = Thread.currentThread();
		m_oCaller = m_oThread.getStackTrace()[2];
	}

	protected AbstractLogMessage(Thread oThread, StackTraceElement oCaller)
	{
		m_oDate = new Date(System.currentTimeMillis());
		m_oThread = oThread;
		m_oCaller = oCaller;
	}

	public Date getDate()
	{
		return m_oDate;
	}

	public Thread getThread()
	{
		return m_oThread;
	}

	public StackTraceElement getCaller()
	{
		return m_oCaller;
	}

	abstract public String getMessageType();
}
