package com.java.dateformat

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows


import static java.lang.System.*;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import internal.GlobalVariable

/**
 * Demonstrates formatting dates as strings and parsing strings
 * into dates and times using pre-Java 8 (java.text.SimpleDateFormat)
 * and Java 8 (java.time.format.DateTimeFormatter) mechanisms.
 */
public class DateFormatDemo{
	/** Pattern to use for String representation of Dates/Times. */
	private final String dateTimeFormatPattern = "yyyy/MM/dd HH:mm:ss z";
	/**
	 * java.util.Date instance representing now that can
	 * be formatted using SimpleDateFormat based on my
	 * dateTimeFormatPattern field.
	 */
	private final Date now = new Date();
	/**
	 * java.time.ZonedDateTime instance representing now that can
	 * be formatted using DateTimeFormatter based on my
	 * dateTimeFormatPattern field.
	 *
	 * Note that ZonedDateTime needed to be used in this example
	 * instead of java.time.LocalDateTime or java.time.OffsetDateTime
	 * because there is zone information in the format provided by
	 * my dateTimeFormatPattern field and attempting to have
	 * DateTimeFormatter.format(TemporalAccessor) instantiated
	 * with a format pattern that includes time zone details
	 * will lead to DateTimeException for instances of
	 * TemporalAccessor that do not have time zone information
	 * (such as LocalDateTime and OffsetDateTime).
	 */
	private final java.time.ZonedDateTime zdt = java.time.ZonedDateTime.now();
	/**
	 * String that can be used by both SimpleDateFormat and
	 * DateTimeFormatter to parse respective date/time instances
	 * from this String.
	 */
	private final String dateTimeString = "2014/09/03 13:59:50 EET";
	/**
	 * Demonstrate presenting java.util.Date as String matching
	 * provided pattern via use of SimpleDateFormat.
	 */
	@Keyword
	public void demonstrateSimpleDateFormatFormatting() {
		final DateFormat format = new SimpleDateFormat(dateTimeFormatPattern);
		final String nowString = format.format(now);
		out.println(
				"Date '" + now + "' formatted with SimpleDateFormat and '"
				+ dateTimeFormatPattern + "': " + nowString);
	}

	@Keyword
	/**
	 * Demonstrate parsing a java.util.Date from a String
	 * via SimpleDateFormat.
	 */
	public void demonstrateSimpleDateFormatParsing() {
		final DateFormat format = new SimpleDateFormat(dateTimeFormatPattern);
		try {
			final Date parsedDate = format.parse(dateTimeString);
			out.println("'" + dateTimeString + "' is parsed with SimpleDateFormat as " + parsedDate);
		}
		// DateFormat.parse(String) throws a checked exception
		catch (ParseException parseException){
			out.println("ERROR: Unable to parse date/time String '"+ dateTimeString + "' with pattern '"+ dateTimeFormatPattern + "'.");
		}
	}

	@Keyword
	/**
	 * Demonstrate presenting ZonedDateTime as a String matching
	 * provided pattern via DateTimeFormatter and its
	 * ofPattern(String) method.
	 */
	public void demonstrateDateTimeFormatFormatting()
	{
		final DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern(dateTimeFormatPattern);
		final String nowString = formatter.format(zdt);
		out.println(
				zdt + " formatted with DateTimeFormatter and '"
				+ dateTimeFormatPattern + "': " + nowString);
	}

	@Keyword
	/**
	 * Demonstrate parsing ZonedDateTime from provided String
	 * via ZonedDateTime's parse(String, DateTimeFormatter) method.
	 */
	public void demonstrateDateTimeFormatParsingTemporalStaticMethod()
	{
		final DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern(dateTimeFormatPattern);
		final ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTimeString, formatter);
		out.println(
				"'" + dateTimeString
				+ "' is parsed with DateTimeFormatter and ZonedDateTime.parse as "
				+ zonedDateTime);
	}

	@Keyword
	/**
	 * Demonstrate parsing ZonedDateTime from String
	 * via DateTimeFormatter.parse(String, TemporaryQuery)
	 * with the Temple Query in this case being ZonedDateTime's
	 * from(TemporalAccessor) used as a Java 8 method reference.
	 */
	public void demonstrateDateTimeFormatParsingMethodReference()
	{
		final DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern(dateTimeFormatPattern);
		final ZonedDateTime zonedDateTime = formatter.parse(dateTimeString, ZonedDateTime.from(zdt));
		out.println(
				"'" + dateTimeString
				+ "' is parsed with DateTimeFormatter and ZoneDateTime::from as "
				+ zonedDateTime);
	}

	@Keyword
	/**
	 * Demonstrate formatting ZonedDateTime via DateTimeFormatter,
	 * but using implementation of Format.
	 */
	public void demonstrateDateTimeFormatAndFormatFormatting()
	{
		final DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern(dateTimeFormatPattern);
		final Format format = formatter.toFormat();
		final String nowString = format.format(zdt);
		out.println(
				"ZonedDateTime " + now + " formatted with DateTimeFormatter/Format (and "
				+ format.getClass().getCanonicalName() + ") and '"
				+ dateTimeFormatPattern + "': " + nowString);
	}

	@Keyword
	/**
	 * Demonstrate formatting and parsing an instance of Instant.
	 */
	public void demonstrateDateTimeFormatFormattingAndParsingInstant()
	{
		// Instant instances don't have timezone information
		final Instant instant = now.toInstant();
		final DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern(
				dateTimeFormatPattern).withZone(ZoneId.systemDefault());
		final String formattedInstance = formatter.format(instant);
		out.println(
				"Instant " + instant + " formatted with DateTimeFormatter and '"
				+ dateTimeFormatPattern + "' to '" + formattedInstance + "'");
		final Instant instant2 =
				formatter.parse(formattedInstance, ZonedDateTime.from(instant));
		out.println(formattedInstance + " parsed back to " + instant2);
	}
}