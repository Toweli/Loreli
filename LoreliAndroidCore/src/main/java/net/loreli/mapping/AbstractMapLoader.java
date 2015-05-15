package net.loreli.mapping;

import java.util.ArrayList;
import java.util.HashMap;

import net.loreli.base.AbstractDecorator;
import net.loreli.location.TileLocation;

import android.graphics.Bitmap;
import android.os.AsyncTask;


// TODO: think about synchronizing the map of the listener
public abstract class AbstractMapLoader extends AbstractDecorator<IMapLoader> implements IMapLoader, IMapLoader.IListener{

	HashMap<TileLocation, ArrayList<IMapLoader.IListener> > m_mapLoadingTiles;
	
	protected AbstractMapLoader(IMapLoader oNext) {
		super(oNext);
	}

	/**
	 * Specification of the requestMapTile-method. This method is executed in a separate Thread
	 * @param oLocation The location of which you want to request the image-data
	 * @param oListener The Listener which have to be notified when the data is loaded
	 * @return true if the data could be loaded, false otherwise
	 */
	protected abstract boolean requestMapTileImplementation(TileLocation oLocation, IListener oListener);
	
	protected abstract Bitmap getMapTileImplementation(TileLocation oLocation);

    /**
     * Specification of the getMapTile-method. this method is directly passed through
     * @param oLocation The location of which you want to request the image-data
     * @param oListener The Listener which have to be notified when the data is loaded
     * @return true if the data could be loaded, false otherwise
     */
	public final void requestMapTile(TileLocation oLocation, IListener oListener)
	{
		TileLocation oRounded = oLocation.roundToBottom();
		if(!m_mapLoadingTiles.containsKey(oRounded))
		{
			m_mapLoadingTiles.put(oRounded, new ArrayList<IMapLoader.IListener>());
			m_mapLoadingTiles.get(oRounded).add(oListener);
			new AsyncTask<TileLocation, Void, Void>()
			{
				@Override
				protected Void doInBackground(TileLocation... params) {
					if(!requestMapTileImplementation(params[0],AbstractMapLoader.this))
					{
						if(getDecorator()!=null)
						{
							getDecorator().requestMapTile(params[0],AbstractMapLoader.this);
						}
						else
						{
							notifyFailed(params[0]);
						}
					}
					else
					{
						notifySuccessed(params[0]);
					}
					return null;
				}
			}.execute(oRounded);
		}
		else
		{
			m_mapLoadingTiles.get(oRounded).add(oListener);
		}
	}

	public final Bitmap getMapTile(TileLocation oLocation)
	{
		TileLocation oRoundedLocation = oLocation.roundToBottom();
		Bitmap oResult = getMapTileImplementation(oRoundedLocation);
		if(oResult == null && getDecorator()!=null)
		{
			oResult = getDecorator().getMapTile(oRoundedLocation);
		}
		return oResult;
	}
	
	public void onMapLoaded(TileLocation oLocation)
	{
		notifySuccessed(oLocation);
	}

	public void onMapLoadedFailed(TileLocation oLocation)
	{
		notifyFailed(oLocation);
	}
	
	protected void notifySuccessed(TileLocation oLocation)
	{
		ArrayList<IMapLoader.IListener> liListener = m_mapLoadingTiles.get(oLocation);
		for(IMapLoader.IListener oListener : liListener)
		{
			oListener.onMapLoaded(oLocation);
		}
	}
	
	protected void notifyFailed(TileLocation oLocation)
	{
		ArrayList<IMapLoader.IListener> liListener = m_mapLoadingTiles.get(oLocation);
		for(IMapLoader.IListener oListener : liListener)
		{
			oListener.onMapLoadedFailed(oLocation);
		}
	}
}
