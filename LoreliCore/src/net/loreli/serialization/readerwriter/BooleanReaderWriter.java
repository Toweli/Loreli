package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IReader;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.IWriter;

public class BooleanReaderWriter implements IObjectReaderWriter<Boolean>
{

	@Override
	public void writeObject(Boolean oObject, IWriter oSerializer)
	{
		oSerializer.writeBoolean(oObject);		
	}

	@Override
	public Boolean readObject(IReader oSerializer) throws IOException
	{
		return oSerializer.readBoolean();
	}

	@Override
	public int getID()
	{
		return 1;
	}

}
