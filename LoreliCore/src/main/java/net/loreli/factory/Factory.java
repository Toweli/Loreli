package net.loreli.factory;

import java.io.InvalidClassException;
import java.util.HashMap;

import net.loreli.logging.ProgramLogSingleton;

public class Factory<T>
{
	HashMap<String, Creator<? extends T>> m_mCreator;

	public Factory(Factory<T> oFactory) throws ClassCastException
	{
		m_mCreator = oFactory.m_mCreator;
	}

	public Factory()
	{
		m_mCreator = new HashMap<String, Creator<? extends T>>();
	}

	public T createObject(String strName)
	{
		return m_mCreator.get(strName).createObject();
	}

	public void registerCreator(Class<? extends T> oClass)
	{
		Creator<T> oCreator;
		try
		{
			oCreator = new Creator<T>(oClass);
			m_mCreator.put(oCreator.getObjectName(), oCreator);
		}
		catch (InvalidClassException e)
		{
			ProgramLogSingleton.getInstance().error("CreatorError", "Class is invalid: " + e.getMessage());
		}
	}
}
