package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IDeSerializer;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.ISerializer;

public class LongReaderWriter implements IObjectReaderWriter<Long>
{

	@Override
	public void writeObject(Long oObject, ISerializer oSerializer)
	{
		oSerializer.writeLong(oObject);
	}

	@Override
	public Long readObject(IDeSerializer oSerializer) throws IOException
	{
		return oSerializer.readLong();
	}

	@Override
	public int getID()
	{
		return 6;
	}

}
