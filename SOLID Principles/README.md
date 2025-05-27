# SOLID Principles
#
#
| Principle | Name | Definition | Benifits
| ------ | ------ | ------ | ------ |
| S | Single Responsibility Principle | A class should have only one reason to change.| Easier to understand & maintain |
| O | Open/Closed Principle | Software entities should be open for extension, but closed for modification. | Add features without changing old code |
| L | Liskov Substitution Principle | 	Subtypes must be substitutable for their base types. | Polymorphism works predictably |
| I | Interface Segregation Principle | Clients should not be forced to depend on interfaces they do not use. | Avoids implementing unused methods |
| D | Dependency Inversion Principle | Depend on abstractions, not concretions. | Loosely coupled, flexible code |

## 1. Single Responsibility Principle
A class should have only one reason to change.
### ❌ Violation:
# 
```
public class Invoice {
    private String customer;
    private double amount;

    public Invoice(String customer, double amount) {
        this.customer = customer;
        this.amount = amount;
    }

    public void printInvoice() {
        System.out.println("Invoice for " + customer + ": $" + amount);
    }

    public void saveToFile(String filename) {
        // File writing logic here
    }
}
```
###### Problem: Invoice handles both data and printing/persistence.
#
### ✅ SRP Applied:
# 
```
public class Invoice {
    private String customer;
    private double amount;

    public Invoice(String customer, double amount) {
        this.customer = customer;
        this.amount = amount;
    }

    public String getDetails() {
        return "Invoice for " + customer + ": $" + amount;
    }
}

public class InvoicePrinter {
    public void print(Invoice invoice) {
        System.out.println(invoice.getDetails());
    }
}

public class InvoiceSaver {
    public void save(Invoice invoice, String filename) {
        // Save logic
    }
}
```

## 2. Open/Closed Principle (OCP)
Software should be open for extension, but closed for modification.
### ❌ Violation:
# 
```
public class DiscountCalculator {
    public double calculateDiscount(String type, double amount) {
        if (type.equals("SILVER")) {
            return amount * 0.1;
        } else if (type.equals("GOLD")) {
            return amount * 0.2;
        }
        return 0;
    }
}
```
###### Problem: Every new discount type requires modifying the method.
#
### ✅ OCP Applied (using polymorphism):
# 
```
public interface Discount {
    double apply(double amount);
}

public class SilverDiscount implements Discount {
    public double apply(double amount) {
        return amount * 0.1;
    }
}

public class GoldDiscount implements Discount {
    public double apply(double amount) {
        return amount * 0.2;
    }
}

public class DiscountCalculator {
    public double calculate(Discount discount, double amount) {
        return discount.apply(amount);
    }
}
```

## 3. Liskov Substitution Principle (LSP)
Subtypes must be substitutable for their base types.
### ❌ Violation:
# 
```
class Bird {
    public void fly() {
        System.out.println("Flying...");
    }
}

class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostrich can't fly!");
    }
}
```
###### Problem: Ostrich violates the behavior expected of Bird.
#
### ✅ LSP Applied (Refactor Hierarchy):
# 
```
abstract class Bird {
    public abstract void move();
}

class FlyingBird extends Bird {
    public void move() {
        System.out.println("Flying...");
    }
}

class Ostrich extends Bird {
    public void move() {
        System.out.println("Running...");
    }
}
```

## 4. Interface Segregation Principle (ISP)
No client should be forced to depend on methods it does not use.
### ❌ Violation:
# 
```
public interface Worker {
    void work();
    void eat();
}

public class Robot implements Worker {
    public void work() { System.out.println("Robot working"); }
    public void eat() { throw new UnsupportedOperationException(); }
}
```
###### Problem: Robot is forced to implement eat() which it doesn’t need.
#
### ✅ ISP Applied:
# 
```
public interface Workable {
    void work();
}

public interface Eatable {
    void eat();
}

public class Human implements Workable, Eatable {
    public void work() { System.out.println("Human working"); }
    public void eat() { System.out.println("Human eating"); }
}

public class Robot implements Workable {
    public void work() { System.out.println("Robot working"); }
}
```

## 5. Dependency Inversion Principle (DIP)
High-level modules should not depend on low-level modules. Both should depend on abstractions.
### ❌ Violation:
# 
```
class MySQLDatabase {
    public void connect() {
        System.out.println("Connected to MySQL");
    }
}

class App {
    private MySQLDatabase db = new MySQLDatabase();

    public void start() {
        db.connect();
    }
}
```
###### Problem: App is tightly coupled to a specific database.
#
### ✅ DIP Applied:
# 
```
public interface Database {
    void connect();
}

public class MySQLDatabase implements Database {
    public void connect() {
        System.out.println("Connected to MySQL");
    }
}

public class App {
    private Database db;

    public App(Database db) {
        this.db = db;
    }

    public void start() {
        db.connect();
    }
}
```