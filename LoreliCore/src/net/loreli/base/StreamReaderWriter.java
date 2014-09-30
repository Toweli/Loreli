package net.loreli.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.loreli.logging.ProgramLogSingleton;
import net.loreli.serialization.IDeSerializer;
import net.loreli.serialization.ISerializable;
import net.loreli.serialization.ISerializer;
import net.loreli.serialization.ObjectSerializer;

public class StreamReaderWriter implements ISerializer, IDeSerializer
{
	private DataInputStream		m_oDataIn;
	private DataOutputStream	m_oBufferedOut;

	public StreamReaderWriter(InputStream oInputStream, OutputStream oOutputStream)
	{
		m_oDataIn = new DataInputStream(new BufferedInputStream(oInputStream));
		m_oBufferedOut = new DataOutputStream(new BufferedOutputStream(oOutputStream));
	}

	public boolean canRead()
	{
		boolean bResult = false;
		try
		{
			bResult = m_oDataIn != null && m_oDataIn.available() > 0;
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOMessage", "DataInputstream is defect");
		}
		return bResult;
	}

	public boolean canWrite()
	{
		return m_oBufferedOut != null;
	}

	private void writeBytes(byte[] aBytes)
	{
		int iTriedWrites = 0;
		boolean bWriteSuccess = false;
		while (iTriedWrites < 10 && !bWriteSuccess)
		{
			try
			{
				m_oBufferedOut.write(aBytes);
				bWriteSuccess = true;
			}
			catch (IOException e)
			{
				iTriedWrites++;
				Thread.yield();
			}
		}
		if (!bWriteSuccess)
		{
			ProgramLogSingleton.getInstance().error("IOException",
					"Cann't write a message after " + iTriedWrites + " tries.");
		}
	}

	@Override
	public boolean readBoolean() throws IOException
	{
		return m_oDataIn.readBoolean();
	}

	@Override
	public byte readByte() throws IOException
	{
		return m_oDataIn.readByte();
	}
	
	@Override
	public char readChar() throws IOException
	{
		return m_oDataIn.readChar();
	}

	@Override
	public short readShort() throws IOException
	{
		return m_oDataIn.readShort();
	}

	@Override
	public int readInt() throws IOException
	{
		return m_oDataIn.readInt();
	}

	@Override
	public long readLong() throws IOException
	{
		return m_oDataIn.readLong();
	}

	@Override
	public float readFloat() throws IOException
	{
		return m_oDataIn.readFloat();
	}

	@Override
	public double readDouble() throws IOException
	{
		return m_oDataIn.readDouble();
	}

	@Override
	public String readString() throws IOException
	{
		int iLength = m_oDataIn.readInt();
		byte[] data = new byte[iLength];
		m_oDataIn.readFully(data);
		return new String(data, "UTF-8");

	}

	@Override
	public void writeBoolean(boolean bOut)
	{
		try
		{
			m_oBufferedOut.writeBoolean(bOut);
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't write into the BufferedOutputStream.");
		}
	}

	@Override
	public void writeByte(byte bOut)
	{
		writeBytes(new byte[] { bOut });
	}

	@Override
	public void writeChar(char cOut)
	{
		try
		{
			m_oBufferedOut.writeChar(cOut);
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't write into the BufferedOutputStream.");
		}
	}
	
	@Override
	public void writeShort(short iOut)
	{
		try
		{
			m_oBufferedOut.writeShort(iOut);
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't write into the BufferedOutputStream.");
		}
	}

	@Override
	public void writeInt(int iOut)
	{
		try
		{
			m_oBufferedOut.writeInt(iOut);
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't write into the BufferedOutputStream.");
		}
	}

	@Override
	public void writeLong(long iOut)
	{
		try
		{
			m_oBufferedOut.writeLong(iOut);
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't write into the BufferedOutputStream.");
		}
	}

	@Override
	public void writeFloat(float fOut)
	{
		try
		{
			m_oBufferedOut.writeFloat(fOut);
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't write into the BufferedOutputStream.");
		}
	}

	@Override
	public void writeDouble(double dOut)
	{
		try
		{
			m_oBufferedOut.writeDouble(dOut);
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't write into the BufferedOutputStream.");
		}
	}

	@Override
	public void writeString(String strOut)
	{
		try
		{
			byte[] data = strOut.getBytes("UTF-8");
			writeInt(data.length);
			writeBytes(data);
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't write into the BufferedOutputStream.");
		}
	}

	public void flush()
	{
		try
		{
			m_oBufferedOut.flush();
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't flush the BufferedOutputStream.");
		}
	}

	@Override
	public Object readSerializable() throws IOException
	{
		return ObjectSerializer.getInstance().deserialize(this);
	}

	@Override
	public void writeSerializable(Object oSerializable)
	{
		ObjectSerializer.getInstance().serialize(oSerializable, this);
	}
}
