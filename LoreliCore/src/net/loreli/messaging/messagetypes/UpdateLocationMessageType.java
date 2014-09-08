package net.loreli.messaging.messagetypes;

import java.io.IOException;

import net.loreli.base.IDeSerializer;
import net.loreli.base.ISerializer;
import net.loreli.location.LongLatLocation;
import net.loreli.messaging.IMessageType;

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
	public void serialize(ISerializer oSerializer)
	{
		oSerializer.writeSerializable(m_oLocation);
	}

	@Override
	public void deserialize(IDeSerializer oDeSerializer) throws IOException
	{
		m_oLocation = new LongLatLocation();
		m_oLocation = (LongLatLocation) oDeSerializer.readSerializable();
	}
}
