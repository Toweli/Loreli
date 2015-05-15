package net.loreli.messaging.messagetypes;

import java.io.IOException;

import net.loreli.messaging.IMessageType;
import net.loreli.serialization.IReader;
import net.loreli.serialization.IWriter;

public class AuthenticationValidatedMessageType implements IMessageType
{
	private boolean	m_bAuthenticationSuccess;

	public AuthenticationValidatedMessageType()
	{
	}

	public AuthenticationValidatedMessageType(boolean bAuthenticationSuccess)
	{
		m_bAuthenticationSuccess = bAuthenticationSuccess;
	}

	@Override
	public void serialize(IWriter oSerializer)
	{
		oSerializer.writeBoolean(m_bAuthenticationSuccess);
	}

	@Override
	public void deserialize(IReader oDeSerializer) throws IOException
	{
		m_bAuthenticationSuccess = oDeSerializer.readBoolean();
	}

	public boolean isAuthenticationSuccess()
	{
		return m_bAuthenticationSuccess;
	}

	public void setAuthenticationSuccess(boolean bAuthenticationSuccess)
	{
		m_bAuthenticationSuccess = bAuthenticationSuccess;
	}

}
