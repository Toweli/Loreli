package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IDeSerializer;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.ISerializer;

public class DoubleReaderWriter implements IObjectReaderWriter<Double>
{

	@Override
	public void writeObject(Double oObject, ISerializer oSerializer)
	{
		oSerializer.writeDouble(oObject);
	}

	@Override
	public Double readObject(IDeSerializer oSerializer) throws IOException
	{
		return oSerializer.readDouble();
	}

	@Override
	public int getID()
	{
		return 0;
	}

}
