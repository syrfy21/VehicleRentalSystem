# Vehicle Rental Management System

A console-based Java application demonstrating core Object-Oriented Programming principles for managing vehicle rentals, customers, and revenue.

## Compile Instructions

Navigate to the `src/` directory and compile all Java files:

```bash
javac *.java
```

## Run Instructions

Run the main entry point:

```bash
java Main
```

Or directly:

```bash
java RentalSystem
```

## Project Structure

```
src/
  Maintainable.java     - Interface for maintenance operations
  Vehicle.java          - Abstract base class for all vehicles
  Car.java              - Car subclass with seats and transmission
  Motorcycle.java       - Motorcycle subclass with engine CC and helmet
  Truck.java            - Truck subclass with max load and service fee
  Customer.java         - Customer management
  Rental.java           - Rental transaction association
  RentalSystem.java     - Main system with menu-driven interface
  Main.java             - Entry point
```

## Key OOP Features Demonstrated

- **Encapsulation**: All fields are private with validated getters/setters.
- **Inheritance**: Car, Motorcycle, and Truck extend abstract Vehicle.
- **Abstraction**: Vehicle is abstract with abstract `calculateRentalCost()` and `displayInfo()`.
- **Interfaces**: `Maintainable` interface implemented by all vehicle types.
- **Polymorphism (Inheritance)**: `ArrayList<Vehicle>` calls overridden methods through Vehicle references.
- **Polymorphism (Interface)**: `Maintainable` references used in `performVehicleMaintenance()`.
- **Overloading**: `findVehicles(String brand)` and `findVehicles(String brand, String model)`; overloaded Vehicle constructors.
- **Overriding**: Subclasses override rental calculation, display, and maintenance behavior.
- **Associations**: Rental references Customer and Vehicle objects.
- **Static/Final**: Shared fee constants (`INSURANCE_FEE`, `HELMET_FEE`, `SERVICE_FEE`).
- **this/super**: Explicit usage in constructors and maintenance methods.

## Menu Options

1. Add New Vehicle
2. Display All Vehicles
3. Search for Vehicle
4. Display Available Vehicles
5. Register New Customer
6. Display All Customers
7. Rent a Vehicle
8. Return a Vehicle
9. Display All Rentals
10. Display Customer Rental History
11. Display Total Revenue
12. Flag Vehicle for Maintenance
13. Display Vehicles Requiring Maintenance
14. Perform Vehicle Maintenance
0. Exit

## Business Rules

- Only available, non-maintenance vehicles can be rented.
- Rental days must be positive.
- Vehicle IDs and Customer IDs must be unique.
- Only active rentals can be returned; a rental cannot be completed twice.
- Maintenance cannot be performed on a vehicle with an active rental.
- A vehicle flagged for maintenance cannot be rented.
