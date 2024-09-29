/**
 * Copyright (c) 2024 Araf Karsh Hamid
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * <p>
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 * <p>
 * or (per the licensee's choosing)
 * <p>
 * under the terms of the Apache 2 License version 2.0
 * as published by the Apache Software Foundation.
 */
package java18;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

import static java.lang.System.out;

/**
 * JavaExamples / ReflectionExample
 *
 * JEP 416: Reimplement Core Reflection with Method Handles
 *
 * Java 18 reimplements parts of the core reflection using MethodHandles to improve performance
 * and reduce the maintenance burden.
 *
 * @author: Araf Karsh Hamid
 * @version: 0.1
 * @date: 2024-09-29T21:29
 */
public class ReflectionExample {

    public static void main(String[] args) throws Throwable {
        // Java 18
        out.println("JAVA 18 >>>>>--------------------------------------------------------");
        oldModel();
        newModel();
    }

    /**
     * Old API Model
     * @throws Throwable
     */
    public static void oldModel() throws Throwable {
        out.println("Old Reflection Model... ");
        // Obtain the Class object for String
        Class<String> stringClass = String.class;
        // Get the method reference to `toUpperCase` with no parameters
        Method toUpperCaseMethod = stringClass.getMethod("toUpperCase");
        // Invoke the method on an instance of String
        String result = (String) toUpperCaseMethod.invoke("hello");
        System.out.println(result);  // Outputs: HELLO
    }

    /**
     * New Much Simpler high performance API
     * @throws Throwable
     */
    public static void newModel() throws Throwable {
        out.println("New Reflection Model... ");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle handle = lookup.findVirtual(String.class, "toUpperCase", MethodType.methodType(String.class));
        String result = (String) handle.invoke("hello");
        System.out.println(result);
    }
}
