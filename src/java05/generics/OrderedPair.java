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
 * Type Inference
 * 
 * Type inference is a Java compiler's ability to look at each method invocation and corresponding 
 * declaration to determine the type argument (or arguments) that make the invocation applicable. 
 * The inference algorithm determines the types of the arguments and, if available, the type that 
 * the result is being assigned, or returned. Finally, the inference algorithm tries to find the 
 * most specific type that works with all of the arguments.
 *
 * Wildcards
 * 
 * In generic code, the question mark (?), called the wildcard, represents an unknown type. The wildcard 
 * can be used in a variety of situations: as the type of a parameter, field, or local variable; sometimes 
 * as a return type (though it is better programming practice to be more specific). The wildcard is never 
 * used as a type argument for a generic method invocation, a generic class instance creation, or a 
 * supertype.
 * 
 * Upper Bounded Wildcards
 * 
 * You can use an upper bounded wildcard to relax the restrictions on a variable. For example, say you want 
 * to write a method that works on List<Integer>, List<Double>, and List<Number>; you can achieve this by 
 * using an upper bounded wildcard.
 * 
 * To declare an upper-bounded wildcard, use the wildcard character ('?'), followed by the extends keyword, 
 * followed by its upper bound. Note that, in this context, extends is used in a general sense to mean either 
 * "extends" (as in classes) or "implements" (as in interfaces).
 * 
 * To write the method that works on lists of Number and the subtypes of Number, such as Integer, Double, and 
 * Float, you would specify List<? extends Number>. The term List<Number> is more restrictive than 
 * List<? extends Number> because the former matches a list of type Number only, whereas the latter matches a 
 * list of type Number or any of its subclasses.
 * 
 * Consider the following process method:
 * 
 * public static void process(List<? extends Foo> list) { // ...  }
 * 
 * The upper bounded wildcard, <? extends Foo>, where Foo is any type, matches Foo and any subtype of Foo. The 
 * process method can access the list elements as type Foo:
 * 
 * Unbounded Wildcards
 * 
 * The unbounded wildcard type is specified using the wildcard character (?), for example, List<?>. This is 
 * called a list of unknown type. There are two scenarios where an unbounded wildcard is a useful approach:
 * 
 * - If you are writing a method that can be implemented using functionality provided in the Object class.
 * - When the code is using methods in the generic class that don't depend on the type parameter. For example, 
 *   List.size or List.clear. In fact, Class<?> is so often used because most of the methods in Class<T> do not 
 *   depend on T.
 * 
 * Lower Bounded Wildcards
 * 
 * The Upper Bounded Wildcards section shows that an upper bounded wildcard restricts the unknown type to be a 
 * specific type or a subtype of that type and is represented using the extends keyword. In a similar way, a 
 * lower bounded wildcard restricts the unknown type to be a specific type or a super type of that type.
 * 
 * A lower bounded wildcard is expressed using the wildcard character ('?'), following by the super keyword, 
 * followed by its lower bound: <? super A>.
 * 
 * --------------------------------------------------------------------------------------------------------------------
 * Note: You can specify an upper bound for a wildcard, or you can specify a lower bound, but you cannot specify both.
 * --------------------------------------------------------------------------------------------------------------------
 * 
 * Say you want to write a method that puts Integer objects into a list. To maximize flexibility, you would like the 
 * method to work on List<Integer>, List<Number>, and List<Object> — anything that can hold Integer values.
 * 
 * To write the method that works on lists of Integer and the supertypes of Integer, such as Integer, Number, and 
 * Object, you would specify List<? super Integer>. The term List<Integer> is more restrictive than List<? super Integer> 
 * because the former matches a list of type Integer only, whereas the latter matches a list of any type that is a 
 * supertype of Integer.
 * 
 * 
 * @param <K>
 * @param <V>
 *
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 */
public class OrderedPair<K, V> implements Pair<K, V> {
	
	private final K key;
	private final V value;
	
	public OrderedPair(K _key, V _value) {
		key = _key;
		value = _value;
	}

	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
	
	/**
	 * OrderPair<Integer, String> op = new OrderedPair<Integer, String>(1, "Book");
	 * 
	 * The type has been explicitly provided, as shown in bold. Generally, this can 
	 * be left out and the compiler will infer the type that is needed:
	 * 
	 * boolean same = op.<Integer, String>.compare(new OrderedPair<Integer, String>(2, "DVD"));
	 * 
	 * This feature, known as type inference, allows you to invoke a generic method as an 
	 * ordinary method, without specifying a type between angle brackets.
	 * 
	 * boolean same = op.compare(new OrderedPair<Integer, String>(2, "DVD"));
	 * 
	 * @param _pair
	 * @return
	 */
	
	public <K, V> boolean compare(Pair<K, V> _pair) {
		return this.getKey().equals(_pair.getKey()) &&
				this.getValue().equals(_pair.getValue());
	}
}
