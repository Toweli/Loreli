package net.loreli.messaging.messagetypes;

import java.io.IOException;

import net.loreli.location.LongLatLocation;
import net.loreli.messaging.IMessageType;
import net.loreli.serialization.IReader;
import net.loreli.serialization.IWriter;

public class UpdateLocationMessageType implements IMessageType
{
	private LongLatLocation	m_oLocation;

	public UpdateLocationMessageType()
	{
		setLocation(null);
	}

	public UpdateLocationMessageType(LongLatLocation oLongLat)
	{
		setLocation(oLongLat);
	}

	public LongLatLocation getLocation()
	{
		return m_oLocation;
	}

	public void setLocation(LongLatLocation oLocation)
	{
		m_oLocation = oLocation;
	}

	@Override
	public void serialize(IWriter oSerializer)
	{
		oSerializer.writeObject(m_oLocation);
	}

	@Override
	public void deserialize(IReader oDeSerializer) throws IOException
	{
		m_oLocation = (LongLatLocation)oDeSerializer.readObject();
	}
}
