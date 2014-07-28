package net.loreli.factory;

import java.io.InvalidClassException;
import java.util.HashMap;

public class Factory<T> implements IFactory
{
	private HashMap<String, Creater<? extends T>>	m_mCreater; // WTF?

	public Factory(IFactory oFactory) throws ClassCastException
	{
		@SuppressWarnings("unchecked")
		Factory<T> obj = this.getClass().cast(oFactory);
		if (obj != null)
			m_mCreater = obj.m_mCreater;
	}

	public Factory()
	{
		m_mCreater = new HashMap<String, Creater<? extends T>>();
	}

	public T createObject(String strName)
	{
		return m_mCreater.get(strName).createObject();
	}

	public void registerCreater(Class<? extends T> oClass)
	{
		Creater<T> oCreater;
		try
		{
			oCreater = new Creater<T>(oClass);
			m_mCreater.put(oCreater.getObjectName(), oCreater);
		}
		catch (InvalidClassException e)
		{
			e.printStackTrace();
		}
	}
}
