package net.loreli.mapping;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import net.loreli.location.TileLocation;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


public class UrlMapLoader extends AbstractMapLoader
{
	private String m_strUrlPrototype;
	
	private HashMap<TileLocation, Bitmap> m_mapBitmaps;
		
	protected UrlMapLoader(IMapLoader oNext, String strUrl) {
		super(oNext);
		m_strUrlPrototype = strUrl;
	}

	@Override
	protected boolean requestMapTileImplementation(TileLocation oLocation,
			IListener oListener) {
		if(m_mapBitmaps.containsKey(oLocation))
		{
			return true;
		}
		try {
			URL oUrl= new URL(String.format(m_strUrlPrototype, oLocation.getZoom(), (int)oLocation.getTileX(), (int)oLocation.getTileY()));
			InputStream oInputStream = oUrl.openStream();
			Bitmap oBitmap = BitmapFactory.decodeStream(oInputStream);
			m_mapBitmaps.put(oLocation, oBitmap);
			return true;
		} catch (MalformedURLException e) {
			Log.e("UrlMapLoader", "wrong UrlFormat");
		} catch (IOException e) {
			Log.e("UrlMapLoader", "stream couldn't be opened.");
		}
		return false;
	}

	@Override
	protected Bitmap getMapTileImplementation(TileLocation oLocation) {
		if(m_mapBitmaps.containsKey(oLocation))
		{
			return m_mapBitmaps.get(oLocation);
		}
		else
		{
			return null;
		}
	}
}