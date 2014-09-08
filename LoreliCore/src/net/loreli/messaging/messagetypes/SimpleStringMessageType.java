package net.loreli.messaging.messagetypes;

import java.io.IOException;

import net.loreli.base.IDeSerializer;
import net.loreli.base.ISerializer;
import net.loreli.base.Ref;
import net.loreli.messaging.IMessageType;

public class SimpleStringMessageType implements IMessageType
{
	private String	m_strMessage;

	public SimpleStringMessageType()
	{
	}

	public SimpleStringMessageType(String strMessage)
	{
		m_strMessage = strMessage;
	}

	@Override
	public void serialize(ISerializer oSerializer)
	{
		oSerializer.writeString(m_strMessage);
	}

	@Override
	public void deserialize(IDeSerializer oDeSerializer) throws IOException
	{
		setMessage(oDeSerializer.readString());
	}

	public String getMessage()
	{
		return m_strMessage;
	}

	public void setMessage(String strMessage)
	{
		m_strMessage = strMessage;
	}

}
