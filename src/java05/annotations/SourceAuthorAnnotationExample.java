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

/**
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 */
@SourceCodeAuthorClass(
		// complexity = ,
		createdBy = "Araf Karsh",
		updatedBy = "Jon Doe",
		versionNumber = 2,
		comments = "Commments for the Custom Code Example Class"
)
public class SourceAuthorAnnotationExample {
	
	@SourceCodeAuthorField(
			createdBy = "Araf Karsh",
			updatedBy = "Jon Doe",
			versionNumber = 3,
			comments = "New Field Added for CustomCodeExample"
	)
	private String newField = "new Field";

	@SourceCodeAuthorMethod(
			createdBy = "Araf Karsh",
			updatedBy = "Jon Doe",
			versionNumber = 4,
			comments = "New Method Added for CustomCodeExample"
	)
	void methodONE() {
	  if (true)
		throw new RuntimeException("This test always failed");
	}
 
	@SourceCodeAuthorMethod(enabled = true)
	void methodTWO() {
	  if (false)
		throw new RuntimeException("This test always passed");
	}
 
	@SourceCodeAuthorMethod(enabled = false)
	void methodTHREE() {
	  if (987 > 1) {
		  // What's the importance of 987?
		  // It's a Fibanacci Number... :-)
	  }
	}
 
}
