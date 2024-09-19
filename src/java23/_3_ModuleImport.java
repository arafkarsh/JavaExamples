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
package java23;

import static java.lang.System.out;
import module java.base;

/**
 * Module Import
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _3_ModuleImport {

    public static void main (String[] args) throws Exception{
        // Multiple API's are called with a single import.  JEP 476
        // import module java.base
        out.println("java --enable-preview _3_ModuleImport.java");
        out.println("Array of Values  = "+List.of("Hello ", "Module Import ", "World!"));
        File file = new File("README.MD");
        out.println("File README.MD = "+file.canRead());
        out.println("Reading the file....");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while(br.ready()) {
                out.println(br.readLine());
            }
        }
        out.println("File closed ....");

    }
}
