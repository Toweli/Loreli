package net.loreli.logging;

public abstract class AbstractLogMessageHandler
{
	private String[] m_aSuportedMessageTypes;
	
	protected AbstractLogMessageHandler( String[] aSuportedMessageTypes )
	{
		m_aSuportedMessageTypes = aSuportedMessageTypes;
	}
	
	final public String[] getSuportedMessageTypes()
	{
		return m_aSuportedMessageTypes;
	}
	
	abstract public void handleMessage( AbstractLogMessage oMessage );
}
