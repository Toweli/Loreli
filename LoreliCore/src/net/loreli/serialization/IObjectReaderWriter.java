package net.loreli.serialization;

import java.io.IOException;


public interface IObjectReaderWriter
{
	public void writeObject(Object oObject, ISerializer oSerializer);
	public Object readObject(IDeSerializer oSerializer) throws IOException;
	public int getID();
}
