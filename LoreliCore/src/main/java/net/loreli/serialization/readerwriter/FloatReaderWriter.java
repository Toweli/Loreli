package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IReader;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.IWriter;

public class FloatReaderWriter implements IObjectReaderWriter<Float>
{

	@Override
	public void writeObject(Float oObject, IWriter oSerializer)
	{
		oSerializer.writeFloat(oObject);
	}

	@Override
	public Float readObject(IReader oSerializer) throws IOException
	{
		return oSerializer.readFloat();
	}

	@Override
	public int getID()
	{
		return 7;
	}

}
