package net.loreli.logging;

import java.io.DataOutputStream;
import java.io.IOException;

public class InfoMassageHandler extends AbstractLogMessageHandler
{
	private static final String[]	m_aExceptedMessageTypes	= { InfoMessage.class.getSimpleName() };

	private DataOutputStream		m_oBufferedOut;
	private String					m_strFormat			= "[%1$tF %1$tT] [%6$s] [Thread: %2$s] [Class: %3$s] [Method: %4$s] [Line: %5$d]%n";

	public InfoMassageHandler(DataOutputStream oOut)
	{
		super(m_aExceptedMessageTypes);

		m_oBufferedOut = oOut;
	}

	/**
	 * Sets the string, that is used to format the message when it's written
	 * into the output stream. Default Format:
	 * 	"[%1$tF %1$tT] [%6$s] [Thread: %2$s] [Class: %3$s] [Method: %4$s] [Line: %5$d]%n"
	 * usable parameter: 
	 * 		1$ 	The date/time when the message has been created 
	 * 		2$ 	The name of the Thread in which the message has been created
	 * 		3$ 	The name of the Class in which the message has been created
	 * 		4$ 	The name of the Method in which the message has been created
	 * 		5$ 	The line number in which the message has been created.
	 * 
	 * 		6$ 	The text of the info message
	 */
	public void setOutputFormat(String strFormat)
	{
		m_strFormat = strFormat;
	}

	@Override
	public void handleMessage(AbstractLogMessage oMessage)
	{
		// we only except InfoMessages, so oMessage should be an InfoMessage
		InfoMessage oMsg = (InfoMessage) (oMessage);


		String strMsg = String.format(m_strFormat,
				oMsg.getDate(), 
				oMsg.getThread().getName(), 
				oMsg.getCaller().getClassName(), 
				oMsg.getCaller().getMethodName(), 
				oMsg.getCaller().getLineNumber(),
				oMsg.getText());

		try
		{
			m_oBufferedOut.writeBytes(strMsg);
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Can't write to output stream.");
		}
	}
}
