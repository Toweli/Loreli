package net.loreli.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionListener implements Runnable
{
	private ServerSocket							m_oServerSocket;
	private int										m_iListeningPort;

	private ArrayList<IConnection>					m_liConnections;
	private ReentrantLock							m_oLock;

	private ArrayList<IConnectionRequestListener>	m_liConnectionRequestHandler;

	private Thread									m_oListeningThread;

	private boolean									m_bRunning;

	public ConnectionListener(int iListeningPort)
	{
		m_iListeningPort = iListeningPort;
		m_liConnectionRequestHandler = new ArrayList<IConnectionRequestListener>();
		m_liConnections = new ArrayList<IConnection>();
	}

	public void startConnectionListening() throws IOException
	{
		m_oLock = new ReentrantLock();
		m_oServerSocket = new ServerSocket(m_iListeningPort);
		m_oServerSocket.setSoTimeout(1000);
		m_oListeningThread = new Thread(this);
		m_bRunning = true;
		m_oListeningThread.start();
	}

	public void stopConnectionListening()
	{
		m_bRunning = false;
	}

	public boolean hasConnections()
	{
		boolean bResult;
		m_oLock.lock();
		bResult = m_liConnections.size() != 0;
		m_oLock.unlock();
		return bResult;
	}

	public boolean isRunning()
	{
		return m_bRunning;
	}

	@Override
	public void run()
	{
		Socket oClient;
		while (m_bRunning)
		{
			try
			{
				oClient = m_oServerSocket.accept();
				NetworkConnection oConnection = new NetworkConnection(oClient);
				m_oLock.lock();
				m_liConnections.add(oConnection);
				m_oLock.unlock();
				notifyConnectionRequest();
			}
			catch (SocketTimeoutException e)
			{
			}
			catch (IOException e)
			{
				m_bRunning = false;
			}
			Thread.yield();
		}
	}

	public ConnectionHandler getConnection()
	{
		IConnection oResult = null;
		if (hasConnections())
		{
			oResult = m_liConnections.get(0);
			m_oLock.lock();
			m_liConnections.remove(0);
			m_oLock.unlock();
		}
		oResult.start();
		return new ConnectionHandler(oResult);
	}

	public void registerConnectionRequestHandler(IConnectionRequestListener oConnectionReqestHandler)
	{
		m_liConnectionRequestHandler.add(oConnectionReqestHandler);
	}

	private void notifyConnectionRequest()
	{
		for (IConnectionRequestListener oHandler : m_liConnectionRequestHandler)
		{
			oHandler.onConnectionRequested(this);
		}
	}

}
