package net.loreli.messaging.messagetypes;

import java.io.IOException;

import net.loreli.base.IDeSerializer;
import net.loreli.base.ISerializer;
import net.loreli.base.Ref;
import net.loreli.messaging.IMessageType;

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
	public void serialize(ISerializer oSerializer)
	{
		oSerializer.writeByte(m_eType.getTypeID());
	}

	@Override
	public void deserialize(IDeSerializer oDeSerializer) throws IOException
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
