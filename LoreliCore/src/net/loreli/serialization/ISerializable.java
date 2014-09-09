package net.loreli.serialization;

import java.io.IOException;

public interface ISerializable
{
	void serialize(ISerializer oSerializer);

	void deserialize(IDeSerializer oDeSerializer) throws IOException;
}
