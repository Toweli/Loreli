package net.loreli.factory;

import java.io.InvalidClassException;
import java.lang.reflect.Constructor;

import net.loreli.logging.ProgramLogSingleton;

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
			throw new InvalidClassException("Class " + m_oClass.getName() + "doesn't have a default ctor.");
		}
	}

	public String getObjectName()
	{
		// TODO: think about name or simpleName
		return m_oClass.getName();
	}

	public T createObject()
	{
		try
		{
			return m_oClass.getConstructor(new Class<?>[] {}).newInstance(new Object[] {});
		}
		catch (Exception e)
		{

			ProgramLogSingleton.getInstance().error("ObjectCreationError",
					"Cann't create an Object of type " + m_oClass.getName());
		}
		return null;
	}
}
