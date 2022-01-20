package de.hotelico.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailValidator
{
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public MailValidator()
	{
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	/**
	 * Validate string with regular expression
	 * 
	 * @param string
	 *            string for validation
	 * @return true valid e-mail address, false invalid e-mail address
	 */
	public boolean validate(final String string)
	{
		matcher = pattern.matcher(string);
		return matcher.matches();
	}
}