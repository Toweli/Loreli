package net.loreli.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.loreli.logging.ProgramLogSingleton;

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

	private int writeBytes(byte[] aBytes)
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
		return aBytes.length;
	}

	@Override
	public int readBoolean(Ref<Boolean> bIn) throws IOException
	{
		bIn.set(m_oDataIn.readBoolean());
		return 1;
	}

	@Override
	public int readByte(Ref<Byte> bIn) throws IOException
	{
		bIn.set(m_oDataIn.readByte());
		return 1;
	}

	@Override
	public int readShort(Ref<Short> iIn) throws IOException
	{
		iIn.set(m_oDataIn.readShort());
		return 2;
	}

	@Override
	public int readInt(Ref<Integer> iIn) throws IOException
	{
		iIn.set(m_oDataIn.readInt());
		return 4;
	}

	@Override
	public int readLong(Ref<Long> iIn) throws IOException
	{
		iIn.set(m_oDataIn.readLong());
		return 8;
	}

	@Override
	public int readFloat(Ref<Float> fIn) throws IOException
	{
		fIn.set(m_oDataIn.readFloat());
		return 4;
	}

	@Override
	public int readDouble(Ref<Double> dIn) throws IOException
	{
		dIn.set(m_oDataIn.readDouble());
		return 8;
	}

	@Override
	public int readString(Ref<String> strIn) throws IOException
	{
		int iLength = m_oDataIn.readInt();
		byte[] data = new byte[iLength];
		m_oDataIn.readFully(data);
		strIn.set(new String(data, "UTF-8"));
		return 4 + iLength;

	}

	@Override
	public int readSerializable(ISerializable oIn) throws IOException
	{
		return oIn.deserialize(this);
	}

	@Override
	public int writeBoolean(boolean bOut)
	{
		try
		{
			m_oBufferedOut.writeBoolean(bOut);
			return 1;
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't write into the BufferedOutputStream.");
			return 0;
		}
	}

	@Override
	public int writeByte(byte bOut)
	{
		return writeBytes(new byte[] { bOut });
	}

	@Override
	public int writeShort(short iOut)
	{
		try
		{
			m_oBufferedOut.writeShort(iOut);
			return 2;
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't write into the BufferedOutputStream.");
			return 0;
		}
	}

	@Override
	public int writeInt(int iOut)
	{
		try
		{
			m_oBufferedOut.writeInt(iOut);
			return 4;
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't write into the BufferedOutputStream.");
			return 0;
		}
	}

	@Override
	public int writeLong(long iOut)
	{
		try
		{
			m_oBufferedOut.writeLong(iOut);
			return 8;
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't write into the BufferedOutputStream.");
			return 0;
		}
	}

	@Override
	public int writeFloat(float fOut)
	{
		try
		{
			m_oBufferedOut.writeFloat(fOut);
			return 4;
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't write into the BufferedOutputStream.");
			return 0;
		}
	}

	@Override
	public int writeDouble(double dOut)
	{
		try
		{
			m_oBufferedOut.writeDouble(dOut);
			return 8;
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't write into the BufferedOutputStream.");
			return 0;
		}
	}

	@Override
	public int writeString(String strOut)
	{
		try
		{
			byte[] data = strOut.getBytes("UTF-8");
			int iResult = writeInt(data.length);
			iResult += writeBytes(data);
			return iResult;
		}
		catch (IOException e)
		{
			ProgramLogSingleton.getInstance().error("IOException", "Cann't write into the BufferedOutputStream.");
			return 0;
		}
	}

	@Override
	public int writeSerializable(ISerializable oOut)
	{
		return oOut.serialize(this);
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
}
