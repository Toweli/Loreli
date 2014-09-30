package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IDeSerializer;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.ISerializer;

public class CharacterReaderWriter implements IObjectReaderWriter<Character>
{

	@Override
	public void writeObject(Character oObject, ISerializer oSerializer)
	{
		oSerializer.writeChar(oObject);
	}

	@Override
	public Character readObject(IDeSerializer oSerializer) throws IOException
	{
		return oSerializer.readChar();
	}

	@Override
	public int getID()
	{
		return 3;
	}

}
