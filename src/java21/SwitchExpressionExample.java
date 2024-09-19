/**
 * Copyright (c) 2024 Araf Karsh Hamid

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
package java21;

import static java.lang.System.out;

/**
 * Java 14, 17 and 21 Example
 * Switch Expression Example
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class SwitchExpressionExample {

    public static void main (String[] args)  {
        // Java 14
        out.println("JAVA 14 >>>>>--------------------------------------------------------");
        out.println("Simple Switch Expressions - No Fall Through");
        out.println("< Day of the Week Old Model = "+switchOldModel(3));
        out.println("> Day of the Week New Model = "+switchNewModel(3));
        out.println("Complex Switch Expressions - No Fall Through");
        out.println("< Day of the Week Old Model = "+switchOldModel(6));
        out.println("> Day of the Week New Model = "+switchNewModel(6));
        out.println("> Type of the Day. Monday     = "+switchNewModel2("Monday"));
        out.println("> Type of the Day. Sunday      = "+switchNewModel2("Sunday"));
        // Java 17
        out.println("JAVA 17 >>>>>--------------------------------------------------------");
        switchNewModel17(127);
        switchNewModel17("127");
        // Java 21
        out.println("JAVA 21 >>>>>--------------------------------------------------------");
        out.println("Condition: "+switchNewModel21(127));
        out.println("Condition: "+switchNewModel21(90));
        out.println("Condition: "+switchNewModel21("127"));
    }

    /**
     * Old Model of Switch Statement
     * @param day
     * @return
     */
    public static String switchOldModel(int day) {
        String dayOfTheWeek = "Undefined";
        switch(day) {
            case 0:     dayOfTheWeek = "Monday"; break;
            case 1:     dayOfTheWeek = "Tuesday"; break;
            case 2:     dayOfTheWeek = "Wednesday"; break;
            case 3:     dayOfTheWeek = "Thursday"; break;
            case 4:     dayOfTheWeek = "Friday"; break;
            case 5:     dayOfTheWeek = "Saturday"; break;
            case 6:
                        System.out.println("Even God took Rest on Sunday!");
                        dayOfTheWeek = "Sunday";
                        break;
            default: throw new RuntimeException("Illegal Day of the Week argument! >> "+day);
        }
        return dayOfTheWeek;
    }

    /**
     * Switch Expressions
     * No Fall Through like in the old model if breaks are missing.
     * @param day
     * @return
     */
    public static String switchNewModel(int day) {
        return switch(day) {
            case 0 -> "Monday";
            case 1 -> "Tuesday";
            case 2 -> "Wednesday";
            case 3 -> "Thursday";
            case 4 -> "Friday";
            case 5 -> "Saturday";
            case 6 ->  {
                System.out.println("Even God took Rest on Sunday!");
                yield "Sunday";
            }
            default -> throw new RuntimeException("Illegal Day of the Week argument! >> "+day);
        };
    }

    public static String switchNewModel2(String day) {
        // Using switch expression in Java 21
        return switch (day.toUpperCase()) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            case "SATURDAY", "SUNDAY" -> "Weekend";
            default -> throw new IllegalArgumentException("Invalid day: " + day);
        };
    }

    // Java 17 Feature
    public static void switchNewModel17(Object o) {
        switch (o) {
            case Integer i -> out.println("Integer: "+ i);
            case Long l -> out.println("Long: "+ l);
            default -> out.println("Other: "+ o);
        }
    }

    // Java 21 Feature
    public static String switchNewModel21(Object o) {
        return switch (o) {
            case Integer i when i > 100 -> "Large integer";
            case Integer i -> "Small integer";
            case String s when s.length() > 5 -> "Long string";
            case String s -> "Short string";
            default -> "Unknown type";
        };
    }
}

