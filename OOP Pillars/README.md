# 🧱 Four Pillars of OOP in Java

## 📌 Summary Table

| Pillar           | Description                                                                 | Java Example                                      |
|------------------|-----------------------------------------------------------------------------|---------------------------------------------------|
| 1. Encapsulation | Wrapping data and methods into a single unit (class). Access control using `private`, `public`, etc. | Use of private fields + getters/setters.          |
| 2. Abstraction   | Hiding complex implementation details and showing only essential features.  | Abstract classes and interfaces.                  |
| 3. Inheritance   | One class (child) inherits properties and behaviors from another (parent).  | `class Dog extends Animal {}`                     |
| 4. Polymorphism  | One interface, many implementations. Ability to use a single interface for multiple forms. | Method overriding and interface implementation.   |

## 1. 🧩 Encapsulation
Encapsulation hides internal state and requires all interaction to be performed through an object’s methods.
```
public class Person {
    private String name; // private field

    public String getName() { return name; }  // public getter
    public void setName(String name) { this.name = name; } // public setter
}
```

## 2. 🚪 Abstraction
Abstraction lets you define the what without the how.
```
abstract class Animal {
    abstract void makeSound(); // abstract method
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Bark");
    }
}
```
or using interfaces
```
interface Vehicle {
    void start();
}

class Car implements Vehicle {
    public void start() {
        System.out.println("Car starting...");
    }
}
```
Abstraction with class is ***partial*** as we can still acess variables from base class if any public, but with interfaces its ***concrete*** abstraction

## 3. 🧬 Inheritance
Inheritance allows a class to inherit fields and methods from another class.
```
class Animal {
    void eat() {
        System.out.println("This animal eats food.");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks.");
    }
}
```

## 4. 🔄 Polymorphism
Polymorphism lets one interface be used for different underlying forms.
```
class Animal {
    void sound() {
        System.out.println("Some sound");
    }
}

class Cat extends Animal {
    void sound() {
        System.out.println("Meow");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Bark");
    }
}

// Usage:
Animal a = new Dog();  // Polymorphic reference
a.sound();             // Output: Bark
```

## ✅ Summary Table

| Pillar         | Java Feature Examples                       |
|----------------|----------------------------------------------|
| Encapsulation  | `private` fields, `public` getters/setters   |
| Abstraction    | `abstract` classes, `interface`              |
| Inheritance    | `extends` and `super` keywords               |
| Polymorphism   | Method overriding, upcasting                 |
