package net.loreli.factory;

import java.io.InvalidClassException;
import java.lang.reflect.Constructor;

public class Creater<T>
{
	Class<? extends T>	m_oClass;

	public Creater(Class<? extends T> oClass) throws InvalidClassException
	{
		m_oClass = oClass;
		boolean bClassIsValid = false;
		for (Constructor<?> cTor : m_oClass.getConstructors())
		{
			if (cTor.getParameterTypes().length == 0)
			{
				bClassIsValid = true;
			}
		}
		if (!bClassIsValid)
		{
			throw new InvalidClassException("Class doesn't have a default ctor.");
		}
	}

	public String getObjectName()
	{
		return m_oClass.getSimpleName();
	}

	public T createObject()
	{
		try
		{
			return m_oClass.getConstructor(new Class<?>[] {}).newInstance(new Object[] {});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
