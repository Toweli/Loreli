package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IDeSerializer;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.ISerializer;

public class IntegerReaderWriter implements IObjectReaderWriter<Integer>
{

	@Override
	public void writeObject(Integer oObject, ISerializer oSerializer)
	{
		oSerializer.writeInt(oObject);
	}

	@Override
	public Integer readObject(IDeSerializer oSerializer) throws IOException
	{
		return oSerializer.readInt();
	}

	@Override
	public int getID()
	{
		return 5;
	}

}
