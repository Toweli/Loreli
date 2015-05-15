package net.loreli.mapping;

import net.loreli.location.TileLocation;
import android.graphics.Bitmap;


public class ServerMapLoader extends AbstractMapLoader
{

	protected ServerMapLoader(IMapLoader oNext) {
		super(oNext);
	}

	@Override
	protected boolean requestMapTileImplementation(TileLocation oLocation,
			IListener oListener) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Bitmap getMapTileImplementation(TileLocation oLocation) {
		// TODO Auto-generated method stub
		return null;
	}

}