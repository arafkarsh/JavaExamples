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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.System.out;

/**
 * JavaExamples / UTF8Example
 * JEP 400: UTF-8 by Default
 *
 * In Java 18, UTF-8 is now the default character set for java.nio.file.Files, java.io, and other
 * standard APIs that handle text. This means no explicit encoding is required when reading or
 * writing text files.
 *
 * @author: Araf Karsh Hamid
 * @version: 0.1
 * @date: 2024-09-29T21:20
 */
public class UTF8Example {

    public static void main(String[] args) throws IOException {
        // Java 18
        out.println("JAVA 18 >>>>>--------------------------------------------------------");
        out.println("UTF 8 Example for NIO.... ");
        Path path = Path.of("example.txt");
        String content = """
            Hello, UTF-8 by default! 😊
            Hello World > \uD83D\uDC4F\uD83C\uDFFD\uD83D\uDC46\uD83C\uDFFD\uD83C\uDF82\uD83C\uDF39\uD83D\uDE0D\uD83E\uDD17 
            """;

        // Write content to the file (UTF-8 by default)
        Files.writeString(path, content);

        // Read content from the file
        String readContent = Files.readString(path);
        System.out.println(readContent);
    }
}
