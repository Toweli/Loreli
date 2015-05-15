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

		oFactory.registerCreator(AuthenticateMessageType.class);
		oFactory.registerCreator(AuthenticationValidatedMessageType.class);
		oFactory.registerCreator(RegisterMessageType.class);
		oFactory.registerCreator(RegistrationValidatedMessageType.class);
		oFactory.registerCreator(RequestAuthenticationMessageType.class);
		oFactory.registerCreator(SimpleStringMessageType.class);
		oFactory.registerCreator(RequestIsAliveMessageType.class);
		oFactory.registerCreator(UpdateLocationMessageType.class);

		FactoryRegister.getInstance().registerFactory("MessageTypeFactory", oFactory);
	}
}
