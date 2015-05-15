package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IReader;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.IWriter;

public class LongReaderWriter implements IObjectReaderWriter<Long>
{

	@Override
	public void writeObject(Long oObject, IWriter oSerializer)
	{
		oSerializer.writeLong(oObject);
	}

	@Override
	public Long readObject(IReader oSerializer) throws IOException
	{
		return oSerializer.readLong();
	}

	@Override
	public int getID()
	{
		return 6;
	}

}
