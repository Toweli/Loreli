package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IReader;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.IWriter;

public class StringReaderWriter implements IObjectReaderWriter<String>
{

	@Override
	public void writeObject(String oObject, IWriter oSerializer)
	{
		oSerializer.writeString(oObject);		
	}

	@Override
	public String readObject(IReader oSerializer) throws IOException
	{
		return oSerializer.readString();
	}

	@Override
	public int getID()
	{
		return 9;
	}
}
