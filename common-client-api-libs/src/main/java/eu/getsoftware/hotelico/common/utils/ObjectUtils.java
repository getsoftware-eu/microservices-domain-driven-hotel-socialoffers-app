package eu.getsoftware.hotelico.common.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class ObjectUtils
{
	private ObjectUtils()
	{
	}
	
	public static String join(Collection<?> collection, String separator)
	{
		return join(collection, separator, "", "");
	}
	
	public static String join(Collection<?> collection, String separator, String prefix, String suffix)
	{
		final StringBuilder builder = new StringBuilder(prefix);
		
		int count = 0;
		
		for (Object o : collection)
		{
			builder.append(o).append(separator);
			count++;
		}
		
		if (count > 0)
		{
			builder.setLength(builder.length() - separator.length());
		}
		
		return builder.append(suffix).toString();
	}
	
	/**
	 * Appends the given prefix at the the begin of the given string value until the length of the resulting string less than defined length parameter.
	 *
	 * @param value
	 *            is the initial string
	 * @param prefix
	 *            is the prefix value that is appended at the begin of value string.
	 * @param length
	 *            is a minimal wanted result length
	 * @return a string with the minimal given length and variable amount of prefixes at the beginning or the given string itself because its length was greater than the specified one.
	 * @author a.hofmann, 01.10.2013 15:57:04
	 */
	public static String preAppend(String value, String prefix, int length)
	{
		int todo = length - value.length();
		
		if (todo > 0)
		{
			final StringBuilder builder = new StringBuilder(length);
			
			while (todo-- > 0)
			{
				builder.append(prefix);
			}
			value = builder.append(value).toString();
		}
		return value;
	}
	
	public static Long[] toObject(long... array)
	{
		Long[] result = new Long[array.length];
		
		for (int i = 0; i < array.length; i++)
		{
			result[i] = array[i];
		}
		
		return result;
	}
	
	public static final Object firstNotNull(Object... objects)
	{
		for (Object o : objects)
		{
			if (o != null)
			{
				return o;
			}
		}
		
		return null;
	}
	
	public static final String firstNotEmpty(String... strings)
	{
		for (String str : strings)
		{
			if (!isEmpty(str))
			{
				return str;
			}
		}
		
		return null;
	}
	
	public static final boolean existEmpty(Object... objects)
	{
		for (Object obj : objects)
		{
			if (isEmpty(String.valueOf(obj)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Very slow method to replace german characters encoded as \\uHHHH.<br/>
	 * Ä=\u00c4, ä=\u00e4, Ö=\u00d6, ö=\u00f6, Ü=\u00dc, ü=\u00fc, ß=\u00df, €=\u20ac.
	 *
	 * @param value
	 * @return
	 * @author a.hofmann, 30.09.2013 19:31:45
	 */
	public static String replaceGermanJsonCharacters(String value)
	{
		return value.
				replaceAll("\\u00c4", "Ä").
				replaceAll("\\u00e4", "ä").
				
				replaceAll("\\u00d6", "Ö").
				replaceAll("\\u00f6", "ö").
				
				replaceAll("\\u00dc", "Ü").
				replaceAll("\\u00fc", "ü").
				
				replaceAll("\\u00df", "ß").
				replaceAll("\\u20ac", "€");
	}
	
	public static boolean equal(final Object a, final Object b)
	{
		return a == b || a != null && a.equals(b);
	}
	
	public static boolean notEqual(final Object a, final Object b)
	{
		return !equal(a, b);
	}
	
	public static boolean isBetween(final float from, final float to, final Float val)
	{
		return val != null && val >= from && val <= to;
	}
	
	public static boolean isZero(final Number val)
	{
		return val == null || val.doubleValue() == 0;
	}
	
	@SafeVarargs
	public static <T> T[] asArray(final T... elements)
	{
		return elements;
	}
	
	public static boolean isEmptyCollection(Collection<?> collection)
	{
		return collection == null || collection.isEmpty();
	}
	
	/**
	 * This method replaces all special German characters [Ä,ä,Ü,ü,Ö,ö,ß] with their two character-analogs and also [ ,\t,\n]- whitespace with an '_' - underscore.<br />
	 * ATTENTION: Please trim your input before usage of this method !
	 *
	 * @param input
	 * @return optimized string.
	 * @author a.hofmann
	 */
//	public static String replaceIllegalCharacters(String input)
//	{
//		StringBuilder builder = new StringBuilder(input.length() + 10);
//		
//		int notModified = 0;
//		
//		for (int i = 0, len = input.length(); i < len; i++)
//		{
//			char c = input.charAt(i);
//			
//			switch (c)
//			{
//				case 'Ä':
//					builder.append("Ae");
//					break;
//				case 'ä':
//					builder.append("ae");
//					break;
//				case 'Ü':
//					builder.append("Ue");
//					break;
//				case 'ü':
//					builder.append("ue");
//					break;
//				case 'Ö':
//					builder.append("Oe");
//					break;
//				case 'ö':
//					builder.append("oe");
//					break;
//				case 'ß':
//					builder.append("ss");
//					break;
//				
//				case ' ':
//				case '\t':
//				case '\n':
//					builder.append('_');
//					break;
//				
//				default:
//				{
//					builder.append(c);
//					notModified++;
//				}
//			}
//		}
//		// Spare the equal copy of the input-string.
//		if (notModified == input.length())
//		{
//			return input;
//		}
//		
//		return builder.toString();
//	}
	
	public static boolean isEmpty(String value)
	{
		return ControllerUtils.isEmptyString(value);
	}
	
	public static boolean isEmpty(Object[] value)
	{
		return value == null || value.length == 0 || value.length == 1 && value[0] == null;
	}
	
	public static boolean isNullOrLessEqualsZero(final Number value)
	{
		return value == null || value.doubleValue() <= 0;
	}
	
	public static String trim(String value)
	{
		return ControllerUtils.trimString(value);
	}
	
	public static String trimToNull(String value)
	{
		value = value != null ? trim(value) : null;
		
		if (value != null && value.isEmpty())
		{
			return null;
		}
		
		return value;
	}
	
	public static int lengthOf(String str)
	{
		return str == null ? -1 : str.length();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String toString(Map map)
	{
		final StringBuilder builder = new StringBuilder("{\n");
		
		if (map != null)
		{
			for (Iterator<Entry> iterator = map.entrySet().iterator(); iterator.hasNext();)
			{
				Entry ent = iterator.next();
				builder.append("   ").append('\"').append(ent.getKey()).append("\": ").append(ent.getValue()).append(",\n");
			}
			
			if (builder.length() > 2)
			{
				builder.setCharAt(builder.length() - 2, '\n');
				builder.setLength(builder.length() - 1);
			}
		}
		
		return builder.append("}\n").toString();
	}
	
	/**
	 * Creates map with entries, that are created by defining every <b>uneven</b> element in the arguments as <b>KEY</b> and corresponding <b>even</b> element as <b>VALUE</b>.
	 *
	 * @param objects
	 *            - pairs of objects that are going to be placed in a newly created {@link HashMap}.
	 * @return a {@link Map} with all given pairs.
	 * @throws IllegalArgumentException
	 *             if the given array has an uneven length.
	 * @author a.hofmann, 05.09.2013 21:02:29
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map asMap(Object... objects)
	{
		if ((objects.length & 1) != 0)
		{
			throw new IllegalArgumentException("The given array has an uneven length.");
		}
		
		final HashMap map = new HashMap<>();
		
		for (int i = 0; i < objects.length; i += 2)
		{
			map.put(objects[i], objects[i + 1]);
		}
		
		return map;
	}
	
	/**
	 * Creates map with entries, that are created by defining every <b>uneven</b> element in the arguments as <b>KEY</b> and corresponding <b>even</b> element as <b>VALUE</b>.
	 *
	 * @param objects
	 *            - pairs of objects that are going to be placed in a newly created {@link HashMap}.
	 * @return a {@link Map} with all given pairs.
	 * @throws IllegalArgumentException
	 *             if the given array has an uneven length.
	 * @author a.hofmann, 05.09.2013 21:02:29
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <M extends Map> M asMap(M dst, Object... objects)
	{
		if ((objects.length & 1) != 0)
		{
			throw new IllegalArgumentException("The given array has an uneven length.");
		}
		
		for (int i = 0; i < objects.length; i += 2)
		{
			dst.put(objects[i], objects[i + 1]);
		}
		
		return dst;
	}
	
	/**
	 * Creates a string-array from the given parameter, that must have the form [x,y,z]. All entries are going to be trimmed.
	 *
	 * @param input
	 *            a string in the form as regexp: [.*(,.*)]
	 * @return an array with the entries from the parameter.
	 * @author a.hofmann, 18.09.2013 13:20:24
	 */
	public static String[] toArray(String input)
	{
		return toArray(input, "[", ",", "]");
	}
	
	/**
	 * More customizable version of the method String[] asString(String input).
	 *
	 * @param array
	 * @param prefix
	 * @param delimiter
	 * @param postfix
	 * @return an array with the entries from the parameter.
	 * @author a.hofmann, 18.09.2013 13:23:28
	 */
	public static String[] toArray(String array, String prefix, String delimiter, String postfix)
	{
		if (array.startsWith(prefix))
		{
			array = array.substring(prefix.length());
		}
		if (array.endsWith(postfix))
		{
			array = array.substring(0, array.length() - postfix.length());
		}
		
		String[] split = array.split(delimiter);
		
		for (int i = 0; i < split.length; i++)
		{
			split[i] = ObjectUtils.trim(split[i]);
		}
		
		return split;
	}
}