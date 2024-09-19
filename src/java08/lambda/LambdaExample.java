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

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;

/**
 * Java 8 Examples
 * 
 * Lambda & Streams
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class LambdaExample {

	private static List<Integer> intArray = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 13, 27, 32, 98, 113);
	private static List<String> languages = Arrays.asList( "Haskell", "Lisp", "Python", "Java", "Scala", "C", "C++", "C#", 
															"Perl", "Ruby", "Fortran", "Visual-Basic", "Pascal", "Basic",
															"JavaScript", "VBScript", "Ada", "JRuby", "Jython"); 
	
	public static void main(String[] args) {
		 
			System.out.println("== LAMBDA Expressions Examples ==================\n");
			lambdaExamples();

			System.out.println("\n\n== LAMBDA Expressions (FILTERS) Examples ========");
			lambdaExamples2();
			
			System.out.println("\n\n== Streams (Map & Reduce) Examples =============");
	        map();
	        mapReduce();
	        mapReduce2();
	        
			System.out.println("\n\n== LAMBDA Expressions Combining Conditions Examples ===");
			lambdaExamples3();

			System.out.println("\n\n== Streams (Statistics) Examples =============\n");
			streamAggregates();
	    }
		
		public static void lambdaExamples() {
	        System.out.print("Print ALL Numbers: \t");
	        functionReference(intArray, (n)->true);
	 
	        System.out.print("\nPrint NO Numbers: \t");
	        functionReference(intArray, (n)->false);
	 
	        System.out.print("\nPrint EVEN Numbers: \t");
	        functionReference(intArray, (n)-> n%2 == 0 );
	 
	        System.out.print("\nPrint ODD Numbers: \t");
	        functionReference(intArray, (n)-> n%2 == 1 );
	 
	        System.out.print("\nPrint Numbers > 5: \t");
	        functionReference(intArray, (n)-> n > 5 );			
		}

	    public static void functionReference(List<Integer> iArray, Predicate<Integer> predicate) {
	        for(Integer n: iArray)  {
	            if(predicate.test(n)) {
	                System.out.print(n + " ");
	            }
	        }
	    }
	    
		public static void lambdaExamples2() {

			System.out.print("\nLanguages which STARTS with J: \t"); 
			filter(languages, (str)->str.startsWith("J")); 
			System.out.print("\nLanguages which ENDS with a: \t"); 
			filter(languages, (str)->str.endsWith("a")); 
			System.out.print("\nPrint ALL languages: \t\t"); 
			filter(languages, (str)->true); 
			System.out.print("\nPrint NO languag: \t"); 
			filter(languages, (str)->false); 
			System.out.print("\nPrint language - LENGTH > 4: \t"); 
			filter(languages, (str)->str.length() > 4);
			System.out.print("\nPrint language - LENGTH = 4: \t"); 
			filter(languages, (str)->str.length() == 4);
			System.out.print("\nPrint language - LENGTH < 4: \t"); 
			filter(languages, (str)->str.length() < 4);
		}
		
	    
	    public static void filter(List<String> names, Predicate<String> condition) { 
		    	// OLD Way
		    	/**
		    	for(String name: names) { 
		    		if(condition.test(name)) { 
		    			System.out.print(name + " "); 
		    		} 
		    	} 
		    	*/
		    	// NEW Way
		    	names.stream()
		    		.filter((name) -> (condition.test(name)))
		    		.forEach((name) -> { System.out.print(name + " "); });	
	    }
	    
		public static void lambdaExamples3() {

			System.out.print("\nLanguages which STARTS with J and Length == 4: \t"); 
			filter2(languages, (str)->str.startsWith("J"), (str)->str.length() == 4);
		}
		
	    /**
	     * Predicate allows you to combine two or more Predicate into one. It provides 
	     * methods similar to logical operator AND and OR named as and(), or() and xor(), 
	     * which can be used to combine the condition you are passing to filter() method. 
	     * For example, In order to get all languages, which starts with J and are four 
	     * character long, you can define two separate Predicate instance covering each 
	     * condition and then combine them using Predicate.and() method, as shown in 
	     * below example:
	     */
	    
	    public static void filter2(List<String> names, Predicate<String> condition1, Predicate<String> condition2) {
		    	names.stream() 
		    		.filter(condition1.and(condition2))
		    		.forEach((n) -> System.out.print(n + " "));
	    }
	    
	    public static void map() {
		    	//Old way:
		    	System.out.print("\nSquaring Elements in an Integer Array OLD Way: ");
		    	for(Integer n : intArray) {
		    	    int x = n * n;
		    	    System.out.print(x);
		    	}
		    	 
		    	//New way:
		    	System.out.print("\nSquaring Elements in an Integer Array NEW Way: ");
		    	intArray.stream().map((x) -> x*x).forEach(System.out::print);
	    }
	 
	    public static void mapReduce() {
		    	//Old way:
		    	int sum1 = 0;
		    	for(Integer n : intArray) {
		    	    int x = n * n;
		    	    sum1 = sum1 + x;
		    	}
		    	System.out.print("\n\nSquare and Sum OLD Way = "+sum1);
		    	 
		    	//New way:
		    	int sum2 = intArray.stream().map(x -> x*x).reduce((x,y) -> x + y).get();
		    	System.out.print("\nSquare and Sum NEW Way = "+sum2);
	    }
	    
	    public static void mapReduce2() {
		    	// Applying 12.7% VAT on each purchase 
		    	// Old way: 
		    	double vat = 0.127;
		    	double surcharge = 0.10;
		    	List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500, 600, 700, 800, 900); 
		    	double total = 0; 
		    	for (Integer cost : costBeforeTax) { 
		    		double price = cost + (vat * cost); 
		    		total = total + price; 
		    	} 
		    	total = total + (total * surcharge);
		    	System.out.print("\n\nTotal 12.7% VAT + 10.0% Surcharge OLD way = " + total); 
		    	
		    	// New way: 
		    	double costAfterTax = costBeforeTax.stream().map((c) -> c + (vat * c)).reduce((s, c) -> s + c).get(); 
		    	costAfterTax = costAfterTax + (costAfterTax * surcharge);
		    	System.out.println("\nTotal 12.7% VAT + 10.0% Surcharge NEW way = " + costAfterTax);
	    }

	    /**
	     * Calculating Maximum, Minimum, Sum and Average of List elements
	     * 
	     * There is a very useful method called summaryStattics() in stream classes like 
	     * IntStream, LongStream and DoubleStream. Which returns returns an IntSummaryStatistics, 
	     * LongSummaryStatistics or DoubleSummaryStatistics describing various summary data about 
	     * the elements of this stream. In following example, we have used this method to calculate 
	     * maximum and minimum number in a List. It also has getSum() and getAverage() which can 
	     * give sum and average of all numbers from List.
	     */
	    public static void streamAggregates() {
		    	//Get count, min, max, sum, and average for numbers 
		    	
		    	List<Integer> primes = Arrays.asList(3, 5, 7, 11, 13, 17, 19, 23, 29,
		    			 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 709, 1051, 2503, 3877); 
		    	
		    	IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics(); 
		    	
		    	System.out.println("Highest PRIME number in List = " + stats.getMax()); 
		    	System.out.println("Lowest PRIME number in List  = " + stats.getMin()); 
		    	System.out.println("Sum of ALL PRIME numbers     = " + stats.getSum()); 
		    	System.out.println("Average of ALL PRIME numbers = " + stats.getAverage());
	    }
}
