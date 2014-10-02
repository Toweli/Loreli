package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IReader;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.IWriter;

public class ShortReaderWriter implements IObjectReaderWriter<Short>
{

	@Override
	public void writeObject(Short oObject, IWriter oSerializer)
	{
		oSerializer.writeShort(oObject);
	}

	@Override
	public Short readObject(IReader oSerializer) throws IOException
	{
		return oSerializer.readShort();
	}

	@Override
	public int getID()
	{
		return 4;
	}

}
