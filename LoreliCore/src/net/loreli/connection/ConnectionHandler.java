package net.loreli.connection;

import java.util.HashMap;

import net.loreli.logging.ProgramLogSingleton;
import net.loreli.messaging.IMessageHandler;
import net.loreli.messaging.Message;

public class ConnectionHandler implements Runnable, IConnectionLostListener, IConnectionEstablishedListener
{
	private IConnection							m_oConnection;
	private Thread								m_oThread;
	private boolean								m_bRunning;

	private boolean								m_bConnecting;

	private HashMap<String, IMessageHandler>	m_mMessageHandler;

	public ConnectionHandler(IConnection oConnection)
	{
		m_mMessageHandler = new HashMap<String, IMessageHandler>();
		setConnection(oConnection);
	}

	public void startHandling()
	{
		m_oThread = new Thread(this);
		m_bRunning = true;
		m_oThread.start();
	}

	public void stopHandling()
	{
		m_bRunning = false;
	}

	@Override
	public void run()
	{
		while (m_bRunning)
		{
			if (m_oConnection.isConnected())
			{

				Message oMessage = m_oConnection.getReceivedMessage();
				if (oMessage != null)
					handleMessage(oMessage);
			}
		}
	}

	public void sendMessage(Message oMessage)
	{
		m_oConnection.sendMessage(oMessage);
	}

	private void handleMessage(Message oMessage)
	{
		m_mMessageHandler.get(oMessage.getMessageTypeName()).handleMessage(oMessage);
	}

	// TODO: allow more than one registration per messagetype
	public void registerMessageHandler(String strMessageType, IMessageHandler oMessageHandler)
	{
		m_mMessageHandler.put(strMessageType, oMessageHandler);
	}

	public void unregisterMessageHandler(String strMessageType)
	{
		m_mMessageHandler.remove(strMessageType);
	}

	public boolean isConnected()
	{
		return m_oConnection.isConnected();
	}

	@Override
	public void onConnectionLost()
	{
		disconnect();
		// TODO: do something special (implement something here)
		// whatever you want
		// ...
		// maybe delete something using this Handler
		// send something etc. ...
	}

	@Override
	public void onConnectionEstablishedListener(IConnection oConnection)
	{
		synchronized (this)
		{
			ProgramLogSingleton.getInstance().info("Connection established.");
			if (m_oConnection == oConnection && m_oConnection.isConnected())
			{
				m_oConnection.start();
				m_bConnecting = false;
			}
		}
	}

	public void disconnect()
	{
		m_oConnection.stop();
		m_oConnection.close();
	}

	public void setConnection(IConnection oConnection)
	{
		synchronized (this)
		{
			m_oConnection = oConnection;
			m_bConnecting = !m_oConnection.isConnected();
		}

	}

	public boolean isConnecting()
	{
		return m_bConnecting;
	}
}
