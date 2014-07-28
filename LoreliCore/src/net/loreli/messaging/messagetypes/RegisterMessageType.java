package net.loreli.messaging.messagetypes;

import java.io.IOException;

import net.loreli.base.IDeSerializer;
import net.loreli.base.ISerializer;
import net.loreli.base.Ref;
import net.loreli.messaging.IMessageType;

public class RegisterMessageType implements IMessageType
{
	private String	m_strAccountName;
	private String	m_strPasswordHash;

	public RegisterMessageType()
	{
	}

	public RegisterMessageType(String strAccountName, String strPasswordHash)
	{
		m_strAccountName = strAccountName;
		m_strPasswordHash = strPasswordHash;
	}

	@Override
	public int serialize(ISerializer oSerializer)
	{
		int iLength = oSerializer.writeString(m_strAccountName);
		iLength += oSerializer.writeString(m_strPasswordHash);
		return iLength;
	}

	@Override
	public int deserialize(IDeSerializer oDeSerializer) throws IOException
	{
		Ref<String> strAccountName = new Ref<String>();
		Ref<String> strPasswordHash = new Ref<String>();
		int iLength = oDeSerializer.readString(strAccountName);
		iLength += oDeSerializer.readString(strPasswordHash);
		m_strAccountName = strAccountName.get();
		m_strPasswordHash = strPasswordHash.get();
		return iLength;
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
