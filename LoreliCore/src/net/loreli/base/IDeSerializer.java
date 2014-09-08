package net.loreli.base;

import java.io.IOException;


public interface IDeSerializer
{
	boolean readBoolean() throws IOException;

	byte readByte() throws IOException;

	short readShort() throws IOException;

	int readInt() throws IOException;

	long readLong() throws IOException;

	float readFloat() throws IOException;

	double readDouble() throws IOException;

	String readString() throws IOException;
	
	Object readSerializable() throws IOException;
}
