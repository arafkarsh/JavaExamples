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

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

import static java.lang.System.out;


/**
 * JavaExamples / VectorExample
 *
 * JEP 417: Vector API (Third Incubator)
 *
 * The Vector API is aimed at improving performance for vector computations.
 *
 *
 * @author: Araf Karsh Hamid
 * @version: 0.1
 * @date: 2024-09-30T00:42
 */
public class VectorExample {

    private static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_256;

    public static void main(String[] args) {
        // Java 18
        out.println("JAVA 18 >>>>>--------------------------------------------------------");
        out.println("Vector Example .... ");
        float[] a = {1.0f, 2.0f, 3.0f, 4.0f};
        float[] b = {5.0f, 6.0f, 7.0f, 8.0f};
        float[] c = new float[4];

        FloatVector va = FloatVector.fromArray(SPECIES, a, 0);
        FloatVector vb = FloatVector.fromArray(SPECIES, b, 0);
        FloatVector vc = va.add(vb);
        vc.intoArray(c, 0);

        for (float value : c) {
            out.println(value);
        }
    }
}