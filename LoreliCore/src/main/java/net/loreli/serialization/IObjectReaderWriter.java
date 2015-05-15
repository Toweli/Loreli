package net.loreli.serialization;

import java.io.IOException;


public interface IObjectReaderWriter<T>
{
	public void writeObject(T oObject, IWriter oSerializer);
	public T readObject(IReader oSerializer) throws IOException;
	public int getID();
}
