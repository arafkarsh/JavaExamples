/**
 * Copyright (c) 2018 Araf Karsh Hamid

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
package java05.generics;

/**
 * 
 * 
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 */
public class GenericMethods {

	//Java Generic Method
	public static <T> boolean isEqual(GenericType<T> g1, GenericType<T> g2){
		return g1.get().equals(g2.get());
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]){
		GenericType<String> g1 = new GenericType<>();
		g1.set("Pankaj");
		
		GenericType<String> g2 = new GenericType<>();
		g2.set("Pankaj");
		
		boolean isEqual = GenericMethods.<String>isEqual(g1, g2);
		//above statement can be written simply as
		isEqual = GenericMethods.isEqual(g1, g2);
		//This feature, known as type inference, allows you to invoke a generic method as an ordinary method, without specifying a type between angle brackets.
		//Compiler will infer the type that is needed
	}
}
