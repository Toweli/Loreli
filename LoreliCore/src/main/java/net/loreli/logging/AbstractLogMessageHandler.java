package net.loreli.logging;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class AbstractLogMessageHandler
{
	private String[] m_aSuportedMessageTypes;
	private DataOutputStream m_oOutputStream;
	
	protected AbstractLogMessageHandler( OutputStream oOutputStream, String[] aSuportedMessageTypes )
	{
		m_aSuportedMessageTypes = aSuportedMessageTypes;
		m_oOutputStream = new DataOutputStream(oOutputStream);
	}
	
	final public String[] getSuportedMessageTypes()
	{
		return m_aSuportedMessageTypes;
	}
	
	abstract public void handleMessage( AbstractLogMessage oMessage );
	
	protected void write(String strMessage) throws IOException
	{
		m_oOutputStream.writeBytes(strMessage);
	}
}
