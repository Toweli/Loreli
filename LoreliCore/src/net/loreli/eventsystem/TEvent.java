package net.loreli.eventsystem;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.*;
import java.util.ArrayList;

public class TEvent<Arguments>
{
	private ArrayList<TEventHandler<Arguments>> m_liEventHandler;
	private Object m_oSender;
	
	public TEvent(Object oSender)
	{
		m_oSender = oSender;
	}
	
	public void raise(Arguments oArguments)
	{
		for(TEventHandler<Arguments> oEventHandler : m_liEventHandler)
		{
			oEventHandler.handleEvent(oArguments);
		}
	}
	
	public void addHandler(Object oHandler, String strMethodName)
	{
//		try
//		{
//		System.out.println(getClass().getTypeParameters()[0]);
//		System.out.println(getClass());
//		Type clazz = ((Type)getClass());
//		ParameterizedType type = (ParameterizedType)clazz;
//		System.out.println(type.getActualTypeArguments()[0]);

//	        System.out.println(((ParameterizedType) aListWithSomeType
//	            .getClass()
//	            .getGenericSuperclass()).getActualTypeArguments()[0]);
			
			
//			m_liEventHandler.add(new TEventHandler<Arguments>(oHandler, oHandler.getClass().getMethod(strMethodName, (Class<?>)(getClass().getTypeParameters()[0]) )));
//		}
//		catch (NoSuchMethodException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		catch (SecurityException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void removeHandler(TEventHandler<Arguments> oHandler)
	{
		m_liEventHandler.remove(oHandler);
	}
}
