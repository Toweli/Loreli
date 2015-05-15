package net.loreli.logging;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DebugMessage extends AbstractLogMessage
{
	// TODO what would be an appropriate initial value?
	private int					m_iDebugLevel	= 0;
	private String				m_strText		= "";
	private Map<String, Object>	m_mapParameter	= new HashMap<String, Object>();

	public DebugMessage()
	{
		super(Thread.currentThread(), Thread.currentThread().getStackTrace()[2]);
	}

	public DebugMessage(Thread oThread, StackTraceElement oCaller)
	{
		super(oThread, oCaller);
	}

	public void setDebugLevel(int iDebugLevel)
	{
		m_iDebugLevel = iDebugLevel;
	}

	public int getDebugLevel()
	{
		return m_iDebugLevel;
	}

	public DebugMessage setText(String strText)
	{
		m_strText = strText;
		return this;
	}

	public String getText()
	{
		return m_strText;
	}

	public DebugMessage addParam(String strName, Object oValue)
	{
		m_mapParameter.put(strName, oValue);
		return this;
	}

	public Set<String> getParameterSet()
	{
		return m_mapParameter.keySet();
	}

	public Object getValue(String strParameterName)
	{
		return m_mapParameter.get(strParameterName);
	}

	@Override
	public String getMessageType()
	{
		return getClass().getSimpleName();
	}
}
