package net.loreli.serialization;

import java.io.IOException;


public interface IObjectReaderWriter<T>
{
	public void writeObject(T oObject, ISerializer oSerializer);
	public T readObject(IDeSerializer oSerializer) throws IOException;
	public int getID();
}
