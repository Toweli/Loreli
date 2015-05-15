package net.loreli.serialization;

import java.util.HashMap;

import net.loreli.serialization.readerwriter.BooleanReaderWriter;
import net.loreli.serialization.readerwriter.ByteReaderWriter;
import net.loreli.serialization.readerwriter.CharacterReaderWriter;
import net.loreli.serialization.readerwriter.DoubleReaderWriter;
import net.loreli.serialization.readerwriter.FloatReaderWriter;
import net.loreli.serialization.readerwriter.IntegerReaderWriter;
import net.loreli.serialization.readerwriter.LongReaderWriter;
import net.loreli.serialization.readerwriter.ShortReaderWriter;
import net.loreli.serialization.readerwriter.StringReaderWriter;

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
	
	public void initPrimitives()
	{
		registerReaderWriter(Boolean.class, new BooleanReaderWriter());
		registerReaderWriter(Byte.class, new ByteReaderWriter());
		registerReaderWriter(Character.class, new CharacterReaderWriter());
		registerReaderWriter(Short.class, new ShortReaderWriter());
		registerReaderWriter(Integer.class, new IntegerReaderWriter());
		registerReaderWriter(Long.class, new LongReaderWriter());
		registerReaderWriter(Float.class, new FloatReaderWriter());
		registerReaderWriter(Double.class, new DoubleReaderWriter());
		registerReaderWriter(String.class, new StringReaderWriter());
	}
}
