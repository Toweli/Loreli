package net.loreli.eventsystem;

public interface TIEventHandler<Arguments>
{
	void handleEvent(Object m_oSender, Arguments oArguments);
}
