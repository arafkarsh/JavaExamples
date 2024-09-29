# Design Patterns - Gang of Four

The Gang of Four (GoF) Design Patterns are a collection of 23 design patterns introduced by Erich 
Gamma, Richard Helm, Ralph Johnson, and John Vlissides in their book “Design Patterns: Elements 
of Reusable Object-Oriented Software”. These patterns provide general reusable solutions to common 
software design problems. They are categorized into three groups: Creational, Structural, and Behavioral.

## 1. Creational Patterns

Creational patterns deal with object creation mechanisms, trying to create objects in a manner 
suitable to the situation. They abstract the instantiation process, making a system independent of 
how its objects are created, composed, and represented. The primary creational patterns are:

	•	Factory Method: Defines an interface for creating an object but allows subclasses to alter the type of objects that will be created.
	•	Abstract Factory: Provides an interface for creating families of related or dependent objects without specifying their concrete classes.
	•	Builder: Separates the construction of a complex object from its representation, allowing the same construction process to create different representations.
	•	Prototype: Creates new objects by copying an existing object, known as the prototype.
	•	Singleton: Ensures a class has only one instance and provides a global point of access to it.

## 2. Structural Patterns

Structural patterns are concerned with how classes and objects are composed to form larger 
structures. They help ensure that when one part of a system changes, the entire structure does 
not need to change. The key structural patterns are:

	•	Adapter: Converts the interface of a class into another interface clients expect. It allows classes to work together that couldn’t otherwise because of incompatible interfaces.
	•	Bridge: Decouples an abstraction from its implementation, allowing them to vary independently.
	•	Composite: Composes objects into tree structures to represent part-whole hierarchies. It allows clients to treat individual objects and compositions of objects uniformly.
	•	Decorator: Adds new responsibilities to an object dynamically. It provides a flexible alternative to subclassing for extending functionality.
	•	Facade: Provides a simplified interface to a complex subsystem, making it easier for clients to interact with.
	•	Flyweight: Reduces the cost of creating and manipulating a large number of similar objects by sharing common parts of state among multiple objects.
	•	Proxy: Provides a surrogate or placeholder for another object to control access to it.

## 3. Behavioral Patterns

Behavioral patterns are concerned with the interactions and responsibilities of objects. They help 
make the communication between objects more flexible and are focused on how objects interact 
and fulfill responsibilities. The major behavioral patterns include:

	•	Chain of Responsibility: Passes a request along a chain of handlers. Each handler decides either to process the request or to pass it to the next handler in the chain.
	•	Command: Encapsulates a request as an object, allowing parameterization of clients with different requests, and support for undoable operations.
	•	Interpreter: Defines a representation for a language’s grammar and uses an interpreter to evaluate sentences in the language.
	•	Iterator: Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.
	•	Mediator: Defines an object that encapsulates how a set of objects interact, promoting loose coupling by preventing objects from referring to each other explicitly.
	•	Memento: Allows capturing and externalizing an object’s internal state so that the object can be restored to this state later without violating encapsulation.
	•	Observer: Defines a dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
	•	State: Allows an object to alter its behavior when its internal state changes. The object will appear to change its class.
	•	Strategy: Defines a family of algorithms, encapsulates each one, and makes them interchangeable. It lets the algorithm vary independently from clients that use it.
	•	Template Method: Defines the skeleton of an algorithm in a method, deferring some steps to subclasses.
	•	Visitor: Represents an operation to be performed on elements of an object structure, allowing the definition of a new operation without changing the classes of the elements on which it operates.

These design patterns provide tested and proven solutions to recurring design problems, which makes 
software more maintainable, scalable, and easier to understand. Each pattern has a specific purpose 
and application, and understanding when and how to apply these patterns is a key skill for software 
engineers.