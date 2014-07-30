package net.loreli.logging;

import java.io.DataOutputStream;
import java.io.IOException;

public class DebugMassageHandler extends AbstractLogMessageHandler
{
	private static final String[]	m_aExceptedMessageTypes	= { DebugMessage.class.getSimpleName() };

	private DataOutputStream		m_oBufferedOut;
	private int						m_iMinDebugLevel		= 0;
	private int						m_iMaxDebugLevel		= Integer.MAX_VALUE;
	private String					m_strFormat				= "[%1$tF %1$tT] [%6$s] [%7$s] [Thread: %2$s] [Class: %3$s] [Method: %4$s] [Line: %5$d]%n";
	private String					m_strParameterFormat	= "%1$s=\"%2$s\"";
	private String					m_strParameterSeparator	= " ";

	public DebugMassageHandler(DataOutputStream oOut)
	{
		super(m_aExceptedMessageTypes);

		m_oBufferedOut = oOut;
	}

	/**
	 * Sets the string, that is used to format the message when it's written
	 * into the output stream. Default Format:
	 * 	"[%1$tF %1$tT] [%6$s] [%7$s ] [Thread: %2$s] [Class: %3$s] [Method: %4$s] [Line: %5$d]%n"
	 * usable parameter: 
	 * 		1$	The date/time when the message has been created 
	 * 		2$	The name of the Thread in which the message has been created
	 * 		3$	The name of the Class in which the message has been created 
	 * 		4$	The name of the Method in which the message has been created 
	 * 		5$ 	The line number in which the message has been created.
	 * 
	 * 		6$	The text of the debug message
	 * 		7$	A list of all parameter stored in the debug message 
	 * 		8$	The DebugLevel of the debug message
	 */
	public void setOutputFormat(String strFormat)
	{
		m_strFormat = strFormat;
	}

	/**
	 * Sets the strings, that are used to format the list of list of all
	 * parameter stored in the debug message
	 * 
	 * @param strFormat
	 *            the string, that is used to format a key (parameter name)
	 *            value pair.
	 * @param srtSeparator
	 *            the string, that is used to separate key (parameter name)
	 *            value pairs
	 */
	public void setParameterListFormat(String strFormat, String srtSeparator)
	{
		m_strParameterFormat = strFormat;
		m_strParameterSeparator = srtSeparator;
	}

	public void setMinDebugLevel(int iDebugLevel)
	{
		m_iMinDebugLevel = iDebugLevel;
	}
	public void setMaxDebugLevel(int iDebugLevel)
	{
		m_iMaxDebugLevel = iDebugLevel;
	}
	
	@Override
	public void handleMessage(AbstractLogMessage oMessage)
	{
		// we only except DebugMessages, so oMessage should be an DebugMessage
		DebugMessage oMsg = (DebugMessage) (oMessage);

		if (oMsg.getDebugLevel() < m_iMinDebugLevel || oMsg.getDebugLevel() > m_iMaxDebugLevel)
			return;

		StringBuilder oParamList = new StringBuilder();

		boolean bFirst = true;
		for (String strParam : oMsg.getParameterSet())
		{
			if (!bFirst)
				oParamList.append(m_strParameterSeparator);
			oParamList.append(String.format(m_strParameterFormat, strParam, oMsg.getValue(strParam)));
			bFirst = false;
		}

		String strMsg = String.format(m_strFormat, oMsg.getDate(), oMsg.getThread().getName(), oMsg.getCaller()
				.getClassName(), oMsg.getCaller().getMethodName(), oMsg.getCaller().getLineNumber(), oMsg.getText(),
				oParamList.toString(), oMsg.getDebugLevel());

		try
		{
			m_oBufferedOut.writeBytes(strMsg);
		}
		catch (IOException e)
		{
			/*
			 * we failed to writ the error message into to output stream TODO
			 * what should we do now? If we write an error message into the
			 * ProgramLog we will probably end up here again.
			 */
		}
	}
}
