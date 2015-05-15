package net.loreli.messaging;

import net.loreli.factory.Factory;
import net.loreli.factory.FactoryRegister;
import net.loreli.logging.ProgramLogSingleton;
import net.loreli.messaging.messagetypes.AuthenticateMessageType;
import net.loreli.messaging.messagetypes.AuthenticationValidatedMessageType;
import net.loreli.messaging.messagetypes.RegisterMessageType;
import net.loreli.messaging.messagetypes.RegistrationValidatedMessageType;
import net.loreli.messaging.messagetypes.RequestAuthenticationMessageType;
import net.loreli.messaging.messagetypes.RequestIsAliveMessageType;
import net.loreli.messaging.messagetypes.SimpleStringMessageType;
import net.loreli.messaging.messagetypes.UpdateLocationMessageType;

public class MessageTypeFactoryInitializer
{
	public static void initMessageTypeFactory()
	{
		if (FactoryRegister.getInstance().isRegistered("MessageTypeFactory"))
		{
			ProgramLogSingleton.getInstance().warning("The MessageTypeFactory is already initialized", 5);
			return;
		}
		Factory<IMessageType> oFactory = new Factory<IMessageType>();

		oFactory.registerCreater(AuthenticateMessageType.class);
		oFactory.registerCreater(AuthenticationValidatedMessageType.class);
		oFactory.registerCreater(RegisterMessageType.class);
		oFactory.registerCreater(RegistrationValidatedMessageType.class);
		oFactory.registerCreater(RequestAuthenticationMessageType.class);
		oFactory.registerCreater(SimpleStringMessageType.class);
		oFactory.registerCreater(RequestIsAliveMessageType.class);
		oFactory.registerCreater(UpdateLocationMessageType.class);

		FactoryRegister.getInstance().registerFactory("MessageTypeFactory", oFactory);
	}
}
