package net.loreli.logging;

import java.io.IOException;
import java.io.OutputStream;

public class ErrorMassageHandler extends AbstractLogMessageHandler
{
	private static final String[]	m_aExceptedMessageTypes	= { ErrorMessage.class.getSimpleName() };

	private String					m_strFormat = "[%1$tF %1$tT] [%6$s] [%7$s] [Thread: %2$s] [Class: %3$s] [Method: %4$s] [Line: %5$d]%n";

	public ErrorMassageHandler(OutputStream oOut)
	{
		super(oOut, m_aExceptedMessageTypes);
	}

	/**
	 * Sets the string, that is used to format the message when it's written into the output stream.
	 * Default Format:
	 *    "[%1$tF %1$tT] [%6$s] [%7$s] [Thread: %2$s] [Class: %3$s] [Method: %4$s] [Line: %5$d]%n"
	 * usable parameter:
	 * 		1$	The date/time when the message has been created
	 * 		2$	The name of the Thread in which the message has been created.
	 * 		3$	The name of the Class in which the message has been created. 
	 * 		4$	The name of the Method in which the message has been created.  
	 * 		5$	The line number in which the message has been created.
	 * 
	 * 		6$	The name of the error message
	 * 		7$	A description of the error message   
	 */
	public void setOutputFormat( String strFormate )
	{
		m_strFormat = strFormate;
	}
	
	@Override
	public void handleMessage(AbstractLogMessage oMessage)
	{
		// we only except ErrorMessages, so oMessage should be an ErrorMessage
		ErrorMessage oMsg = (ErrorMessage) (oMessage);

		String strMsg = String.format(m_strFormat,
				oMsg.getDate(), 
				oMsg.getThread().getName(), 
				oMsg.getCaller().getClassName(), 
				oMsg.getCaller().getMethodName(), 
				oMsg.getCaller().getLineNumber(), 
				oMsg.getName(), 
				oMsg.getDescription()
		);

		try
		{
			write(strMsg);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
