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

import java.net.InetAddress;

import static java.lang.System.out;

/**
 * JavaExamples / CustomDNSResolverExample 
 *
 * @author: Araf Karsh Hamid
 * @version: 0.1
 * @date: 2024-09-30T07:54
 */
public class CustomDNSResolverExample {

    public static void main(String[] args) {
        // Java 18
        out.println("JAVA 18 >>>>>--------------------------------------------------------");
        out.println("Custom DNS Resolver Example ....  (Testing done under Java 23)");
        try {
            // Test resolving a domain ending in ".fusion"
            InetAddress testDomainAddress = InetAddress.getByName("myapp.fusion");
            System.out.println("Resolved INTERNAL address for myapp.fusion: " + testDomainAddress);
        } catch (Exception e) {
            System.out.println("Host Not Found! : "+e.getMessage());
        }
        try {
            // Test resolving a normal domain (e.g., google.com)
            InetAddress normalDomainAddress = InetAddress.getByName("google.com");
            System.out.println("Resolved EXTERNAL address for google.com: " + normalDomainAddress);
        } catch (Exception e) {
            System.out.println("Host Not Found! : "+e.getMessage());        }
    }
}
