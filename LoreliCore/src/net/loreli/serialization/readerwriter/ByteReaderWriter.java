package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IDeSerializer;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.ISerializer;

public class ByteReaderWriter implements IObjectReaderWriter<Byte>
{

	@Override
	public void writeObject(Byte oObject, ISerializer oSerializer)
	{
		oSerializer.writeByte(oObject);		
	}

	@Override
	public Byte readObject(IDeSerializer oSerializer) throws IOException
	{
		return oSerializer.readByte();
	}

	@Override
	public int getID()
	{
		return 2;
	}

}
