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

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 */
public class DateTimeExample2 {

	public static void main (String[] args) {
		
		example1();
		example2();
	}
	
	/**
	 * How to create an OffsetDateTime object in Java
	 */
	public static void example1() {
	      // Current date time with an offset
	      OffsetDateTime dateTime = OffsetDateTime.now();
	      System.out.println(dateTime);

	      // Current date time from specified clock with an offset
	      OffsetDateTime dateTime2 = OffsetDateTime.now(Clock.systemDefaultZone());
	      System.out.println(dateTime2);

	      // Current date time from specified time-zone with an offset
	      OffsetDateTime dateTime3 = OffsetDateTime.now(ZoneId.systemDefault());
	      System.out.println(dateTime3);

	      // Specific date time from LocalDateTime with an offset
	      OffsetDateTime dateTime4 = OffsetDateTime.of(LocalDateTime.of(2017, 05, 12, 05, 45),
	            ZoneOffset.ofHoursMinutes(6, 30));
	      System.out.println(dateTime4);
	}
	
	/**
	 * How to get LocalDateTime, LocalDate, LocalTime, ZonedDateTime, 
	 * Instant and OffsetTime from OffsetDateTime
	 */
	public static void example2() {
	      // Current date time with an offset
	      OffsetDateTime dateTime = OffsetDateTime.now();
	      System.out.println(dateTime);

	      // To LocalDateTime
	      LocalDateTime localDateTime = dateTime.toLocalDateTime();
	      System.out.println(localDateTime);

	      // To LocalDate
	      LocalDate localDate = dateTime.toLocalDate();
	      System.out.println(localDate);

	      // To LocalTime
	      LocalTime localTime = dateTime.toLocalTime();
	      System.out.println(localTime);

	      // To ZonedDateTime
	      ZonedDateTime zonedDateTime = dateTime.toZonedDateTime();
	      System.out.println(zonedDateTime);

	      // To Instant
	      Instant instant=dateTime.toInstant();
	      System.out.println(instant);
	      
	      // To OffsetTime
	      OffsetTime offsetTime=dateTime.toOffsetTime();
	      System.out.println(offsetTime);
	}
	
	/**
	 * How to convert or parse String to OffsetDateTime in java
	 */
	public static void example3() {
	      // Parsing ISO offset date time
	      OffsetDateTime dateTime = OffsetDateTime.parse("2017-07-07T21:36:10.598+05:30",
	            DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	      System.out.println(dateTime);

	      // Parsing 'yyyy-MMM-dd HH:mm:ss Z' pattern
	      OffsetDateTime dateTime1 = OffsetDateTime.parse("2017-May-02 23:35:05 +0530",
	            DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss Z"));
	      System.out.println(dateTime1);

	      // Parsing 'yyyy-MM-dd KK:mm:ss a x' pattern
	      OffsetDateTime dateTime2 = OffsetDateTime.parse("2017-05-30 10:20:30 AM +0530",
	            DateTimeFormatter.ofPattern("yyyy-MM-dd KK:mm:ss a x"));
	      System.out.println(dateTime2);

	      // Formatting 'cccc, MMMM dd, yyyy KK:mm a X' pattern
	      OffsetDateTime dateTime3 = OffsetDateTime.parse("Wednesday, May 31, 2017 10:21 PM +0530",
	            DateTimeFormatter.ofPattern("cccc, MMMM dd, yyyy KK:mm a X"));
	      System.out.println(dateTime3);
	}
}
