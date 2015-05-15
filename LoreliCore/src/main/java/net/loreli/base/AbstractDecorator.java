package net.loreli.base;

public abstract class AbstractDecorator<T>
{
	private T	m_oNext;

	protected AbstractDecorator(T oNext)
	{
		m_oNext = oNext;
	}

	protected T getDecorator()
	{
		return m_oNext;
	}

}
