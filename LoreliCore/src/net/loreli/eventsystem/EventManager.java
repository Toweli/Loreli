package net.loreli.eventsystem;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import net.loreli.logging.ProgramLogSingleton;

public class EventManager
{
	private static EventManager m_oInstance = null;
	
	private BlockingQueue<Runnable> m_quEventsToHandle = new LinkedBlockingQueue<Runnable>();
	
	private EventManager()
	{
	}
	
	public static EventManager getInstance()
	{
		if(m_oInstance == null)
		{
			m_oInstance = new EventManager();
		}
		return m_oInstance;
	}
	
	public void emitEvents()
	{
		while(!m_quEventsToHandle.isEmpty())
		{
			try
			{
				m_quEventsToHandle.poll(500, TimeUnit.MILLISECONDS).run();
			}
			catch (InterruptedException e)
			{
				ProgramLogSingleton.getInstance().error("InterruptedException",
						"BlockingQueue was interrupted while polling a message.");
			}
		}
	}
	
	public void putEventEmiter(EventEmitter oEmitter)
	{
		try
		{
			m_quEventsToHandle.put(oEmitter);
		}
		catch (InterruptedException e)
		{
			ProgramLogSingleton.getInstance().error("InterruptedException",
					"BlockingQueue was interrupted while putting a message.");
		}
	}
}
