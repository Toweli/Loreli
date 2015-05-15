package net.loreli.factory;

import java.io.InvalidClassException;
import java.util.HashMap;

import net.loreli.logging.ProgramLogSingleton;

public class Factory<T>
{
	private HashMap<String, Creater<? extends T>>	m_mCreater; // WTF?

	public Factory(Factory<T> oFactory) throws ClassCastException
	{
		m_mCreater = oFactory.m_mCreater;
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
			ProgramLogSingleton.getInstance().error("CreatorError", "Class is invalid: " + e.getMessage());
		}
	}
}
