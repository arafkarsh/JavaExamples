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
package java19;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.StructuredTaskScope;

import static java.lang.System.out;
import static java.lang.Thread.*;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class StructuredConcurrencyExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Java 19
        out.println("JAVA 19 >>>>>--------------------------------------------------------");
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            out.println(">> Starting Task 1");
            StructuredTaskScope.Subtask<String> future1 = scope.fork(() -> {
                sleep(1000);
                out.println("  Task 1 Completed");
                return "Task 1 completed";
            });
            out.println(">> Starting Task 2");
            StructuredTaskScope.Subtask<String> future2 = scope.fork(() -> {
                sleep(2000);
                out.println("  Task 2 completed");
                return "Task 2 completed";
            });
            out.println(">> Waiting for All tasks to completed");
            scope.join();                   // Wait for all tasks to complete
            out.println(">> Throws Exception if a task is failed!");
            scope.throwIfFailed();          // Throw if any tasks failed

            out.println(">> Waiting for Final Results.......");
            System.out.println(">> RESULT 1 = "+future1.get());
            System.out.println(">> RESULT 2 = "+future2.get());
        }
    }
}
