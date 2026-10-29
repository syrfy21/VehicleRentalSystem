Vehicle Rental Management System - Design Report
==================================================

1. Introduction
----------------
This report explains the object-oriented design choices made in the Vehicle Rental Management System console application. The system manages cars, motorcycles, trucks, customers, rentals, returns, revenue, and vehicle maintenance while demonstrating abstraction, inheritance, polymorphism, interfaces, encapsulation, method overloading, and associations.

2. Abstraction and Abstract Classes
-----------------------------------
The `Vehicle` class is declared `abstract` because it represents a general concept that should not be instantiated directly. It declares abstract methods `calculateRentalCost(int days, boolean optionalService)` and `displayInfo()`. This forces subclasses (`Car`, `Motorcycle`, `Truck`) to provide concrete implementations, ensuring that each vehicle category calculates costs and displays information according to its specific business rules. Using an abstract class allows shared fields (ID, brand, model, year, daily price, availability, maintenance flag) and common behavior (`performMaintenance`, `needsMaintenance`, `canBeRented`) to be inherited, reducing duplication.

3. Abstract Classes vs Interfaces
----------------------------------
An abstract class (`Vehicle`) defines a partial implementation with shared state and behavior, making it ideal for inheritance relationships where subclasses are specialized versions of the same concept. An interface (`Maintainable`) defines only a contract (method signatures) without any implementation or state. The assignment requires demonstrating both forms of polymorphism: inheritance-based polymorphism through the abstract `Vehicle` class and interface-based polymorphism through `Maintainable`. The `Maintainable` interface is implemented by all three vehicle subclasses independently, allowing maintenance operations to be performed through `Maintainable` references rather than only through `Vehicle` references. This satisfies the mandatory interface requirement and shows that interfaces can be used to model cross-cutting capabilities (like maintenance) that are not tied to a single inheritance hierarchy.

4. Polymorphism (Inheritance-Based)
------------------------------------
The `RentalSystem` stores all vehicles in an `ArrayList<Vehicle>`. When the system iterates over this collection and calls `displayInfo()` or `calculateRentalCost()`, the Java Virtual Machine invokes the overridden method in the actual subclass (`Car`, `Motorcycle`, or `Truck`) at runtime. This eliminates the need for conditional logic (`if/else` or `switch`) based on vehicle type strings, which aligns with the assignment's constraint to avoid type-string-based logic. This is inheritance-based polymorphism: the same message sent to different objects produces different behaviors.

5. Polymorphism (Interface-Based)
----------------------------------
The `performVehicleMaintenance()` method in `RentalSystem` demonstrates interface-based polymorphism by assigning a `Vehicle` reference to a `Maintainable` variable (`Maintainable maintainable = v;`) and then calling `performMaintenance()` on that reference. Because `Car`, `Motorcycle`, and `Truck` all implement `Maintainable`, the correct subclass-specific maintenance behavior is invoked (e.g., oil change for cars, chain lubrication for motorcycles, engine service for trucks). The system can check `needsMaintenance()` through the same interface reference (`m.needsMaintenance()`), confirming that different vehicle types respond correctly when treated only as `Maintainable` objects.

6. Overloading and Overriding
-----------------------------
Overloading is demonstrated through:
- Two constructors in `Vehicle`: one with a daily price parameter and one without (defaulting to $50/day).
- Overloaded search operations: `findVehicles(String brand)` and `findVehicles(String brand, String model)` return collections of matching vehicles based on different criteria.

Overriding is demonstrated through:
- `calculateRentalCost()` overridden in each subclass: Car adds optional insurance ($15/day), Motorcycle adds optional helmet ($5/day), and Truck adds a fixed $50 service fee per rental regardless of the optional parameter.
- `displayInfo()` overridden to show subclass-specific attributes (seats/transmission, engine CC/helmet, max load).
- `performMaintenance()` overridden to provide subclass-specific maintenance messages.

7. Encapsulation, Constructors, and Access Modifiers
-----------------------------------------------------
All fields are private (`private`), enforcing encapsulation. Access is controlled through public getters and validated setters (e.g., `setDailyPrice` rejects non-positive values). Constructors initialize all required fields. The overloaded constructor in `Vehicle` provides flexibility while maintaining consistent initialization through `this()` delegation.

8. Associations and Collections
-------------------------------
Associations are modeled through object references:
- `Rental` contains a `Customer` reference (`customer`) and a `Vehicle` reference (`vehicle`).
- `RentalSystem` manages collections (`ArrayList<Vehicle>`, `ArrayList<Customer>`, `ArrayList<Rental>`) providing the storage mechanism required by the assignment.

Business rules enforce associations correctly:
- A rental cannot exist without a registered customer and an existing vehicle.
- A vehicle cannot have more than one active rental (`findActiveRentalByVehicle`).
- A customer can have multiple active rentals.

9. Use of `this`, `super`, `static`, and `final`
-----------------------------------------------
- `this` is used explicitly in constructors and setters (`this.vehicleId = vehicleId`) and in the overloaded `Vehicle` constructor (`this(...)`).
- `super` is used in subclass constructors (`super(vehicleId, brand, ...)`) and in overridden `performMaintenance()` methods (`super.performMaintenance()`) to reuse base behavior before adding subclass-specific actions.
- `static final` constants (`INSURANCE_FEE`, `HELMET_FEE`, `SERVICE_FEE`) represent shared, immutable fees that do not change for any instance.

10. Validation and Exception Handling
-------------------------------------
Input validation is applied consistently:
- Vehicle ID uniqueness is checked before adding.
- Customer ID uniqueness is enforced.
- Rental days must be positive (`days <= 0` is rejected).
- Unknown customers or vehicles are rejected.
- Unavailable vehicles (either rented or under maintenance) cannot be rented.
- Active rentals cannot be completed twice.
- Maintenance cannot be flagged or performed on vehicles with active rentals.
- `NumberFormatException` is caught when parsing menu choices or numeric inputs to prevent unexpected termination.

11. Maintenance Rules Integration
---------------------------------
The maintenance module fully satisfies the assignment requirements:
- Each vehicle tracks `maintenanceRequired` independently of rental status.
- `flagVehicleMaintenance()` checks for active rentals before flagging.
- `displayVehiclesRequiringMaintenance()` uses `needsMaintenance()` (interface method).
- `performVehicleMaintenance()` uses a `Maintainable` reference and checks for active rentals before clearing the flag.
- After maintenance (`performMaintenance()`), the vehicle can be rented again if no active rental exists.

12. Conclusion
--------------
The system demonstrates a well-structured Java application that uses inheritance for specialization, interfaces for cross-cutting capabilities, polymorphism to eliminate conditional type logic, and encapsulation to protect internal state. The design emphasizes quality and maintainability over simple functionality, meeting all assessment criteria for the Object-Oriented Programming assignment.
