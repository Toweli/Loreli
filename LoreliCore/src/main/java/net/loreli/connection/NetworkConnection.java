package net.loreli.connection;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import net.loreli.base.StreamReaderWriter;
import net.loreli.eventsystem.TEvent;
import net.loreli.logging.ProgramLogSingleton;
import net.loreli.messaging.Message;

public class NetworkConnection implements Runnable, IConnection
{
	public TEvent<IConnection> ConnectionLostEvent = new TEvent<>(this, IConnection.class);
	public TEvent<IConnection> ConnectionEstablishedEvent = new TEvent<>(this, IConnection.class);
	
	private Socket								m_oSocket;
	private boolean								m_bIsRunning;
	private Thread								m_oListeningThread;

	private String								m_strAddress;
	private int									m_iPort;

	private StreamReaderWriter							m_oStreamReaderWriter;

	private BlockingQueue<Message>			m_qReceivedMessages;

	NetworkConnection(Socket oSocket)
	{
		m_oSocket = oSocket;
		m_qReceivedMessages = new ArrayBlockingQueue<Message>(100);
	}

	NetworkConnection(String strAddress, int iPort)
	{
		m_qReceivedMessages = new ArrayBlockingQueue<Message>(100);
		m_strAddress = strAddress;
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
				m_oSocket = new Socket(m_strAddress, m_iPort);
				bConnected = true;
				ConnectionEstablishedEvent.raise(this);
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

				ProgramLogSingleton.getInstance().error("ConnectionError",
						"Can't create a Socket to " + m_strAddress + ":" + m_iPort);
			}
			iTrys++;
		}
		if(!bConnected)
		{
			ConnectionLostEvent.raise(this);
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
			ProgramLogSingleton.getInstance().error("IOException", "Can't create a StreamReaderWriter");
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
		while (m_bIsRunning && !m_oSocket.isClosed())
		{
			Message oMessage = new Message();
			try
			{
				oMessage.deserialize(m_oStreamReaderWriter);
				try
				{
					m_qReceivedMessages.put(oMessage);
				}
				catch (InterruptedException e)
				{
					ProgramLogSingleton.getInstance().error("InterruptedException",
							"BlockingQueue was interrupted while putting a message.");
				}
			}
			catch (IOException e1)
			{
				ProgramLogSingleton.getInstance().error("DeserializationError", "Can't deserialize a message");
				try
				{
					m_oSocket.close();
				}
				catch (IOException e)
				{
					ProgramLogSingleton.getInstance().error("SocketCloseException", "Can't close socket.");
				}
			}

		}
		if (m_oSocket.isClosed())
		{
			ConnectionLostEvent.raise(this);
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
			ProgramLogSingleton.getInstance().error("InterruptedException",
					"BlockingQueue was interrupted while waiting.");
		}
		return null;
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
			ProgramLogSingleton.getInstance().error("SocketError", "Can't close the Socket");
		}
	}
}
