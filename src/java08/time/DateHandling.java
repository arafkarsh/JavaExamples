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

import java.util.Date;

/**
 * public class Timestamp extends Date
 * 
 * A thin wrapper around java.util.Date that allows the JDBC API to identify this as an SQL TIMESTAMP value. 
 * It adds the ability to hold the SQL TIMESTAMP fractional seconds value, by allowing the specification of 
 * fractional seconds to a precision of nanoseconds. A Timestamp also provides formatting and parsing operations 
 * to support the JDBC escape syntax for timestamp values.
 * 
 * The precision of a Timestamp object is calculated to be either:
 * 
 * 19 , which is the number of characters in yyyy-mm-dd hh:mm:ss
 * 20 + s , which is the number of characters in the yyyy-mm-dd hh:mm:ss.[fff...] and s represents the scale of the 
 * given Timestamp, its fractional seconds precision.
 * 
 * Note: This type is a composite of a java.util.Date and a separate nanoseconds value. Only integral seconds are stored in 
 * the java.util.Date component. The fractional seconds - the nanos - are separate. The Timestamp.equals(Object) method never 
 * returns true when passed an object that isn't an instance of java.sql.Timestamp, because the nanos component of a date is 
 * unknown. As a result, the Timestamp.equals(Object) method is not symmetric with respect to the java.util.Date.equals(Object) 
 * method. Also, the hashCode method uses the underlying java.util.Date implementation and therefore does not include nanos in 
 * its computation.
 * 
 * Due to the differences between the Timestamp class and the java.util.Date class mentioned above, it is recommended that code 
 * not view Timestamp values generically as an instance of java.util.Date. The inheritance relationship between Timestamp and 
 * java.util.Date really denotes implementation inheritance, and not type inheritance.
 * 
 * 
 * Java's java.sql.Timestamp class is used in the JDBC API. If you need to set a date + time on a 
 * java.sql.PreparedStatement or get a date + time from a java.sql.ResultSet, you will interact 
 * with java.sql.Timestamp.
 * 
 * Actually, java.sql.Timestamp extends java.util.Date, so anything you can do with a java.util.Date 
 * you can also do with a java.sql.Timestamp. Check out java.util.Date for more details.
 * 
 * Nanoseconds
 * 
 * One difference in the java.sql.Timestamp from its superclass java.util.Date is its ability to hold the nanoseconds 
 * of a date too. You can get and set the nanoseconds using the getNanos() and setNanos().
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class DateHandling {

	public static void main(String[] args) {
		
		Date dt = new Date();
		
		long time = System.currentTimeMillis();
		
		java.sql.Timestamp timestamp1 = new java.sql.Timestamp(time);	
		java.sql.Timestamp timestamp2 = new java.sql.Timestamp(dt.getTime());

		timestamp1.setNanos(123456);
		timestamp2.setNanos(678911);	// Checkout instead 10 (last 2 digits) why did i put 11?
		
		System.out.println("Java Util Date \t\t= "+dt);
		System.out.println("Java SQL Timestamp1 \t= "+timestamp1);
		System.out.println("Java SQL Timestamp2 \t= "+timestamp2);
		
		System.out.println("Timestamp1 Nanos \t= "+timestamp1.getNanos());
		System.out.println("Timestamp2 Nanos \t= "+timestamp2.getNanos());
	}

}
