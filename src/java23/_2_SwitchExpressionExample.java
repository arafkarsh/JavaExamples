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
import static java.lang.System.out;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
void main(String[] args) {
    // Java 23
    out.println("JAVA 23 >>>>>--------------------------------------------------------");
    // Primitive Pattern Matching: JEP 455
    oldModel();
    newModel();
}

void newModel() {
    out.println("Switch Expression Pattern Matching New Model.... Primitive Supported");
    Object o = 127;
    switch (o) {
        case int i -> out.println("Integer: "+ i);
        case long l -> out.println("Long: "+ l);
        default -> out.println("Other: "+ o);
    }
}

void oldModel() {
    out.println("Switch Expression Pattern Matching Old Model....");
    Object o = 127;
    switch (o) {
        case Integer i -> out.println("Integer: "+ i);
        case Long l -> out.println("Long: "+ l);
        default -> out.println("Other: "+ o);
    }
}

