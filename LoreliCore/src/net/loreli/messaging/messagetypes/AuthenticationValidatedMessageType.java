package net.loreli.messaging.messagetypes;

import java.io.IOException;

import net.loreli.messaging.IMessageType;
import net.loreli.serialization.IDeSerializer;
import net.loreli.serialization.ISerializer;

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
	public void serialize(ISerializer oSerializer)
	{
		oSerializer.writeBoolean(m_bAuthenticationSuccess);
	}

	@Override
	public void deserialize(IDeSerializer oDeSerializer) throws IOException
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
