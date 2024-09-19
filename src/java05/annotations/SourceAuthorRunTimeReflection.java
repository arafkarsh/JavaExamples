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
package java05.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 */
public class SourceAuthorRunTimeReflection {
	
	public static final byte CLASS 	= 0;
	public static final byte METHOD	= 1;
	public static final byte FIELD	= 2;

	/**
	 * Get Author Details from Class @ Runtime
	 * 
	 * @param args
	 * @throws Exception
	 */
	
	public static void main(String[] args) throws Exception {
		 
		System.out.println("Custom Annotations for Class Author and Audit Details...");
		
		Class obj = Class.forName("com.fusionfire.examples.commons.utils.SourceAuthorAnnotationExample");

		showAuthorDetails(obj, null);
		showMethodDetails(obj);
	}
	
	/**
	 * Show Author Details from Class Perspective
	 * 
	 * @param obj
	 * @return
	 * @throws ClassNotFoundException
	 */

	public static Class showAuthorDetails(Class obj, Class _author1) throws ClassNotFoundException {
		// Process @CustomClass
		
		Class _author = Class.forName("com.fusionfire.examples.commons.utils.SourceCodeAuthorClass");
		
		if (obj.isAnnotationPresent(_author)) {
	 
			Annotation annotation = (Annotation) obj.getAnnotation(_author);
			SourceCodeAuthorClass customClass = (SourceCodeAuthorClass) annotation;
	 
			System.out.printf("%nComplexity \t : %s", customClass.complexity());
			System.out.printf("%nCreatedBy \t : %s", customClass.createdBy());
			System.out.printf("%nUpdatedBy \t : %s", customClass.updatedBy());
			System.out.printf("%nVersion \t : %s", customClass.versionNumber());
			System.out.printf("%nLastModified \t : %s", customClass.lastModified());
			
			System.out.printf("%n%nCode Comments \t : ");
	 
			int comments = customClass.comments().length;
			for (String comment : customClass.comments()) {
				if (comments > 1) {
					System.out.print(comment + ", ");
				} else {
					System.out.print(comment);
				}
				comments--;
			}
			System.out.printf("%n%n");
		}
		return obj;	
	}
	
	
	
	/**
	 * Show Method Author details
	 * 
	 * @param obj
	 * @throws ClassNotFoundException
	 */
	
	public static void showMethodDetails(Class obj) throws ClassNotFoundException {
		int passed = 0, failed = 0, count = 0, ignore = 0;
		// Process @SourceCodeAuthorMethod
		for (Method method : obj.getDeclaredMethods()) {
			
			Class cm = Class.forName("com.fusionfire.examples.commons.utils.SourceCodeAuthorMethod");
	 
			// if method is annotated with @@SourceCodeAuthorMethod
			if (method.isAnnotationPresent(cm)) {
	 
				Annotation annotation = method.getAnnotation(cm);
				SourceCodeAuthorMethod methodTest = (SourceCodeAuthorMethod) annotation;
	 
				// Check Enabled Methods
				if (methodTest.enabled()) {
	 
				  try {
					method.invoke(obj.newInstance());
					System.out.printf("%s - Class.Method '%s' \t: PASSED %n", ++count, method.getName());
					passed++;
				  } catch (Throwable ex) {
					System.out.printf("%s - Class.Method '%s' \t: FAILED: %s %n", ++count, method.getName(), ex.getCause());
					failed++;
				  }
	 
				} else {
					System.out.printf("%s - Class.Method '%s' \t: IGNORED%n", ++count, method.getName());
					ignore++;
				}
			}
		}
		System.out.printf("%nResult : Total : %d, Passed: %d, Failed %d, Ignore %d%n", count, passed, failed, ignore);

		
	}
}
