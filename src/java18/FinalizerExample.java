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

    @Override
    public void close() throws Exception {
        out.println(new Date()+" | Releasing resource in finalize: " + resource);
    }
}
