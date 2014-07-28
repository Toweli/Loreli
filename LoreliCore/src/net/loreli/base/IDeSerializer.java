package net.loreli.base;

import java.io.IOException;

public interface IDeSerializer
{
	int readBoolean(Ref<Boolean> bIn) throws IOException;

	int readByte(Ref<Byte> bIn) throws IOException;

	int readShort(Ref<Short> iIn) throws IOException;

	int readInt(Ref<Integer> iIn) throws IOException;

	int readLong(Ref<Long> iIn) throws IOException;

	int readFloat(Ref<Float> fIn) throws IOException;

	int readDouble(Ref<Double> dIn) throws IOException;

	int readString(Ref<String> strIn) throws IOException;

	int readSerializable(ISerializable oIn) throws IOException;
}
