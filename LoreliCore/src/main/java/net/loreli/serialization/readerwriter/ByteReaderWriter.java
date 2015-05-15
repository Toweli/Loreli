package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IReader;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.IWriter;

public class ByteReaderWriter implements IObjectReaderWriter<Byte>
{

	@Override
	public void writeObject(Byte oObject, IWriter oSerializer)
	{
		oSerializer.writeByte(oObject);		
	}

	@Override
	public Byte readObject(IReader oSerializer) throws IOException
	{
		return oSerializer.readByte();
	}

	@Override
	public int getID()
	{
		return 2;
	}

}
