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
package java10;

import java.util.List;

/**
 * Type Inference Examples in Java 10
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class TypeInferenceExample {

    public static void main(String[] args) {
        // Java 10
        System.out.println("Java 10 >>>>>>--------------------------------------------------------");
        List<String> usCities = List.of("New York", "San Jose", "Chicago", "Connecticut", "New Jersey", "Denver");
        List<String> indianCities = List.of("New Delhi", "Mumbai", "Kolkata", "Chennai", "Bengaluru", "Kochi", "Pune");
        System.out.println("US Cities     > "+usCities);
        System.out.println("Indian Cities > "+indianCities);
        // Complex List
        List<List<String>> allCities1 = List.of(usCities, indianCities);
        System.out.println("All Cities 1   > "+allCities1);

        // Simplified List with Type Inference
        // Java Infers the Type at the Compile time
        // var allCities2 = List.of(usCities, indianCities);
        // You cannot assign NULL to a var
        // var is Not a Keyword
        System.out.println("Type Inference Example .... ");
        var allCities2 = List.of(usCities, indianCities);           // From the Return value of List.of(...)
        System.out.println("All Cities 2   > "+allCities2);
        allCities2.stream().forEach(System.out::println);
    }
}
