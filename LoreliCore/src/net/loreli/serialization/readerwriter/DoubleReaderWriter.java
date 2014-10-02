package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IReader;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.IWriter;

public class DoubleReaderWriter implements IObjectReaderWriter<Double>
{

	@Override
	public void writeObject(Double oObject, IWriter oSerializer)
	{
		oSerializer.writeDouble(oObject);
	}

	@Override
	public Double readObject(IReader oSerializer) throws IOException
	{
		return oSerializer.readDouble();
	}

	@Override
	public int getID()
	{
		return 8;
	}

}
