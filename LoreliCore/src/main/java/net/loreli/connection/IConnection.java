package net.loreli.connection;

import net.loreli.messaging.Message;

public interface IConnection
{
	void sendMessage(Message oMessage);

	boolean hasReceivedMessage();

	Message getReceivedMessage();

	void start();

	void stop();

	boolean isConnected();

	void close();
}
