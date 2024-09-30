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
package java18;

import static java.lang.System.out;

/**
 * JEP 420: Pattern Matching for switch (Second Preview)
 *
 * Pattern matching for switch has been improved in Java 18 to allow more flexible and readable
 * code.
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class SwitchExpressionExample {

    public static void main (String[] args) {
        // Java 17
        out.println("JAVA 17 >>>>>--------------------------------------------------------");
        out.println("Pattern Matching in Java 17");
        Object o = 127;
        switch (o) {
            case String s -> out.println("String: " + s);
            case Integer i -> out.println("Integer: "+ i);
            case Long l -> out.println("Long: "+ l);
            default -> out.println("Other: "+ o);
        }
        // Java 18
        out.println("JAVA 18 >>>>>--------------------------------------------------------");
        out.println("Pattern Matching in Java 18");
        out.println(typeFinder("Hello"));
        out.println(typeFinder(127));
        out.println(typeFinder(null));
        out.println(typeFinder(3.14f));

    }

    public static String typeFinder(Object obj) {
        return switch (obj) {
            case String s -> "String: " + s;
            case Integer i -> "Integer: " + i;
            case null -> "Null value";
            default -> "Unknown type";
        };
    }
}
