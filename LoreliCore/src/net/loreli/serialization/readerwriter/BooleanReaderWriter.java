package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IDeSerializer;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.ISerializer;

public class BooleanReaderWriter implements IObjectReaderWriter<Boolean>
{

	@Override
	public void writeObject(Boolean oObject, ISerializer oSerializer)
	{
		oSerializer.writeBoolean(oObject);		
	}

	@Override
	public Boolean readObject(IDeSerializer oSerializer) throws IOException
	{
		return oSerializer.readBoolean();
	}

	@Override
	public int getID()
	{
		return 1;
	}

}
