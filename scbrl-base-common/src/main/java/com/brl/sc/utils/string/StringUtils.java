package com.brl.sc.utils.string;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class StringUtils {
	
	public static final String EMPTY = "";
	public static final String BLANK = " ";
	
	public static final String[] EMPTY_STRING_ARRAY = new String[0];

	public static final Pattern KVP_PATTERN = Pattern.compile("([_.a-zA-Z0-9][-_.a-zA-Z0-9]*)[=](.*)"); //key value pair pattern.
	
	public static final Pattern INT_PATTERN = Pattern.compile("^\\d+$");

	public static String getEmpty()
	{
		return EMPTY;
	}
	
	public static String getBlank()
	{
		return BLANK;
	}
	
	public static int asInt(Object input)
	{
		return asInt(input, 0);
	}
	
	public static int asInt(Object input, int defValue)
	{
		int rs = defValue;
		try {
			String str = asString(input);
			if(input != null) rs = Integer.parseInt(str);
		} catch (NumberFormatException e) {
		}
		return rs;
	}
	
	public static boolean asBoolean(String input)
	{
		return asBoolean(input, false);
	}
	public static boolean asBoolean(String input, boolean def)
	{
		boolean rs = def;
		try {
			if(input != null)
			{
				input = input.toUpperCase();
				if(input.equalsIgnoreCase("1") || 
				   input.equalsIgnoreCase("TRUE"))
				{
					rs = true;
				} else
				{
					rs = false;
				}
			} 
		} catch (Exception e) {
		}
		return rs;
	}
	
	public static long asLong(Object input)
	{
		long rs = 0;
		try {
			String str = asString(input);
			if(input != null) rs = Long.parseLong(str);
		} catch (NumberFormatException e) {
		}
		return rs;
	}
	
	public static float asFloat(Object input)
	{
		float rs = 0;
		try {
			String str = asString(input);
			if(input != null) rs = Float.parseFloat(str);
		} catch (NumberFormatException e) {
		}
		return rs;
	}
	
	public static double asDouble(Object input)
	{
		double rs = 0;
		try {
			String str = asString(input);
			if(input != null) rs = Double.parseDouble(str);
		} catch (NumberFormatException e) {
		}
		return rs;
	}
	
	public static String asString(Object input)
	{
		return asString(input, null);
	}
	
	public static String asString(Object input, String def)
	{
		String rs = def;
		try {
			if(input != null)
			{
				if(input instanceof String)
				{
					rs = (String) input;
				} else
				{
					rs = input.toString();
				}
			}
		} catch (Exception e) {
		}
		return rs;
	}
	
	public static boolean isEmpty(String input)
	{
		if(input == null || input.length() == 0 || input.equalsIgnoreCase(EMPTY))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * split.
	 * 
	 * @param ch char.
	 * @return string array.
	 */
	public static String[] split(String str, char ch)
	{
		List<String> list = null;
        char c;
        int ix = 0,len=str.length();
		for(int i=0;i<len;i++)
		{
			c = str.charAt(i);
			if( c == ch )
			{
				if( list == null )
					list = new ArrayList<String>();
				list.add(str.substring(ix, i));
				ix = i + 1;
			}
		}
		if( ix > 0 )
			list.add(str.substring(ix));
		return list == null ? EMPTY_STRING_ARRAY : (String[])list.toArray(EMPTY_STRING_ARRAY);
	}

	/**
	 * join string.
	 * 
	 * @param array String array.
	 * @return String.
	 */
	public static String join(String[] array)
	{
		if( array.length == 0 ) return "";
		StringBuilder sb = new StringBuilder();
		for( String s : array )
			sb.append(s);
		return sb.toString();
	}

	/**
	 * join string like javascript.
	 * 
	 * @param array String array.
	 * @param split split
	 * @return String.
	 */
	public static String join(String[] array, char split)
	{
		if( array.length == 0 ) return EMPTY;
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<array.length;i++)
		{
			if( i > 0 )
				sb.append(split);
			sb.append(array[i]);
		}
		return sb.toString();
	}

	/**
	 * join string like javascript.
	 * 
	 * @param array String array.
	 * @param split split
	 * @return String.
	 */
	public static String join(String[] array, String split)
	{
		if( array.length == 0 ) return "";
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<array.length;i++)
		{
			if( i > 0 )
				sb.append(split);
			sb.append(array[i]);
		}
		return sb.toString();
	}
	
	public static String join(Collection<String> coll, String split) {
	    if(coll.isEmpty()) return EMPTY;
	    StringBuilder sb = new StringBuilder();
	    boolean isFirst = true;
	    for(String s : coll) {
	        if(isFirst) isFirst = false; else sb.append(split);
	        sb.append(s);
	    }
	    return sb.toString();
	}
	
	public static String join(String input, String split, int len)
	{
		if(len == 0) return EMPTY;
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for(int i = 0; i < len; i ++)
		{
			if(first) first = false; else sb.append(split);
			sb.append(input);
		}
		return sb.toString();
	}
	
	public static String upperFirst(String input) {
        char[] chString = input.toCharArray();
        char ch = chString[0];
        if(ch >= 97 && ch <= 122)
        {
        	chString[0] -= 32;
        	return String.valueOf(chString);
        } else
        {
        	return input;
        }
    }
	
	public static String lowerFirst(String input)
	{
		char[] chString = input.toCharArray();
        char ch = chString[0];
        if(ch >= 65 && ch <= 90)
        {
        	chString[0] += 32;
        	return String.valueOf(chString);
        } else
        {
        	return input;
        }
	}
	
	public static String listToString(List<?> list, char separator)
	{
		if (list == null) return EMPTY;
		Object[] array = list.toArray();
		int startIndex = 0;
		int endIndex = array.length;
	    int bufSize = (endIndex - startIndex);
	    if (bufSize <= 0) {
	    	return EMPTY;
	    }
	     
        bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length()) + 1);
        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
		return buf.toString();
	}


	/**
	 * 生成随机汉字
	 * @return
	 */
	public static char getRandomChinese() {
		String str = "";
		int hightPos;
		int lowPos;

		Random random = new Random();

		hightPos = (176 + Math.abs(random.nextInt(39)));
		lowPos = (161 + Math.abs(random.nextInt(93)));

		byte[] b = new byte[2];
		b[0] = (Integer.valueOf(hightPos)).byteValue();
		b[1] = (Integer.valueOf(lowPos)).byteValue();

		try {
			str = new String(b, "GB2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return str.charAt(0);
	}
	/**
	 * 生成随机英文
	 * @return
	 */
	public static char getRandomChar() {
		Random rand = new Random();
		int c=rand.nextInt(26)+'a';
		return (char)c;
	}


	public static void main(String[] args)
	{
//		String[] array = split("dsaf,dfasd", ',');
//		System.out.println(array);
		System.out.println(10%2);

	}
	
}
