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

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class PredicateExample {

    public static void main (String[] args) {
        // Create a Fibonacci Number Series
        List<Integer> fibonacci = List.of(1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181);
        System.out.println("Fibonacci Series = "+fibonacci);
        System.out.println("Print Even Fibonacci Numbers  - Test 1");
        Predicate<Integer> evenPredicate = number -> number%2==0;
        String result = fibonacci.stream()                           // Stream Fibonacci Series
                .filter(evenPredicate)                                  // filter Even Numbers
                .map(String::valueOf)                                   // Convert each Integer to String
                .collect(Collectors.joining(", "));         // Join with comma and space
        System.out.print(result);
        System.out.println("\nPrint Odd Fibonacci Numbers  - Test 2");
        String result2 = fibonacci.stream()                           //  Stream Fibonacci Series
                .filter(evenPredicate.negate())                        // Filter Odd Numbers
                .map(String::valueOf)                                   // Convert each Integer to String
                .collect(Collectors.joining(", "));          // Join with comma and space
        System.out.print(result2);
        System.out.println("\nPrint Even Fibonacci Numbers  - Test 3");
        String result3 = fibonacci.stream()                           //  Stream Fibonacci Series
                .filter(PredicateExample::isEven)                      // Filter Odd Numbers
                .map(String::valueOf)                                   // Convert each Integer to String
                .collect(Collectors.joining(", "));          // Join with comma and space
        System.out.print(result3);
        System.out.println("\nPrint Odd Fibonacci Numbers  - Test 4 >> Predicate.Not - New Feature in Java 11");
        String result4 = fibonacci.stream()                           //  Stream Fibonacci Series
                .filter(Predicate.not(PredicateExample::isEven))    // Filter Odd Numbers
                .map(String::valueOf)                                   // Convert each Integer to String
                .collect(Collectors.joining(", "));          // Join with comma and space
        System.out.print(result4);
    }

    /**
     * Checks if the Number is Even
     * @param number
     * @return
     */
    public static boolean isEven(Integer number) {
        return number%2==0;
    }
}


