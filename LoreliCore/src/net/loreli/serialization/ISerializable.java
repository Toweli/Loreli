package net.loreli.serialization;

import java.io.IOException;

public interface ISerializable
{
	void serialize(IWriter oSerializer);

	void deserialize(IReader oDeSerializer) throws IOException;
}
