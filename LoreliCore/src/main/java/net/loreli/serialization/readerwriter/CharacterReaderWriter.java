package net.loreli.serialization.readerwriter;

import java.io.IOException;

import net.loreli.serialization.IReader;
import net.loreli.serialization.IObjectReaderWriter;
import net.loreli.serialization.IWriter;

public class CharacterReaderWriter implements IObjectReaderWriter<Character>
{

	@Override
	public void writeObject(Character oObject, IWriter oSerializer)
	{
		oSerializer.writeChar(oObject);
	}

	@Override
	public Character readObject(IReader oSerializer) throws IOException
	{
		return oSerializer.readChar();
	}

	@Override
	public int getID()
	{
		return 3;
	}

}
