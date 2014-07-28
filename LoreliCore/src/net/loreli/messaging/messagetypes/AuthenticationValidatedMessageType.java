package net.loreli.messaging.messagetypes;

import java.io.IOException;

import net.loreli.base.IDeSerializer;
import net.loreli.base.ISerializer;
import net.loreli.base.Ref;
import net.loreli.messaging.IMessageType;

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
	public int serialize(ISerializer oSerializer)
	{
		return oSerializer.writeBoolean(m_bAuthenticationSuccess);
	}

	@Override
	public int deserialize(IDeSerializer oDeSerializer) throws IOException
	{
		Ref<Boolean> bAuthentificationSuccess = new Ref<Boolean>();
		int iLength = oDeSerializer.readBoolean(bAuthentificationSuccess);
		m_bAuthenticationSuccess = bAuthentificationSuccess.get();
		return iLength;
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
