package net.loreli.eventsystem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.loreli.logging.ProgramLogSingleton;

public class TEventHandler<Arguments>
{
	private Object	m_oHandler;
	private String	m_strMethod;

	public TEventHandler(Object oHandler, String strMethod)
	{
		m_oHandler = oHandler;
		m_strMethod = strMethod;
	}
	
	public Object getHandler()
	{
		return m_oHandler;
	}
	
	public String getMethodName()
	{
		return m_strMethod;
	}

	public void handleEvent(Object oSender, Arguments oArguments)
	{
		try
		{
			Method oMethod = m_oHandler.getClass().getMethod(m_strMethod, Object.class, oArguments.getClass());
			oMethod.invoke(m_oHandler, new Object[]{oSender, oArguments});
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
		catch (NoSuchMethodException e)
		{
			ProgramLogSingleton.getInstance().error("NoSuchMethodException", "Method doesn't exist. (Maybe it's not accessable(public).)");
		}
		catch (SecurityException e)
		{
			ProgramLogSingleton.getInstance().error("SecurityException", "SecurityException");
		}
	}
	
	@Override
	public boolean equals(Object oOther)
	{
		if(oOther instanceof TEventHandler<?>)
		{
			TEventHandler<?> oOtherHandler = (TEventHandler<?>) oOther;
			return oOtherHandler.m_oHandler.equals(m_oHandler) && oOtherHandler.m_strMethod.equals(m_strMethod);
		}
		else
		{
			return false;
		}
	}
}
