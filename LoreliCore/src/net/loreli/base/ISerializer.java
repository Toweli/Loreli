package net.loreli.base;

public interface ISerializer
{
	int writeBoolean(boolean bOut);

	int writeByte(byte bOut);

	int writeShort(short iOut);

	int writeInt(int iOut);

	int writeLong(long iOut);

	int writeFloat(float fOut);

	int writeDouble(double dOut);

	int writeString(String strOut);

	int writeSerializable(ISerializable oOut);
}
