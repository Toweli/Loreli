package net.loreli.base;

public class Ref<T>
{
	private T	m_tValue;

	public void set(T tValue)
	{
		m_tValue = tValue;
	}

	public T get()
	{
		return m_tValue;
	}

	@Override
	public String toString()
	{
		return m_tValue.toString();
	}

	@Override
	public boolean equals(Object obj)
	{
		return m_tValue.equals(obj);
	}

	@Override
	public int hashCode()
	{
		return m_tValue.hashCode();
	}
}
