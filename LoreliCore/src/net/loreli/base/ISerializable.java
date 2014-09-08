package net.loreli.base;

import java.io.IOException;

public interface ISerializable
{
	void serialize(ISerializer oSerializer);

	void deserialize(IDeSerializer oDeSerializer) throws IOException;
}
