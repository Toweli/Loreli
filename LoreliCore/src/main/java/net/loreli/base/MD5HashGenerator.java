package net.loreli.base;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashGenerator
{
	// TODO: validate this magic stuff
	public static String getMD5Hash(String oText)
	{
		MessageDigest m;
		try
		{
			m = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e)
		{
			return null;
		}
		m.reset();
		m.update(oText.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String strHashText = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while (strHashText.length() < 32)
		{
            strHashText = "0" + strHashText;
		}
		return strHashText;
	}
}
