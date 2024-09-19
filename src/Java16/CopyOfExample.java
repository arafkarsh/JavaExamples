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
package Java16;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 16 Examples
 * List CopyOf
 * 
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class CopyOfExample {

    public static void main (String[] args) {
        List<String> cities = buildCities();
        addNewCity(cities, "Mumbai");

        List<String> copyOfCities = List.copyOf(cities);
        addNewCity(copyOfCities, "Lucknow");
    }

    public static void addNewCity(List<String> cities, String city) {
        try {
            cities.add(city);
            System.out.println("City Added = " + city);
        } catch (Exception e) {
            System.out.println("Unable to add "+city+"! Error = "+e);
        }
        System.out.println("Cites = " + cities);
    }

    public static  List<String> buildCities() {
        List<String> cities = new ArrayList<String>();
        cities.add("New York");
        cities.add("Kochi");
        cities.add("London");
        cities.add("Tokyo");
        cities.add("Sydney");
        System.out.println("Cites = "+cities);
        return cities;
    }
}
