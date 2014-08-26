package net.loreli.logging;

import java.io.IOException;
import java.io.OutputStream;

public class WarningMessageHandler extends AbstractLogMessageHandler
{
	private static final String[]	m_aExceptedMessageTypes	= { WarningMessage.class.getSimpleName() };

	private int						m_iMinSeverity	= 0;
	private int						m_iMaxSeverity	= Integer.MAX_VALUE;
	private String					m_strFormat			= "[%1$tF %1$tT] [%6$s] [Thread: %2$s] [Class: %3$s] [Method: %4$s] [Line: %5$d]%n";

	public WarningMessageHandler(OutputStream oOut)
	{
		super(oOut, m_aExceptedMessageTypes);
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
	 * 		6$ 	The text of the warning
	 * 		7$ 	The Severity of the warning
	 */
	public void setOutputFormat(String strFormat)
	{
		m_strFormat = strFormat;
	}
	
	public void setMinSeverity(int iSeverity)
	{
		m_iMinSeverity = iSeverity;
	}
	
	public void setMaxSeverity(int iSeverity)
	{
		m_iMaxSeverity = iSeverity;
	}

	@Override
	public void handleMessage(AbstractLogMessage oMessage)
	{
		// we only except DebugMessages, so oMessage should be an DebugMessage
		WarningMessage oMsg = (WarningMessage) (oMessage);

		if (oMsg.getSeverity() < m_iMinSeverity || oMsg.getSeverity() > m_iMaxSeverity)
			return;

		String strMsg = String.format(m_strFormat, 
				oMsg.getDate(), 
				oMsg.getThread().getName(), 
				oMsg.getCaller().getClassName(), 
				oMsg.getCaller().getMethodName(), 
				oMsg.getCaller().getLineNumber(), 
				oMsg.getText(), 
				oMsg.getSeverity());

		try
		{
			write(strMsg);
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Can't write to output stream.");
		}
	}
}
