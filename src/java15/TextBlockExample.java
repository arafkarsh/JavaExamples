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
package java15;

/**
 * Java 15 Example
 * Text Block Example
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class TextBlockExample {

    public static void main (String[] args)  {
        // Java 15
        System.out.println("JAVA 15 >>>>>--------------------------------------------------------");
        System.out.println("Text Block Example - with Text Formatting maintained");
        String multiLineData = """
                    This is the 1st line.
                    This is the line 2.
                        2.1 This is a sub line of Line 2
                        2.2 This is a sub line of Line 2
                    This is the line 3.
                    This is the last line.
                """;
        System.out.println("Multi-Line Text Data >> ");
        System.out.print(multiLineData);

        System.out.println("Text Block Example - Parametrized");
        String profileTemplate = """
                First Name: %s
                Last Name: %s
                Age: %d
                City: %s
                Country: %s
                """;
        System.out.println("Multi-Line Profile Template >> ");
        System.out.print(profileTemplate.formatted("Jane", "Doe", 23, "Denver", "USA"));
    }
}

