package net.loreli.eventsystem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.loreli.logging.ProgramLogSingleton;

public class TEventHandler<Arguments>
{
	private Object	m_oHandler;
	private Method	m_oMethod;

	public TEventHandler(Object oHandler, Method oMethod)
	{
		m_oHandler = oHandler;
		m_oMethod = oMethod;
		if(!m_oMethod.isAccessible() || m_oMethod.getGenericParameterTypes().length!=1)
		{
			ProgramLogSingleton.getInstance().error("MethodError", "Wrong method.");
		}
	}

	public void handleEvent(Arguments oArguments)
	{
		try
		{
			m_oMethod.invoke(m_oHandler, new Object[]{oArguments});
		}
		catch (IllegalAccessException e)
		{
			ProgramLogSingleton.getInstance().error("IllegalAccessException", "cann't invoke method");
		}
		catch (IllegalArgumentException e)
		{
			ProgramLogSingleton.getInstance().error("IllegalArgumentException", "wrong arguments for this method");
		}
		catch (InvocationTargetException e)
		{
			ProgramLogSingleton.getInstance().error("InvocationTargetException", "object doesn't have the method.");
		}
	}
}
