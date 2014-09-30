package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IDeSerializer;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.ISerializer;

public class StringReaderWriter implements IObjectReaderWriter<String>
{

	@Override
	public void writeObject(String oObject, ISerializer oSerializer)
	{
		oSerializer.writeString(oObject);		
	}

	@Override
	public String readObject(IDeSerializer oSerializer) throws IOException
	{
		return oSerializer.readString();
	}

	@Override
	public int getID()
	{
		return 9;
	}
}
