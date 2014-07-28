package net.loreli.location;

public class LocationConverter
{
	public static TileLocation convertToTile(LongLatLocation oLongLat, int iZoom)
	{
		double dTileX = (oLongLat.getLongitude() + 180) / 360 * (1 << iZoom);
		double dTileY = (1 - Math.log(Math.tan(Math.toRadians(oLongLat.getLatitude())) + 1
				/ Math.cos(Math.toRadians(oLongLat.getLatitude())))
				/ Math.PI)
				/ 2 * (1 << iZoom);

		return new TileLocation(dTileX, dTileY, iZoom);
	}

	public static LongLatLocation convertToLongLat(TileLocation oTile)
	{
		double dLongitude = oTile.getTileX() / (1 << oTile.getZoom()) * 360 - 180;
		double dN = Math.PI - 2 * Math.PI * oTile.getTileY() / (1 << oTile.getZoom());
		double dLatitude = 180.0 / Math.PI * Math.atan(0.5 * Math.exp(dN) - Math.exp(-dN));

		return new LongLatLocation(dLongitude, dLatitude);
	}
}
