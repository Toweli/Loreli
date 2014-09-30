package net.loreli.serialization;

import java.util.HashMap;

public class ReaderWriterRegistry
{
	private HashMap<Class<?>, IObjectReaderWriter<?>> m_mapReaderWiter;
	
	private static ReaderWriterRegistry M_OINSTANCE = null;
	
	private ReaderWriterRegistry()
	{
		m_mapReaderWiter = new HashMap<>();
	}
	
	public static ReaderWriterRegistry getInstance()
	{
		if(M_OINSTANCE == null)
		{
			M_OINSTANCE = new ReaderWriterRegistry();
		}
		return M_OINSTANCE;
	}
	
	public <T> void registerReaderWriter(Class<T> oClass, IObjectReaderWriter<T> oReaderWriter)
	{
		m_mapReaderWiter.put(oClass, oReaderWriter);
	}
	
	@SuppressWarnings("unchecked")
	public <T> IObjectReaderWriter<T> getReaderWriter(Class<T> oClass)
	{
		return (IObjectReaderWriter<T>) m_mapReaderWiter.get(oClass);
	}
}
