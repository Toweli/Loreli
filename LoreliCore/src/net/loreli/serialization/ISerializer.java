package net.loreli.serialization;

public interface ISerializer
{
	void writeBoolean(boolean bOut);

	void writeByte(byte bOut);

	void writeShort(short iOut);

	void writeInt(int iOut);

	void writeLong(long iOut);

	void writeFloat(float fOut);

	void writeDouble(double dOut);

	void writeString(String strOut);
	
	void writeSerializable(Object oSerializable);
}
