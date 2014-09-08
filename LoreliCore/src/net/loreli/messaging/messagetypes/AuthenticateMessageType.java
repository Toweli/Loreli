package net.loreli.messaging.messagetypes;

import java.io.IOException;

import net.loreli.base.IDeSerializer;
import net.loreli.base.ISerializer;
import net.loreli.base.Ref;
import net.loreli.messaging.IMessageType;

public class AuthenticateMessageType implements IMessageType
{
	private String	m_strAccountName;
	private String	m_strPasswordHash;

	public AuthenticateMessageType()
	{
	}

	public AuthenticateMessageType(String strAccountName, String strPasswordHash)
	{
		m_strAccountName = strAccountName;
		m_strPasswordHash = strPasswordHash;
	}

	@Override
	public void serialize(ISerializer oSerializer)
	{
		oSerializer.writeString(m_strAccountName);
		oSerializer.writeString(m_strPasswordHash);
		
	}

	@Override
	public void deserialize(IDeSerializer oDeSerializer) throws IOException
	{
		m_strAccountName = oDeSerializer.readString();
		m_strPasswordHash = oDeSerializer.readString();
	}

	public String getAccountName()
	{
		return m_strAccountName;
	}

	public void setAccountName(String strAccountName)
	{
		m_strAccountName = strAccountName;
	}

	public String getPasswordHash()
	{
		return m_strPasswordHash;
	}

	public void setPasswordHash(String strPasswordHash)
	{
		m_strPasswordHash = strPasswordHash;
	}

}
