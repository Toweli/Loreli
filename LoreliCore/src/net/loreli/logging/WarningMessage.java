package net.loreli.logging;

public class WarningMessage extends AbstractLogMessage
{
	// TODO what would be an appropriate initial value?
	private int		m_iSeverity	= 0;
	private String	m_strText	= "";

	public WarningMessage()
	{
		super(Thread.currentThread(), Thread.currentThread().getStackTrace()[2]);
	}

	public WarningMessage(Thread oThread, StackTraceElement oCaller)
	{
		super(oThread, oCaller);
	}

	public void setSeverity(int iSeverity)
	{
		m_iSeverity = iSeverity;
	}

	public int getSeverity()
	{
		return m_iSeverity;
	}

	public WarningMessage setText(String strText)
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
