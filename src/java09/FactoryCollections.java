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
package java09;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class FactoryCollections {
	
	public static void main (String[] args) {
		// Characteristcs of Factory-made Collections
		// - Unmodifiable
		// - Nulls Disallowed
		// - Duplicates Disallowed (Sets and Maps)
		// - Randomized Iteration Order (Sets and Maps)
		exampleList();
	}
	
	public static void exampleList() {
		// Java 8
		List<Integer> intArry1 = Collections.unmodifiableList(Arrays.asList(1,2,3,4));
		System.out.println("IntArray 1 = "+intArry1);
		
		// Java 9
		List<Integer> intArry2 = List.of(1,2,3,4);
		System.out.println("IntArray 2 = "+intArry2);

		// Java 8
		Map<String, Integer> map1 = new HashMap<>();
		map1.put("a", 10);
		map1.put("b", 20);
		map1.put("c", 30);
		
		map1 = Collections.unmodifiableMap(map1);
		System.out.println("Map 1 = "+map1);

		// Java 9
		Map<String, Integer> map2 = Map.of("a", 10, "b", 20, "c", 30);
		System.out.println("Map 2 = "+map2);
	}
}
