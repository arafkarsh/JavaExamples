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
package java16;

/**
 * Java 16 Examples
 * Record Example - To Eliminate the verbosity in Creating Java Beans
 * - Public Accessor Methods
 * - Constructor
 * - Equals
 * - Hashcode
 * - toString
 * are automatically created
 * You can have custom implementations of the above methods..
 * Compact Constructors are allowed only in Records.
 * You cant add Instance Variables or Instance Initializers
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class RecordExample {

    record User(String firstName, String lastName, String email, String phone, String dept) {}

    public static void main (String[] args) {
        // Java 16
        System.out.println("JAVA 16 >>>>>--------------------------------------------------------");
        System.out.println("Record Example. ");
        User adminUser = new User("Jane", "Doe", "jane.doe@g.com", "111-222-3456", "Engineering");
        System.out.println("Admin User =  "+adminUser.firstName() + " " + adminUser.lastName());
        System.out.println("Admin User =  "+adminUser);
    }
}
