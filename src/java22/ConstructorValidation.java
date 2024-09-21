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
package java22;

import java.math.BigInteger;

import static java.lang.System.out;

/**
 * Java 22 Example
 * Constructor Validation before the super() method call.
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class ConstructorValidation {

    public static void main(String[] args) {
        // Java 22
        out.println("JAVA 22 >>>>>--------------------------------------------------------");
        out.println("Constructor Validation - Old Way with Static Code");
        testConstructorOld(127);
        testConstructorOld(-23);
        out.println("Constructor Validation - New Way: Validations before super() call");
        testConstructorNew(127);
        testConstructorNew(-23);
    }

    public static void testConstructorOld(long value) {
        try {
            PositiveIntegerOld pio = new PositiveIntegerOld(value);
            out.println("OLD: Integer Value = "+value);
        } catch (Exception e) {
            out.println("ERROR: Invalid Input = "+value);
            out.println(e.getMessage());
        }
    }

    public static void testConstructorNew(long value) {
        try {
            PositiveIntegerNew pio = new PositiveIntegerNew(value);
            out.println("NEW: Integer Value = "+value);
        } catch (Exception e) {
            out.println("ERROR: Invalid Input = "+value);
            out.println(e.getMessage());
        }
    }
}

/**
 * Old of Constructor Validation using a static method call.
 */
class PositiveIntegerOld extends BigInteger {

    public PositiveIntegerOld(long value) {
        super(String.valueOf(validateInput(value)));
    }

    /**
     * validation static method call
     * @param value
     * @return
     */
    private static long validateInput(long value) {
        if(value < 0) {
            throw new IllegalArgumentException("Input Must be a Positive Number! Input="+value);
        }
        return value;
    }
}

/**
 * Constructor Validation before the super() method call
 */
class PositiveIntegerNew extends BigInteger {

    public PositiveIntegerNew(long value) {
        if(value < 0) {
            throw new IllegalArgumentException("Input Must be a Positive Number! Input="+value);
        }
        super(String.valueOf(value));
    }
}