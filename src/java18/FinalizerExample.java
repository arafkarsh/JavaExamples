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

import java.util.Date;

import static java.lang.System.out;

/**
 * JavaExamples / FinalizerExample
 *
 * JEP 421: Deprecate Finalization for Removal
 *
 * Java 18 deprecates the finalization mechanism for removal, which encourages developers to use
 * alternative methods like AutoCloseable.
 *
 * Old Way: Using finalize()
 *
 * In the older Java versions, developers often used the finalize() method to release resources,
 * such as closing a file or releasing memory. However, this approach has significant downsides,
 * such as unpredictable timing of finalization and high overhead for garbage collection.
 *
 * New Way: Using AutoCloseable and try-with-resources
 *
 * Java 18 encourages developers to use AutoCloseable with try-with-resources to release
 * resources deterministically and effectively, addressing the issues found with finalize().
 *
 * @author: Araf Karsh Hamid
 * @version: 0.1
 * @date: 2024-09-29T21:52
 */
public class FinalizerExample {

    public static void main(String[] args) throws Exception {
        // Java 18
        out.println("JAVA 18 >>>>>--------------------------------------------------------");
        var ofe = new OldFinalizerExample();
        ofe.printResource();
        ofe = null;
        // Finalizer may or may not be called in a timely manner
        System.gc();
        try (var nfe = new NewFinalizerExample()) {
            // Example usage of the resource
            nfe.printResource();
        }
        // Resource is automatically closed when exiting the try-with-resources block
    }
}

/**
 * Old Finalizer Model
 */
class OldFinalizerExample {

    private String resource;

    public OldFinalizerExample() {
        resource = "Old Finalizer Model...";
        out.println(new Date()+" | Old Finalizer Model");
    }

    public void printResource() {
        out.println(new Date()+" | Resource = "+resource);
    }

    /**
     * finalize()
     * 	•	finalize() is invoked by the garbage collector just before an object is destroyed.
     * 	•	This method can be used to release resources, but it has major drawbacks:
     * 	•	Unpredictable timing: There’s no guarantee on when finalize() will be called.
     * 	•	Performance: The use of finalizers adds an overhead to garbage collection and can cause delays.
     * 	•	Memory Leaks: Objects with a finalize() method often take longer to be collected.
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        try {
            out.println(new Date()+" | Releasing resource in finalize: " + resource);
        } finally {
            super.finalize();
        }
    }
}

/**
 * New Finalizer Model
 */
class NewFinalizerExample implements AutoCloseable {

    private String resource;

    public NewFinalizerExample() {
        resource = "New Finalizer Model...";
        out.println(new Date()+" | New Finalizer Model");
    }

    public void printResource() {
        out.println(new Date()+" | Resource = "+resource);
    }

    /**
     * 	- AutoCloseable Interface: Implementing AutoCloseable allows the object to be used in
     * 	  a try-with-resources block, where close() is called automatically at the end of the block.
     * 	- try-with-resources: This construct ensures that the resource is closed automatically,
     * 	  which is deterministic and occurs immediately after the block is exited.
     * 	- Clearer Lifecycle Management: Resources are properly closed as soon as they’re no
     * 	  longer needed, without relying on the unpredictable garbage collector behavior.
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        out.println(new Date()+" | Releasing resource in finalize: " + resource);
    }
}
