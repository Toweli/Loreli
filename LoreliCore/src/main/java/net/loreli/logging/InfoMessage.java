package net.loreli.logging;

public class InfoMessage extends AbstractLogMessage
{
	private String	m_strText;

	public InfoMessage()
	{
		super(Thread.currentThread(), Thread.currentThread().getStackTrace()[2]);
		m_strText = "";
	}

	public InfoMessage(Thread oThread, StackTraceElement oCaller)
	{
		super(oThread, oCaller);
		m_strText = "";
	}

	public InfoMessage setText(String strText)
	{
		m_strText = strText;
		return this;
	}

	public String getText()
	{
		return m_strText;
	}

	@Override
	public String getMessageType()
	{
		return getClass().getSimpleName();
	}
}
