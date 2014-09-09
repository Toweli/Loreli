package net.loreli.messaging.messagetypes;

import java.io.IOException;

import net.loreli.messaging.IMessageType;
import net.loreli.serialization.IDeSerializer;
import net.loreli.serialization.ISerializer;

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
	public void serialize(ISerializer oSerializer)
	{
		oSerializer.writeBoolean(m_bRegistrationSuccess);
	}

	@Override
	public void deserialize(IDeSerializer oDeSerializer) throws IOException
	{
		m_bRegistrationSuccess = oDeSerializer.readBoolean();
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
