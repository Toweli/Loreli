package net.loreli.base;

import java.io.IOException;

public interface ISerializable
{
	int serialize(ISerializer oSerializer);

	int deserialize(IDeSerializer oDeSerializer) throws IOException;
}
