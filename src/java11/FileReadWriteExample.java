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
package java11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Java 11 Example
 * Files ReadString WriteString
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class FileReadWriteExample {

    public static void main (String[] args) throws IOException {
        // Set Path
        Path path = Paths.get("./resources/akiera-kiera_biography.txt");
        // Read File
        String fileData = Files.readString(path);
        System.out.println("File Size = "+fileData.length());

        // Write File
        Path path2 = Paths.get("./resources/akiera.txt");
        Files.writeString(path2, fileData);
        if(path2.toFile().canRead()) {
          System.out.println("New File is readable! "+path2.toString());
        }
    }
}
