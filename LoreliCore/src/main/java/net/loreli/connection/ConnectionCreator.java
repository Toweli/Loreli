package net.loreli.connection;

public class ConnectionCreator
{
	public static ConnectionHandler createConnection(String strAddress, int iPort)
	{
		final NetworkConnection oNetConnection = new NetworkConnection(strAddress, iPort);
		final ConnectionHandler oResult = new ConnectionHandler(oNetConnection);

		oNetConnection.ConnectionEstablishedEvent.addHandler(oResult, "onConnectionEstablished");
		oNetConnection.ConnectionLostEvent.addHandler(oResult, "onConnectionLost");
		
		Thread oThr = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				oNetConnection.connect();
			}
		});
		oThr.start();
		return oResult;
	}

	public static void performReconnect(final ConnectionHandler oConnectionHandler, String strAddress, int iPort)
	{
		final NetworkConnection oNetConnection = new NetworkConnection(strAddress, iPort);
		oConnectionHandler.disconnect();
		oConnectionHandler.setConnection(oNetConnection);
		

		oNetConnection.ConnectionEstablishedEvent.addHandler(oConnectionHandler, "onConnectionEstablished");
		oNetConnection.ConnectionLostEvent.addHandler(oConnectionHandler, "onConnectionLost");

		Thread oThr = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				oNetConnection.connect();
			}
		});
		oThr.start();
	}
}
