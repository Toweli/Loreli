package net.loreli.location;

import java.io.IOException;

import net.loreli.base.IDeSerializer;
import net.loreli.base.ISerializable;
import net.loreli.base.ISerializer;
import net.loreli.base.Ref;

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
	public int serialize(ISerializer oSerializer)
	{
		int iLength = 0;
		iLength += oSerializer.writeDouble(m_dLongitude);
		iLength += oSerializer.writeDouble(m_dLatitude);
		return iLength;
	}

	@Override
	public int deserialize(IDeSerializer oDeSerializer) throws IOException
	{
		int iLength = 0;
		Ref<Double> oVal = new Ref<Double>();
		iLength += oDeSerializer.readDouble(oVal);
		m_dLongitude = oVal.get();
		iLength += oDeSerializer.readDouble(oVal);
		m_dLatitude = oVal.get();
		return iLength;
	}

}
