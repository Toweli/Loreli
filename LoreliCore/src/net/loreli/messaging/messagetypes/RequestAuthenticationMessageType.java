package net.loreli.messaging.messagetypes;

import net.loreli.base.IDeSerializer;
import net.loreli.base.ISerializer;
import net.loreli.messaging.IMessageType;

public class RequestAuthenticationMessageType implements IMessageType
{
	@Override
	public int serialize(ISerializer oSerializer)
	{
		return 0;
	}

	@Override
	public int deserialize(IDeSerializer oDeSerializer)
	{
		return 0;
	}
}
