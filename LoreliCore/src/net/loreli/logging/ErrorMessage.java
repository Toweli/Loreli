package net.loreli.logging;

public class ErrorMessage extends AbstractLogMessage
{
	private String	m_strName;
	private String	m_strDescription;

	public ErrorMessage()
	{
		super(Thread.currentThread(), Thread.currentThread().getStackTrace()[2]);
		m_strName = "UNKNOWN_ERROR";
		m_strDescription = "";
	}

	public ErrorMessage(Thread oThread, StackTraceElement oCaller)
	{
		super(oThread, oCaller);
		m_strName = "UNKNOWN_ERROR";
		m_strDescription = "";
	}

	public ErrorMessage setName(String strName)
	{
		m_strName = strName;
		return this;
	}

	public String getName()
	{
		return m_strName;
	}

	public ErrorMessage setDescription(String strDescription)
	{
		m_strDescription = strDescription;
		return this;
	}

	public String getDescription()
	{
		return m_strDescription;
	}

	@Override
	public String getMessageType()
	{
		return getClass().getSimpleName();
	}
}
