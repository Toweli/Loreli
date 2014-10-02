package net.loreli.messaging;

import java.io.IOException;

import net.loreli.serialization.IReader;
import net.loreli.serialization.ISerializable;
import net.loreli.serialization.IWriter;

public class Message implements ISerializable
{
	private IMessageType	m_oMessageType;

	public Message()
	{

	}

	public Message(IMessageType oMessageType)
	{
		m_oMessageType = oMessageType;
	}

	@Override
	public void serialize(IWriter oSerializer)
	{
		oSerializer.writeObject(m_oMessageType);
	}

	@Override
	public void deserialize(IReader oDeSerializer) throws IOException
	{
		m_oMessageType = (IMessageType)oDeSerializer.readObject();
	}

	public String getMessageTypeName()
	{
		return m_oMessageType.getClass().getSimpleName();
	}

	public IMessageType getMessageType()
	{
		return m_oMessageType;
	}

	public void setMessageType(IMessageType oMessageType)
	{
		m_oMessageType = oMessageType;
	}

}
