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
package java08.lambda;

/**
 * FunctionalInterface Example
 * 
 * Passing Function References
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class FunctionalInterfaceExample {
	
	public static void main(String[] args) {
		
		double x = 10.0;
		double y = 3.0;

		System.out.println("Examples Functional Interface - Java 8 ============\n");
		
		System.out.println("ADD "+x+" + "+y+" = "+ calc( (a,b) -> a+b, x, y ));
		System.out.println("MUL "+x+" * "+y+" = "+ calc( (a,b) -> a*b, x, y ));
		System.out.println("SUB "+x+" - "+y+" = "+ calc( (a,b) -> a-b, x, y ));
		System.out.println("DIV "+x+" / "+y+" = "+ calc( (a,b) -> a/b, x, y ));
		
		System.out.println("\nPassing Behaviours from Math functions =============\n");
		
		System.out.println("MAX   "+x+" or "+y+" \t\t= "+ calc( (a,b) -> Math.max(a,b), x, y ));
		System.out.println("MIN   "+x+" or "+y+" \t\t= "+ calc( (a,b) -> Math.min(a,b), x, y ));
		System.out.println("POWER "+x+" ^ "+y+"  \t\t= "+ calc( (a,b) -> Math.pow(a,b), x, y ));		
		System.out.println("HYPOT sqrt("+x+"^2 + "+y+"^2) \t= "+ calc( (a,b) -> Math.hypot(a,b), x, y ));
	
		System.out.println("\nPassing Behaviours from Math functions 2 ===========\n");
		
		System.out.println("sin("+x+")  = "+ calcS( (a) -> Math.sin(a), x));
		System.out.println("cos("+x+")  = "+ calcS( (a) -> Math.cos(a), x));	
		System.out.println("tan("+x+")  = "+ calcS( (a) -> Math.tan(a), x));
		System.out.println("log("+x+")  = "+ calcS( (a) -> Math.log(a), x));
		System.out.println("sqrt("+x+") = "+ calcS( (a) -> Math.sqrt(a), x));
		
		System.out.println("\nAvoiding Inner Class ===========\n");
		
		Thread old = new Thread(new Runnable() {
			public void run() {
				System.out.println("Runnable Inner Class in Thread....");
			}
		});
		old.start();
		
		Thread t = new Thread(() -> System.out.println("Runnable Thread using Functional Interface"));
		t.start();
	}

	public static double calc(Calculator calculator, double x, double y) {
		return calculator.calculate(x,y);
	}

	public static double calcS(CalculatorScientific calculator, double x) {
		return calculator.calculate(x);
	}
}
