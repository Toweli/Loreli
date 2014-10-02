package net.loreli.serialization;

import java.io.IOException;


public interface IReader
{
	boolean readBoolean() throws IOException;

	byte readByte() throws IOException;
	
	char readChar() throws IOException;

	short readShort() throws IOException;

	int readInt() throws IOException;

	long readLong() throws IOException;

	float readFloat() throws IOException;

	double readDouble() throws IOException;

	String readString() throws IOException;
	
	Object readObject() throws IOException;
}
