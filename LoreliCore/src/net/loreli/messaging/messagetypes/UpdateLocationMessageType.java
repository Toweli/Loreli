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
	public int serialize(ISerializer oSerializer)
	{
		int iLength = 0;
		iLength += oSerializer.writeSerializable(m_oLocation);
		return iLength;
	}

	@Override
	public int deserialize(IDeSerializer oDeSerializer) throws IOException
	{
		int iLength = 0;
		m_oLocation = new LongLatLocation();
		iLength += oDeSerializer.readSerializable(m_oLocation);
		return iLength;
	}
}
