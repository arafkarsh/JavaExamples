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
package java08.inheritance;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class MultipleInheritanceExample {
	
	public static void main(String[] args) {
		
		Vehicle hoverCraft 	= new HoverCraft();
		Vehicle car 			= new CarBMW();
		Vehicle boat 		= new SpeedBoat();
		
		System.out.println("Multiple Inheritance in Java 8 using Default Methods in Interface\n");
		
		System.out.println("HoverCraft Total Wheels    = "+hoverCraft.totalWheels());
		System.out.println("HoverCraft Will FLY?       = "+hoverCraft.willFly());
		System.out.println("HoverCraft Max Speed?      = "+hoverCraft.maxSpeed());
		System.out.println("HoverCraft Runs On Land?   = "+((HoverCraft)hoverCraft).runsOnLand());
		System.out.println("HoverCraft Runs On Water?  = "+((HoverCraft)hoverCraft).runsOnWater());
		System.out.println();
		System.out.println("Car BMW Total Wheels       = "+car.totalWheels());
		System.out.println("Car BMW Will FLY?          = "+car.willFly());
		System.out.println("Car BMW Max Speed?         = "+car.maxSpeed());
		System.out.println("Car BMW Runs On Land?      = "+((Car) car).runsOnLand());
		System.out.println();
		System.out.println("Speed Boat Total Wheels    = "+boat.totalWheels());
		System.out.println("Speed Boat Will FLY?       = "+boat.willFly());
		System.out.println("Speed Boat Max Speed?      = "+boat.maxSpeed());
		System.out.println("Speed Boat Runs On Water?  = "+((Boat) boat).runsOnWater());
	}

}
