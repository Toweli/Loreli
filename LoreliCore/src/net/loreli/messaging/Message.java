package net.loreli.messaging;

import java.io.IOException;

import net.loreli.base.IDeSerializer;
import net.loreli.base.ISerializable;
import net.loreli.base.ISerializer;
import net.loreli.base.Ref;
import net.loreli.factory.Factory;
import net.loreli.factory.FactoryRegister;

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
	public void serialize(ISerializer oSerializer)
	{
//		int iLength = oSerializer.writeString(m_oMessageType.getClass().getSimpleName());
//		iLength += oSerializer.writeSerializable(m_oMessageType);
//		return iLength;
	}

	@Override
	public void deserialize(IDeSerializer oDeSerializer) throws IOException
	{
//		Ref<String> strName = new Ref<String>();
//		int iLength = oDeSerializer.readString(strName);
//		m_oMessageType = new Factory<IMessageType>(FactoryRegister.getInstance().getFactory("MessageTypeFactory"))
//				.createObject(strName.get());
//		iLength += oDeSerializer.readSerializable(m_oMessageType);
//		return iLength;
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
