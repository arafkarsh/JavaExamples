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
package java11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Java 11 Example
 * String Utils Example
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class StringUtilsExample {

    public static void main (String[] args)  {
        // Java 11
        System.out.println("JAVA 11 >>>>>--------------------------------------------------------");
        System.out.println(">> String Utils Blank Test --------------------------------------------");
        String emptyString = " ";
        System.out.println("String Var emptyString=["+emptyString+"] isBlank = "+emptyString.isBlank());

        System.out.println(">> String Utils Strip Test --------------------------------------------");
        String planet = " Saturn ";
        System.out.println("String var planet=["+planet+"]  After Strip planet=["+planet.strip()+"]");
        System.out.println("String var planet=["+planet+"]  After Strip planet=["+planet.stripLeading()+"] >> Leading");
        System.out.println("String var planet=["+planet+"]  After Strip planet=["+planet.stripTrailing()+"] >> Trailing");

        System.out.println(">> String Utils Lines Test --------------------------------------------");
        String multiLineData = "This is the 1st Line.\nThis is Line 2.\nThis is Line 3.\nThis is Line 4.\nThis is the last Line.\n";
        multiLineData.lines().forEach(System.out::println);
    }
}
