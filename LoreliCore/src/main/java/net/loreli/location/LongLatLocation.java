package net.loreli.location;

import java.io.IOException;

import net.loreli.serialization.IReader;
import net.loreli.serialization.ISerializable;
import net.loreli.serialization.IWriter;

public class LongLatLocation implements ISerializable
{
	private double	m_dLongitude;
	private double	m_dLatitude;

	public LongLatLocation(double dLongitude, double dLatitude)
	{
		setLongitude(dLongitude);
		setLatitude(dLatitude);
	}

	public LongLatLocation()
	{
		setLongitude(0);
		setLatitude(0);
	}

	public double getLongitude()
	{
		return m_dLongitude;
	}

	public void setLongitude(double dLongitude)
	{
		m_dLongitude = dLongitude;
	}

	public double getLatitude()
	{
		return m_dLatitude;
	}

	public void setLatitude(double m_dLatitude)
	{
		this.m_dLatitude = m_dLatitude;
	}

	@Override
	public void serialize(IWriter oSerializer)
	{
		oSerializer.writeDouble(m_dLongitude);
		oSerializer.writeDouble(m_dLatitude);
	}

	@Override
	public void deserialize(IReader oDeSerializer) throws IOException
	{
		m_dLongitude = oDeSerializer.readDouble();
		m_dLatitude = oDeSerializer.readDouble();
	}

}
