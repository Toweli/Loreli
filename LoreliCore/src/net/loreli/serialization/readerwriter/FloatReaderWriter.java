package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IDeSerializer;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.ISerializer;

public class FloatReaderWriter implements IObjectReaderWriter<Float>
{

	@Override
	public void writeObject(Float oObject, ISerializer oSerializer)
	{
		oSerializer.writeFloat(oObject);
	}

	@Override
	public Float readObject(IDeSerializer oSerializer) throws IOException
	{
		return oSerializer.readFloat();
	}

	@Override
	public int getID()
	{
		return 7;
	}

}
