package net.loreli.messaging.messagetypes;

import java.io.IOException;

import net.loreli.messaging.IMessageType;
import net.loreli.serialization.IReader;
import net.loreli.serialization.IWriter;

public class RequestIsAliveMessageType implements IMessageType
{
	public enum RequestType
	{
		NONE((byte) 0), REQUEST((byte) 1), RESULT((byte) 2);

		private final byte	m_iTypeID;

		RequestType(byte iTypeID)
		{
			m_iTypeID = iTypeID;
		}

		public byte getTypeID()
		{
			return m_iTypeID;
		}
	}

	private RequestType	m_eType;

	public RequestIsAliveMessageType(RequestType eType)
	{
		m_eType = eType;
	}

	public RequestIsAliveMessageType()
	{
		m_eType = RequestType.NONE;
	}

	public RequestType getRequestType()
	{
		return m_eType;
	}

	public void setRequestType(RequestType eType)
	{
		m_eType = eType;
	}

	@Override
	public void serialize(IWriter oSerializer)
	{
		oSerializer.writeByte(m_eType.getTypeID());
	}

	@Override
	public void deserialize(IReader oDeSerializer) throws IOException
	{
		byte bRes = oDeSerializer.readByte();
		for (RequestType eType : RequestType.values())
		{
			if (eType.getTypeID() == bRes)
			{
				m_eType = eType;
			}
		}
	}

}
