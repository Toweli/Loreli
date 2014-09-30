package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IDeSerializer;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.ISerializer;

public class ShortReaderWriter implements IObjectReaderWriter<Short>
{

	@Override
	public void writeObject(Short oObject, ISerializer oSerializer)
	{
		oSerializer.writeShort(oObject);
	}

	@Override
	public Short readObject(IDeSerializer oSerializer) throws IOException
	{
		return oSerializer.readShort();
	}

	@Override
	public int getID()
	{
		return 4;
	}

}
