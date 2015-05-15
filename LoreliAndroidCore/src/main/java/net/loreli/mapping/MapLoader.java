package net.loreli.mapping;

import net.loreli.base.AbstractDecorator;
import net.loreli.location.TileLocation;
import android.graphics.Bitmap;


public class MapLoader extends AbstractDecorator<IMapLoader> implements IMapLoader {

	public MapLoader(IMapLoader oNext) {
		super(oNext);
	}

	@Override
	public void requestMapTile(TileLocation oLocation, IListener oListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Bitmap getMapTile(TileLocation oLocation) {
		// TODO Auto-generated method stub
		return null;
	}

}
