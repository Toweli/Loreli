package net.loreli.mapping;




import net.loreli.location.TileLocation;
import android.graphics.Bitmap;



/**
 * interface for loading mapTiles (Images)
 * @author Towel
 * 
 */
public interface IMapLoader {
	/**
	 * Listener of IMapLoader to get response when a tile is loaded or couldn't be loaded.
	 * @author Towel
	 * 
	 */
	interface IListener
	{
		/**
		 * Called when a tile is loaded.
		 * You can use {@link IMapLoader#getMapTile(TileLocation)} when this method is called.
		 * @param oLocation The location of the tile that was loaded.
		 */
		void onMapLoaded(TileLocation oLocation);
		/**
		 * Called when a tile couldn't be loaded. (Something goes wrong)
		 * @param oLocation The location of the tile that couldn't be loaded.
		 */
		void onMapLoadedFailed(TileLocation oLocation);
	}
	
	/**
	 * This method loads a map-tile of the specified location. When the tile is loaded {@link IListener.onMapLoaded(TileLocation)} of the listener is called.
	 * When the tile couldn't be loaded {@link IListener.onMapLoadedFailed(TileLocation)} of the listener is called instead.
	 * Tiles should be loaded in separate threads.
	 * @param oLocation The location of the map, that should be loaded.
	 * @param oListener The listener that get the response of the loading event
	 */
	void requestMapTile(TileLocation oLocation, IListener oListener);
	/**
	 * This is a method to get loaded tile-image-data of a specific tile-location.
	 * @param oLocation The location of the tile.
	 * @return The tile-image of the specified location if possible, <b>null</b> otherwise. See {@link Image}.
	 */
	Bitmap getMapTile(TileLocation oLocation);
	
}
