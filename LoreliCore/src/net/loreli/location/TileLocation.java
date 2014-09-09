package net.loreli.location;

import java.io.IOException;

import net.loreli.serialization.IDeSerializer;
import net.loreli.serialization.ISerializable;
import net.loreli.serialization.ISerializer;

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
	public void serialize(ISerializer oSerializer)
	{
		oSerializer.writeDouble(m_dTileX);
		oSerializer.writeDouble(m_dTileY);
		oSerializer.writeInt(m_iZoom);
	}

	@Override
	public void deserialize(IDeSerializer oDeSerializer) throws IOException
	{
		m_dTileX = oDeSerializer.readDouble();
		m_dTileY = oDeSerializer.readDouble();
		m_iZoom = oDeSerializer.readInt();
	}
}
