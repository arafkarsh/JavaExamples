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

    // Record with Attributes Definition ONLY
    record User(String firstName, String lastName, String email, String phone, String dept) {}

    // Record with Custom Constructor for Field Validations
    record User2(String firstName, String lastName, String email, String phone, String dept) {
        User2(String firstName, String lastName, String email, String phone, String dept) {
            if(firstName == null || lastName == null) {
                throw new IllegalArgumentException("Name should not be NULL! First Name = "+firstName+" Last Name = "+lastName);
            }
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
            this.dept = dept;
        }
    }

    // Record with Compact Constructor - ONLY Validations are written
    record User3(String firstName, String lastName, String email, String phone, String dept) {
        User3 {
            if(firstName == null || lastName == null) {
                throw new IllegalArgumentException("Name should not be NULL! First Name = "+firstName+" Last Name = "+lastName);
            }
        }
    }

    public static void main (String[] args) {
        // Java 16
        System.out.println("JAVA 16 >>>>>--------------------------------------------------------");
        System.out.println("Record Example. Test 1: ");
        User adminUser = new User("Jane", "Doe", "jane.doe@g.com", "111-222-3456", "Engineering");
        System.out.println("Admin User =  "+adminUser.firstName() + " " + adminUser.lastName());
        System.out.println("Admin User =  "+adminUser);

        System.out.println("Record Example. Test 2: With Custom Constructor");
        User2 adminUser2 = new User2("Susan", "Doe", "susan.doe@g.com", "111-222-3457", "Engineering");
        System.out.println("Admin User =  "+adminUser2.firstName() + " " + adminUser2.lastName());
        System.out.println("Admin User =  "+adminUser2);

        System.out.println("Record Example. Test 3: With Compact Constructor");
        User3 adminUser3 = new User3("Annie", "Doe", "annie.doe@g.com", "111-222-3458", "Engineering");
        System.out.println("Admin User =  "+adminUser3.firstName() + " " + adminUser3.lastName());
        System.out.println("Admin User =  "+adminUser3);

        System.out.println("Record Example. Test 4: With Compact Constructor & Testing Field Validations");
        User3 adminUser4 = new User3("Annie", null, "annie.doe@g.com", "111-222-3458", "Engineering");
        System.out.println("Admin User =  "+adminUser4.firstName() + " " + adminUser4.lastName());
        System.out.println("Admin User =  "+adminUser4);
    }
}
