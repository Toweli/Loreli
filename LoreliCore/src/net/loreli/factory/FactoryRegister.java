package net.loreli.factory;

import java.util.HashMap;

public class FactoryRegister
{
	private HashMap<String, IFactory>	m_mFactories;

	private static FactoryRegister		m_oInstance;

	public static FactoryRegister getInstance()
	{
		if (m_oInstance == null)
			m_oInstance = new FactoryRegister();
		return m_oInstance;
	}

	private FactoryRegister()
	{
		m_mFactories = new HashMap<String, IFactory>();
	}

	public IFactory getFactory(String strName)
	{
		return m_mFactories.get(strName);
	}

	public void registerFactory(String strName, IFactory oFactory)
	{
		m_mFactories.put(strName, oFactory);
	}

	public boolean isRegistered(String strName)
	{
		return m_mFactories.containsKey(strName);
	}
}
