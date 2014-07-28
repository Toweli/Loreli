package net.loreli.location;

import java.io.IOException;

import net.loreli.base.IDeSerializer;
import net.loreli.base.ISerializable;
import net.loreli.base.ISerializer;
import net.loreli.base.Ref;

public class TileLocation implements ISerializable
{
	private int		m_iZoom;
	private double	m_dTileX;
	private double	m_dTileY;

	public TileLocation(double dTileX, double dTileY, int iZoom)
	{
		setTileX(dTileX);
		setTileY(dTileY);
		setZoom(iZoom);
	}

	public TileLocation()
	{
		setTileX(0);
		setTileY(0);
		setZoom(0);
	}

	public double getTileX()
	{
		return m_dTileX;
	}

	public void setTileX(double dTileX)
	{
		m_dTileX = dTileX;
	}

	public double getTileY()
	{
		return m_dTileY;
	}

	public void setTileY(double m_dTileY)
	{
		this.m_dTileY = m_dTileY;
	}

	public int getZoom()
	{
		return m_iZoom;
	}

	public void setZoom(int iZoom)
	{
		m_iZoom = iZoom;
	}

	public TileLocation roundToBottom()
	{
		return new TileLocation(Math.floor(m_dTileX), Math.floor(m_dTileY), m_iZoom);
	}

	@Override
	public boolean equals(Object oOther)
	{
		TileLocation oOtherLoc = (TileLocation) oOther;
		return m_dTileX == oOtherLoc.m_dTileX && m_dTileY == oOtherLoc.m_dTileY && m_iZoom == oOtherLoc.m_iZoom;
	}

	@Override
	public int serialize(ISerializer oSerializer)
	{
		int iLength = 0;
		iLength += oSerializer.writeDouble(m_dTileX);
		iLength += oSerializer.writeDouble(m_dTileY);
		iLength += oSerializer.writeInt(m_iZoom);
		return iLength;
	}

	@Override
	public int deserialize(IDeSerializer oDeSerializer) throws IOException
	{
		int iLength = 0;
		Ref<Double> oVal = new Ref<Double>();
		iLength += oDeSerializer.readDouble(oVal);
		m_dTileX = oVal.get();
		iLength += oDeSerializer.readDouble(oVal);
		m_dTileY = oVal.get();
		Ref<Integer> iVal = new Ref<Integer>();
		iLength += oDeSerializer.readInt(iVal);
		m_iZoom = iVal.get();
		return iLength;
	}
}
