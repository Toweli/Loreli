package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IReader;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.IWriter;

public class IntegerReaderWriter implements IObjectReaderWriter<Integer>
{

	@Override
	public void writeObject(Integer oObject, IWriter oSerializer)
	{
		oSerializer.writeInt(oObject);
	}

	@Override
	public Integer readObject(IReader oSerializer) throws IOException
	{
		return oSerializer.readInt();
	}

	@Override
	public int getID()
	{
		return 5;
	}

}
