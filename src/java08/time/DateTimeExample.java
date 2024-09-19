/**
 * Copyright (c) 2018 Araf Karsh Hamid

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.

 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation

 *   or (per the licensee's choosing)

 * under the terms of the Apache 2 License version 2.0
 * as published by the Apache Software Foundation.
 */
package java08.time;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Java 8 Date & Time Example
 * 
 * The Date-Time API uses the calendar system defined in ISO-8601 as the default calendar. 
 * This calendar is based on the Gregorian calendar system and is used globally as the defacto 
 * standard for representing date and time. The core classes in the Date-Time API have names 
 * such as LocalDateTime, ZonedDateTime, and OffsetDateTime. All of these use the ISO calendar 
 * system. If you want to use an alternative calendar system, such as Hijrah or Thai Buddhist, 
 * the java.time.chrono package allows you to use one of the predefined calendar systems.
 * 
 * http://docs.oracle.com/javase/tutorial/datetime/overview/index.html
 * http://docs.oracle.com/javase/tutorial/datetime/TOC.html
 *
 * To Download Time Zone Master Data
 * http://www.iana.org/time-zones
 * 
 * JSR 310
 * The new API specifies a number of new classes which are divided into the categories of 
 * continuous and human time. Continuous time is based on Unix time and is represented as 
 * a single incrementing number.
 * 
 * Class			Description
 * Instant			A point in time in nanoseconds from January 1st 1970
 * Duration			An amount of time measured in nanoseconds
 * 
 * Human time is based on fields that we use in our daily life such as day, hour, minute and 
 * second. It is represented by a group of classes, some of which we will discuss in this article.
 * 
 * Class			Description
 * LocalDate		a date, without time of day, offset or zone
 * LocalTime		the time of day, without date, offset or zone
 * LocalDateTime	the date and time, without offset or zone
 * OffsetDate		a date with an offset such as +02:00, without time of day or zone
 * OffsetTime		the time of day with an offset such as +02:00, without date or zone
 * OffsetDateTime	the date and time with an offset such as +02:00, without a zone
 * ZonedDateTime	the date and time with a time zone and offset
 * YearMonth		a year and month
 * MonthDay			month and day
 * DateTimeFields	stores a map of field-value pairs which may be invalid
 * Calendrical		access to the low-level API
 * Period			a descriptive amount of time, such as "2 months and 3 days" * 
 * 
 * Year/MonthOfDay/DayOfWeek/...	classes for the important fields
 * 
 * In addition to the above classes three support classes have been implemented. The Clock class wraps 
 * the current time and date, ZoneOffset is a time offset from UTC and ZoneId defines a time zone such 
 * as 'Australia/Brisbane'.
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class DateTimeExample {

	/**
	 * Testing Date & Time API
	 * 
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		
		oldAPIForDateConversion();
		
		clockSamples();
		
		dateAdjusters();
		
		calculateAge(1985, Month.FEBRUARY.getValue(), 21);
		
		dateDetails(2014, Month.FEBRUARY.getValue(), 9);
		dateDetails(2014, Month.MAY.getValue(), 15);		
		dateDetails(2014, Month.NOVEMBER.getValue(), 15);
		
		dateArithmatic();
		dateFormatting();
	}
	
	public static void clockSamples() {
		System.out.println();

		createClock(Clock.systemUTC());								// Clock with GMT / UTC Time Zone
		
		createClock(ZoneId.of("America/Los_Angeles"));				// Clock with Pacific Time Zone
		createClock(ZoneId.of("Europe/Berlin"));					// Clock with German Time Zone
		
		createClock(Clock.system(ZoneId.of("Asia/Kolkata")));		// Clock with IST Zone
		createClock(Clock.system(ZoneId.of("Australia/Sydney")));	// Clock with Australia Sydney Time Zone
		
		System.out.println();
	}
	
	public static void createClock(ZoneId zoneId) {
		createClock(Clock.system(zoneId));
	}
	
	public static void createClock(Clock clock) {	
		ZonedDateTime zLocal = ZonedDateTime.now(clock);
		
		System.out.println("Clock Time Zone       : "+zLocal.getZone());
		System.out.println("Clock TZ Difference:  : "+zLocal.getOffset().getId());
		System.out.println("Zone Time             : "+zLocal.getHour()+":"+zLocal.getMinute()+":"+zLocal.getSecond());
		System.out.println("Zoned Date Time       : "+zLocal.toString());
		System.out.println("Zoned Date Time       : "+zLocal.getYear()+"-"+zLocal.getMonthValue()+"-"+zLocal.getDayOfMonth()+" "
														  +zLocal.getHour()+":"+zLocal.getMinute()+":"+zLocal.getSecond()+"."+zLocal.getNano()+" "
														  +zLocal.getZone().getId()+" "+zLocal.getOffset().getId());		
		System.out.println("Clock Time in Millis  : "+clock.millis());
		System.out.println();

	}
	
	public static void dateAdjusters() {
		LocalDate date = LocalDate.of(2010, Month.DECEMBER, 3);
		DayOfWeek dotw = date.getDayOfWeek();
		
		System.out.printf("%s is on a %s%n", date, dotw);

		System.out.printf("First day of Month:\t %s%n",     date.with(TemporalAdjusters.firstDayOfMonth()));
		System.out.printf("Last day of Month:\t %s%n",      date.with(TemporalAdjusters.lastDayOfMonth()));
		System.out.printf("First Monday of Month:\t %s%n",  date.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
		System.out.printf("Last Friday of Month:\t %s%n",   date.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)));
		System.out.println();
		System.out.printf("1st day of next Month:\t %s%n",  date.with(TemporalAdjusters.firstDayOfNextMonth()));
		System.out.printf("First day of next Year:\t %s%n", date.with(TemporalAdjusters.firstDayOfNextYear()));
		System.out.printf("First day of Year:\t %s%n",      date.with(TemporalAdjusters.firstDayOfYear()));
	}
	
	public static void calculateAge(int year, int month, int day) {
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(year, month, day);

		
		Period p = Period.between(birthday, today);
		long p2 = ChronoUnit.DAYS.between(birthday, today);
		
		System.out.printf("\nYour Birthday : %s-%s-%s \n", year, month, day);
		System.out.println("You are " + p.getYears() + " years, " + p.getMonths() +
		                   " months, and " + p.getDays() + " days old. (" + p2 + " days total)");
		
		LocalDate nextBDay = birthday.withYear(today.getYear());

		//If your birthday has occurred this year already, add 1 to the year.
		if (nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
		    nextBDay = nextBDay.plusYears(1);
		}

		Period np = Period.between(today, nextBDay);
		long np2 = ChronoUnit.DAYS.between(today, nextBDay);
		System.out.println("There are " + np.getMonths() + " months, and " + np.getDays() 
							+ " days until your next birthday. (" + np2 + " total)");
		
		System.out.println();
	}

	public static void dateDetails(int _year, int _month, int _day) {
		LocalDate date = LocalDate.of(_year, _month, _day);
		
		// information about the month
		Month month = date.getMonth(); // 
		int monthValue = month.getValue(); //
		int minLength = month.minLength(); // 
		int maxLength = month.maxLength(); // 

		System.out.println("Date Details for the Date:\t"+date);
		System.out.printf("Year is Leap year=%s  :\t%s\n", date.isLeapYear(), date.getYear());
		System.out.println("Month of the Year        :\t"+date.getMonth());
		System.out.println("Day of the Week:\t\t"+date.getDayOfWeek().name());
		System.out.println("Day of the Month:\t\t"+date.getDayOfMonth());
		System.out.printf("Days in the Month of %s :\t%d\n", month, maxLength);
		System.out.println("Last day of the Month:\t\t"+date.with(TemporalAdjusters.lastDayOfMonth()));
		
		System.out.println("At start of the Day:\t\t"+date.atStartOfDay());
		System.out.println("Office Start Timing:\t\t"+date.atStartOfDay().plus(570, ChronoUnit.MINUTES));
		System.out.println("Office End Timing:\t\t"+date.atStartOfDay().plus(1110, ChronoUnit.MINUTES));	
		 
		// time information
		LocalTime time = LocalTime.of(15, 30);
		int hour = time.getHour(); 
		int second = time.getSecond(); 
		int minute = time.getMinute(); 
		int secondOfDay = time.toSecondOfDay(); 
		
		System.out.println("First month of the Quarter:\t"+month.firstMonthOfQuarter());
		
		System.out.println();
	}
	
	public static void dateArithmatic() {
		ZonedDateTime zDate = ZonedDateTime.now();
		
		System.out.println("Date Arithmatic:\t\t"+zDate);
		System.out.println("Date +3 days   :\t\t"+zDate.plusDays(3));
		System.out.println("Date +3.5 days :\t\t"+zDate.plusDays(3).plusHours(12));
		System.out.println("Date +3 days 2 hrs 15 mins:\t"+zDate.plusDays(3).plusHours(2).plusMinutes(15));		
		System.out.println("Date +6 days 2 hrs 35 mins:\t"+zDate.plusDays(5).plusHours(2).plusMinutes(25)+"\n");
		
		zDate = ZonedDateTime.now();
		System.out.println("Date Arithmatic:\t\t"+zDate);
		System.out.println("Date -1 days   :\t\t"+zDate.minusDays(1));
		System.out.println("Date -1.5 days :\t\t"+zDate.minusDays(1).minusHours(12));
		System.out.println("Date -1 days 2 hrs 15 mins:\t"+zDate.minusDays(1).minusHours(2).minusMinutes(15));
		
		System.out.println();
		
	}
	
	public static void dateFormatting() {
		ZonedDateTime zDate = ZonedDateTime.now();
		
		System.out.println("Date Formatting BASIC ISO DATE  = "+zDate.format(DateTimeFormatter.BASIC_ISO_DATE));
		System.out.println("Date Formatting ISO DATE TIME   = "+zDate.format(DateTimeFormatter.ISO_DATE_TIME));
		System.out.println("Date Formatting ISO ZONED DT    = "+zDate.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
		System.out.println("Date Formatting ISO WEEK DATE   = "+zDate.format(DateTimeFormatter.ISO_WEEK_DATE));
		System.out.println("Date Formatting LOCAL DATE TIME = "+zDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		System.out.println("Date Formatting ISO INSTANT     = "+zDate.format(DateTimeFormatter.ISO_INSTANT));
		
		System.out.println("Date Formatting dd/MM/yyyy      = "+zDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

		System.out.println("Date Formatting French Date     = "+zDate.format(DateTimeFormatter.ofPattern("d. MMMM yyyy", new Locale("fr"))));

	}
	
	public static void oldAPIForDateConversion() throws ParseException {
		
		
		System.out.println();
		
		System.out.println("Old API for Date Time Zone Conversion");
		// Reference : http://tutorials.jenkov.com/java-internationalization/time-zones.html 
		
		Calendar calendar = new GregorianCalendar();

		calendar.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));

		calendar.set(Calendar.HOUR_OF_DAY, 12);

		System.out.println("UTC: " + calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println("UTC: " + calendar.getTimeInMillis());

		calendar.setTimeZone(TimeZone.getTimeZone("Europe/Copenhagen"));
		System.out.println("CPH: " + calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println("CPH: " + calendar.getTimeInMillis());

		calendar.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		System.out.println("NYC: " + calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println("NYC: " + calendar.getTimeInMillis());
		
		
		// Reference : http://www.mkyong.com/java/java-convert-date-and-time-between-timezone/
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");

		String dateInString = "22-01-2015 10:15:55 AM";
		java.util.Date date = formatter.parse(dateInString);
		TimeZone tz = TimeZone.getDefault();

		// From TimeZone Asia/Singapore
		System.out.println("\nTimeZone : " + tz.getID() + " - " + tz.getDisplayName());
		System.out.println("TimeZone : " + tz);
		System.out.println("Date : " + formatter.format(date));

		// To TimeZone America/New_York
		SimpleDateFormat sdfAmerica = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
		TimeZone tzInAmerica = TimeZone.getTimeZone("UTC");
		sdfAmerica.setTimeZone(tzInAmerica);
		
		String sDateInAmerica = sdfAmerica.format(date); // Convert to String first
		java.util.Date dateInAmerica = formatter.parse(sDateInAmerica);

		System.out.println("\nTimeZone : " + tzInAmerica.getID() + 
                                      " - " + tzInAmerica.getDisplayName());
		System.out.println("TimeZone : " + tzInAmerica);
		System.out.println("Date (String) : " + sDateInAmerica);
		System.out.println("Date (Object) : " + formatter.format(dateInAmerica)); 
	}
}
