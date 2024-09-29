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

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import static java.lang.System.out;

/**
 * JavaExamples / CodeSnippetExample
 * JEP 413: Code Snippets in Java API Documentation
 *
 * Java 18 allows Javadoc to include code snippets using the new @snippet tag, which provides
 * better structure and functionality.
 *
 * @author: Araf Karsh Hamid
 * @version: 0.1
 * @date: 2024-09-29T19:24
 */
public class CodeSnippetExample {

    public static void main(String[] args) throws IOException {
        // Java 18
        CodeSnippetExample cse = new CodeSnippetExample();
        cse.startWebServer();
    }

    /**
     * Start a Java Web Server
     * Web Server Listens on Port 8080
     *
     * {@snippet :
     *      HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
     *       server.createContext("/", exchange -> {
     *       String response = "Hello World!, My First Java Web Server!";
     *       exchange.sendResponseHeaders(200, response.length());
     *       try (OutputStream os = exchange.getResponseBody()) {
     *       os.write(response.getBytes());
     *       }
     *       });
     *       server.start();
     * }
     * @throws IOException
     */
    public void startWebServer() throws IOException {
        // Java 18
        out.println("JAVA 18 >>>>>--------------------------------------------------------");
        out.println("Code Snippet Example... ");
        out.println("Java Web Server Example...  Listening on Port 8080");
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", exchange -> {
            String response = "Hello World!, My First Java Web Server!";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });
        // server.start();
        System.out.println("Server Not Started at http://localhost:8080");
    }
}
