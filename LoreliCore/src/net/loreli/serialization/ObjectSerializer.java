package net.loreli.serialization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.loreli.factory.Factory;
import net.loreli.factory.FactoryRegister;

public class ObjectSerializer
{
	private static ObjectSerializer									m_oInstance	= null;

	private Map<Integer, Class<? extends IObjectReaderWriter<?>>>	m_mClassMap;

	private Factory<IObjectReaderWriter<?>>							m_oFactory;

	@SuppressWarnings("unchecked")
	private ObjectSerializer()
	{
		m_mClassMap = new HashMap<Integer, Class<? extends IObjectReaderWriter<?>>>();

		// exec generated code here for initializing the builder factory
		m_oFactory = (Factory<IObjectReaderWriter<?>>) (FactoryRegister.getInstance()
				.getFactory("ObjectReaderWriterFactory"));
	}

	public static ObjectSerializer getInstance()
	{
		if (m_oInstance == null)
		{
			m_oInstance = new ObjectSerializer();
		}
		return m_oInstance;
	}

	public <T> void serialize(T oObject, ISerializer oSerializer)
	{
		@SuppressWarnings("unchecked")
		IObjectReaderWriter<T> oReaderWriter = (IObjectReaderWriter<T>) m_oFactory.createObject(oObject.getClass()
				.getName());
		oSerializer.writeInt(oReaderWriter.getID());
		oReaderWriter.writeObject(oObject, oSerializer);
	}

	public Object deserialize(IDeSerializer oDeSerializer) throws IOException
	{
		int iID = oDeSerializer.readInt();
		IObjectReaderWriter<?> oReaderWriter = m_oFactory.createObject(m_mClassMap.get(iID).getName());
		return oReaderWriter.readObject(oDeSerializer);
	}

}
