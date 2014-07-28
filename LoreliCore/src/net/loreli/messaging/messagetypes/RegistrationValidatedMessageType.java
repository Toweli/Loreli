package net.loreli.messaging.messagetypes;

import java.io.IOException;

import net.loreli.base.IDeSerializer;
import net.loreli.base.ISerializer;
import net.loreli.base.Ref;
import net.loreli.messaging.IMessageType;

public class RegistrationValidatedMessageType implements IMessageType
{
	private boolean	m_bRegistrationSuccess;

	public RegistrationValidatedMessageType()
	{
	}

	public RegistrationValidatedMessageType(boolean bRegistrationSuccess)
	{
		m_bRegistrationSuccess = bRegistrationSuccess;
	}

	@Override
	public int serialize(ISerializer oSerializer)
	{
		return oSerializer.writeBoolean(m_bRegistrationSuccess);
	}

	@Override
	public int deserialize(IDeSerializer oDeSerializer) throws IOException
	{
		Ref<Boolean> bRegistrationSuccess = new Ref<Boolean>();
		int iLength = oDeSerializer.readBoolean(bRegistrationSuccess);
		m_bRegistrationSuccess = bRegistrationSuccess.get();
		return iLength;
	}

	public boolean isRegistrationSuccess()
	{
		return m_bRegistrationSuccess;
	}

	public void setRegistrationSuccess(boolean bRegistrationSuccess)
	{
		m_bRegistrationSuccess = bRegistrationSuccess;
	}
}
