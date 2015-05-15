package net.loreli.logging;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class AbstractLogMessageHandler
{
	private String[] m_aSupportedMessageTypes;
	private DataOutputStream m_oOutputStream;
	
	protected AbstractLogMessageHandler( OutputStream oOutputStream, String[] aSupportedMessageTypes )
	{
		m_aSupportedMessageTypes = aSupportedMessageTypes;
		m_oOutputStream = new DataOutputStream(oOutputStream);
	}
	
	final public String[] getSupportedMessageTypes()
	{
		return m_aSupportedMessageTypes;
	}
	
	abstract public void handleMessage( AbstractLogMessage oMessage );
	
	protected void write(String strMessage) throws IOException
	{
		m_oOutputStream.writeBytes(strMessage);
	}
}
