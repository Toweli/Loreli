package net.loreli.connection;

public class ConnectionCreator
{
	public static ConnectionHandler createConnection(String strAddress, int iPort)
	{
		final NetworkConnection oNetConnection = new NetworkConnection(strAddress, iPort);
		final ConnectionHandler oResult = new ConnectionHandler(oNetConnection);

		Thread oThr = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				oNetConnection.connect();
				if (oNetConnection.isConnected())
				{
					oResult.onConnectionEstablishedListener(oNetConnection);
				}
				else
				{
					oResult.onConnectionLost();
				}
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

		Thread oThr = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				oNetConnection.connect();
				if (oNetConnection.isConnected())
				{
					oConnectionHandler.onConnectionEstablishedListener(oNetConnection);
				}
				else
				{
					oConnectionHandler.onConnectionLost();
				}
			}

		});
		oThr.start();
	}
}
