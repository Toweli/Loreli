package net.loreli.logging;

import java.util.ArrayList;
import java.util.HashMap;

public class ProgramLogSingleton
{
	public static ProgramLogSingleton								m_oSingleton	= new ProgramLogSingleton();

	private HashMap<String, ArrayList<AbstractLogMessageHandler>>	m_mapMassageHandler;							// WTF?

	private ProgramLogSingleton()
	{
		m_mapMassageHandler = new HashMap<String, ArrayList<AbstractLogMessageHandler>>();
	}

	public static ProgramLogSingleton getInstance()
	{
		return m_oSingleton;
	}

	public void addMessageHandler(AbstractLogMessageHandler oHandler)
	{
		String aSupportedMessageTypes[] = oHandler.getSupportedMessageTypes();

		for (int i = 0; i < aSupportedMessageTypes.length; ++i)
		{
			ArrayList<AbstractLogMessageHandler> alHandler = m_mapMassageHandler.get(aSupportedMessageTypes[i]);

			if (null == alHandler)
			{
				alHandler = new ArrayList<AbstractLogMessageHandler>();
				m_mapMassageHandler.put(aSupportedMessageTypes[i], alHandler);
			}

			alHandler.add(oHandler);
		}
	}

	public Boolean writeMessage(AbstractLogMessage oMessage)
	{
		ArrayList<AbstractLogMessageHandler> alHandler = m_mapMassageHandler.get(oMessage.getMessageType());

		if (null == alHandler)
			return false; // there is no messageHandler for this type of message.

		for (int i = 0; i < alHandler.size(); ++i)
		{
			AbstractLogMessageHandler oHandler = alHandler.get(i);

			if (null == oHandler)
				continue; // Well, that's not supposed to happen.

			oHandler.handleMessage(oMessage);
		}

		return true;
	}

	public Boolean error(String strName, String strDescription)
	{
		Thread oThread = Thread.currentThread();
		StackTraceElement oCaller = oThread.getStackTrace()[2];

		ErrorMessage oMessage = new ErrorMessage(oThread, oCaller);
		oMessage.setName(strName);
		oMessage.setDescription(strDescription);

		return writeMessage(oMessage);
	}

	public Boolean debug(String strText, int iDebugLevel)
	{
		Thread oThread = Thread.currentThread();
		StackTraceElement oCaller = oThread.getStackTrace()[2];

		DebugMessage oMessage = new DebugMessage(oThread, oCaller);
		oMessage.setText(strText);
		oMessage.setDebugLevel(iDebugLevel);

		return writeMessage(oMessage);
	}

	public Boolean info(String strText)
	{
		Thread oThread = Thread.currentThread();
		StackTraceElement oCaller = oThread.getStackTrace()[2];

		InfoMessage oMessage = new InfoMessage(oThread, oCaller);
		oMessage.setText(strText);

		return writeMessage(oMessage);
	}

	public Boolean warning(String strText, int iSeverity)
	{
		Thread oThread = Thread.currentThread();
		StackTraceElement oCaller = oThread.getStackTrace()[2];

		WarningMessage oMessage = new WarningMessage(oThread, oCaller);
		oMessage.setText(strText);
		oMessage.setSeverity(iSeverity);

		return writeMessage(oMessage);
	}
}
