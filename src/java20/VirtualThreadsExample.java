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
package java20;

import java.time.Duration;
import java.util.Date;

import static java.lang.System.out;

/**
 * Virtual Threads - JEP 436
 * Virtual threads are lightweight threads that dramatically reduce the effort of writing,
 * maintaining, and observing high-throughput concurrent applications.
 *
 * Source: https://openjdk.org/jeps/436
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class VirtualThreadsExample {

    public static void main(String[] args) throws InterruptedException {
        // Java 20
        out.println("JAVA 20 >>>>>--------------------------------------------------------");
        virtualThreadsExample1();
    }

    /**
     * Virtual Threads Example 1
     * @throws InterruptedException
     */
    private static void virtualThreadsExample1() throws InterruptedException {
        out.println(new Date()+"| Virtual Threads Example 1: JEP 436");
        var virtualThread = Thread.ofVirtual().start(() -> {
            System.out.println(new Date()+"| Running in a virtual thread");
            try { Thread.sleep(Duration.ofSeconds(3)); }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        });
        virtualThread.join(); // Wait for the virtual thread to complete
        System.out.println(new Date()+"| Process Complete!!!");
    }
}
