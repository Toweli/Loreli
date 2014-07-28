package net.loreli.connection;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import net.loreli.base.StreamReaderWriter;
import net.loreli.messaging.Message;

public class NetworkConnection implements Runnable, IConnection
{
	private Socket								m_oSocket;
	private boolean								m_bIsRunning;
	private Thread								m_oListeningThread;

	private String								m_strAdress;
	private int									m_iPort;

	StreamReaderWriter							m_oStreamReaderWriter;

	private ArrayList<IConnectionLostListener>	m_liConnectionLostListener;

	private ArrayBlockingQueue<Message>			m_qReceivedMessages;

	NetworkConnection(Socket oSocket)
	{
		m_oSocket = oSocket;
		m_qReceivedMessages = new ArrayBlockingQueue<Message>(100);
		m_liConnectionLostListener = new ArrayList<IConnectionLostListener>();
	}

	NetworkConnection(String strAdress, int iPort)
	{
		m_qReceivedMessages = new ArrayBlockingQueue<Message>(100);
		m_liConnectionLostListener = new ArrayList<IConnectionLostListener>();
		m_strAdress = strAdress;
		m_iPort = iPort;
	}

	public boolean connect()
	{
		int iTrys = 0;
		boolean bConnected = false;
		while (!bConnected && iTrys < 10)
		{
			try
			{
				m_oSocket = new Socket(m_strAdress, m_iPort);
				bConnected = true;
			}
			catch (Exception e)
			{
				try
				{
					Thread.sleep(200);
				}
				catch (InterruptedException e1)
				{
				}
				e.printStackTrace();
			}
			iTrys++;
		}
		return bConnected;
	}

	public boolean isRunning()
	{
		return m_bIsRunning;
	}

	public void start()
	{
		try
		{
			m_oStreamReaderWriter = new StreamReaderWriter(m_oSocket.getInputStream(), m_oSocket.getOutputStream());
			m_oListeningThread = new Thread(this);
			m_oListeningThread.start();
			m_bIsRunning = true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			m_bIsRunning = false;
		}
	}

	public void stop()
	{
		m_bIsRunning = false;
	}

	@Override
	public void run()
	{
		// TODO: very dirty ...
		// while(m_oSocket == null || !m_oSocket.isConnected());

		while (m_bIsRunning && !m_oSocket.isClosed())
		{
			Message oMessage = new Message();
			try
			{
				oMessage.deserialize(m_oStreamReaderWriter);
				// synchronized (this) {
				try
				{
					m_qReceivedMessages.put(oMessage);
					// notify();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				// }
			}
			catch (IOException e1)
			{
				try
				{
					m_oSocket.close();
				}
				catch (IOException e)
				{
				}
			}

		}
		if (m_oSocket.isClosed())
		{
			sendConnectionLost();
		}
	}

	public void sendMessage(final Message oMessage)
	{
		Thread oThr = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				synchronized (m_oStreamReaderWriter)
				{
					oMessage.serialize(m_oStreamReaderWriter);
					m_oStreamReaderWriter.flush();
				}

			}
		});
		oThr.start();

	}

	public boolean hasReceivedMessage()
	{
		return !m_qReceivedMessages.isEmpty();
	}

	public Message getReceivedMessage()
	{
		try
		{
			return m_qReceivedMessages.poll(500, TimeUnit.MILLISECONDS);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public boolean registerConnectionLostHandler(IConnectionLostListener oHandler)
	{
		if (!m_liConnectionLostListener.contains(oHandler))
		{
			m_liConnectionLostListener.add(oHandler);
			return true;
		}
		return false;
	}

	public boolean unregisterConnectionLostHandler(IConnectionLostListener oHandler)
	{
		if (m_liConnectionLostListener.contains(oHandler))
		{
			m_liConnectionLostListener.remove(oHandler);
			return true;
		}
		return false;
	}

	private void sendConnectionLost()
	{
		for (IConnectionLostListener oListener : m_liConnectionLostListener)
		{
			oListener.onConnectionLost();
		}
	}

	@Override
	public boolean isConnected()
	{
		return m_oSocket != null && !m_oSocket.isClosed();
	}

	@Override
	public void close()
	{
		try
		{
			m_oSocket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
