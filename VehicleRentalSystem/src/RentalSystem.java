import java.util.ArrayList;
import java.util.Scanner;

public class RentalSystem {
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Customer> customers;
    private ArrayList<Rental> rentals;
    private Scanner scanner;

    public RentalSystem() {
        this.vehicles = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter choice: ");
            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1: addVehicle(); break;
                    case 2: displayAllVehicles(); break;
                    case 3: searchVehicle(); break;
                    case 4: displayAvailableVehicles(); break;
                    case 5: registerCustomer(); break;
                    case 6: displayAllCustomers(); break;
                    case 7: rentVehicle(); break;
                    case 8: returnVehicle(); break;
                    case 9: displayAllRentals(); break;
                    case 10: displayCustomerRentalHistory(); break;
                    case 11: displayTotalRevenue(); break;
                    case 12: flagVehicleMaintenance(); break;
                    case 13: displayVehiclesRequiringMaintenance(); break;
                    case 14: performVehicleMaintenance(); break;
                    case 0: running = false; System.out.println("Exiting system. Goodbye!"); break;
                    default: System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n===== Vehicle Rental Management System =====");
        System.out.println("1. Add New Vehicle");
        System.out.println("2. Display All Vehicles");
        System.out.println("3. Search for Vehicle");
        System.out.println("4. Display Available Vehicles");
        System.out.println("5. Register New Customer");
        System.out.println("6. Display All Customers");
        System.out.println("7. Rent a Vehicle");
        System.out.println("8. Return a Vehicle");
        System.out.println("9. Display All Rentals");
        System.out.println("10. Display Customer Rental History");
        System.out.println("11. Display Total Revenue");
        System.out.println("12. Flag Vehicle for Maintenance");
        System.out.println("13. Display Vehicles Requiring Maintenance");
        System.out.println("14. Perform Vehicle Maintenance");
        System.out.println("0. Exit");
    }

    // Overloaded search methods for display/search
    // Overloaded search/display operations
    private ArrayList<Vehicle> findVehicles(String brand) {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.getBrand().equalsIgnoreCase(brand)) {
                result.add(v);
            }
        }
        return result;
    }

    private ArrayList<Vehicle> findVehicles(String brand, String model) {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.getBrand().equalsIgnoreCase(brand) && v.getModel().equalsIgnoreCase(model)) {
                result.add(v);
            }
        }
        return result;
    }

    private Vehicle findVehicleById(String id) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                return v;
            }
        }
        return null;
    }

    private Customer findCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    private Rental findActiveRentalByVehicle(Vehicle v) {
        for (Rental r : rentals) {
            if (r.getVehicle().equals(v) && !r.isCompleted()) {
                return r;
            }
        }
        return null;
    }

    private Rental findRentalById(String id) {
        for (Rental r : rentals) {
            if (r.getRentalId().equalsIgnoreCase(id)) {
                return r;
            }
        }
        return null;
    }

    private void addVehicle() {
        System.out.println("\n--- Add New Vehicle ---");
        System.out.print("Type (1=Car, 2=Motorcycle, 3=Truck): ");
        int type = Integer.parseInt(scanner.nextLine());
        System.out.print("Vehicle ID: ");
        String vid = scanner.nextLine();
        if (findVehicleById(vid) != null) {
            System.out.println("Error: Vehicle ID already exists.");
            return;
        }
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Daily Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Vehicle v = null;
        if (type == 1) {
            System.out.print("Seats: ");
            int seats = Integer.parseInt(scanner.nextLine());
            System.out.print("Transmission: ");
            String trans = scanner.nextLine();
            v = new Car(vid, brand, model, year, price, seats, trans);
        } else if (type == 2) {
            System.out.print("Engine CC: ");
            int cc = Integer.parseInt(scanner.nextLine());
            System.out.print("Helmet included? (true/false): ");
            boolean helmet = Boolean.parseBoolean(scanner.nextLine());
            v = new Motorcycle(vid, brand, model, year, price, cc, helmet);
        } else if (type == 3) {
            System.out.print("Max Load (tons): ");
            double load = Double.parseDouble(scanner.nextLine());
            v = new Truck(vid, brand, model, year, price, load);
        } else {
            System.out.println("Invalid type.");
            return;
        }
        vehicles.add(v);
        System.out.println("Vehicle added successfully.");
    }

    private void displayAllVehicles() {
        System.out.println("\n--- All Vehicles ---");
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered.");
            return;
        }
        for (Vehicle v : vehicles) {
            v.displayInfo();
        }
    }

    // Overloaded search operations
    private void searchVehicle() {
        System.out.println("Search by (1=ID, 2=Brand, 3=Model, 4=Brand+Model): ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            System.out.print("Enter Vehicle ID: ");
            String id = scanner.nextLine();
            Vehicle v = findVehicleById(id);
            if (v != null) v.displayInfo();
            else System.out.println("Vehicle not found.");
        } else if (choice == 2) {
            System.out.print("Enter Brand: ");
            String brand = scanner.nextLine();
            ArrayList<Vehicle> result = findVehicles(brand);
            if (result.isEmpty()) System.out.println("No vehicles found for brand: " + brand);
            for (Vehicle v : result) v.displayInfo();
        } else if (choice == 3) {
            System.out.print("Enter Model: ");
            String model = scanner.nextLine();
            // Overloaded search demonstration: using findVehicles with brand empty and model
            boolean found = false;
            for (Vehicle v : vehicles) {
                if (v.getModel().equalsIgnoreCase(model)) {
                    v.displayInfo();
                    found = true;
                }
            }
            if (!found) System.out.println("No vehicles found for model: " + model);
        } else if (choice == 4) {
            System.out.print("Enter Brand: ");
            String brand = scanner.nextLine();
            System.out.print("Enter Model: ");
            String model = scanner.nextLine();
            ArrayList<Vehicle> result = findVehicles(brand, model);
            if (result.isEmpty()) System.out.println("No vehicles found for brand: " + brand + " and model: " + model);
            for (Vehicle v : result) v.displayInfo();
        } else {
            System.out.println("Invalid search option.");
        }
    }

    private void displayAvailableVehicles() {
        System.out.println("\n--- Available Vehicles ---");
        boolean found = false;
        for (Vehicle v : vehicles) {
            if (v.canBeRented()) {
                v.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No vehicles available for rent.");
    }

    private void registerCustomer() {
        System.out.println("\n--- Register Customer ---");
        System.out.print("Customer ID: ");
        String cid = scanner.nextLine();
        if (findCustomerById(cid) != null) {
            System.out.println("Error: Customer ID already exists.");
            return;
        }
        System.out.print("Full Name: ");
        String name = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("License Number: ");
        String license = scanner.nextLine();
        customers.add(new Customer(cid, name, phone, email, license));
        System.out.println("Customer registered successfully.");
    }

    private void displayAllCustomers() {
        System.out.println("\n--- All Customers ---");
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
            return;
        }
        for (Customer c : customers) {
            c.displayInfo();
        }
    }

    private void rentVehicle() {
        System.out.println("\n--- Rent a Vehicle ---");
        System.out.print("Customer ID: ");
        String cid = scanner.nextLine();
        Customer customer = findCustomerById(cid);
        if (customer == null) {
            System.out.println("Error: Customer not found.");
            return;
        }
        System.out.print("Vehicle ID: ");
        String vid = scanner.nextLine();
        Vehicle vehicle = findVehicleById(vid);
        if (vehicle == null) {
            System.out.println("Error: Vehicle not found.");
            return;
        }
        if (!vehicle.canBeRented()) {
            if (findActiveRentalByVehicle(vehicle) != null) {
                System.out.println("Error: Vehicle already has an active rental.");
            } else if (vehicle.isMaintenanceRequired()) {
                System.out.println("Error: Vehicle requires maintenance and cannot be rented.");
            } else {
                System.out.println("Error: Vehicle is not available for rent.");
            }
            return;
        }
        System.out.print("Rental Days: ");
        int days = Integer.parseInt(scanner.nextLine());
        if (days <= 0) {
            System.out.println("Error: Rental days must be positive.");
            return;
        }

        boolean optionalService = false;
        if (vehicle instanceof Car) {
            System.out.print("Include insurance? (true/false): ");
            optionalService = Boolean.parseBoolean(scanner.nextLine());
        } else if (vehicle instanceof Motorcycle) {
            System.out.print("Include helmet rental? (true/false): ");
            optionalService = Boolean.parseBoolean(scanner.nextLine());
        } else if (vehicle instanceof Truck) {
            System.out.println("Truck rental includes fixed $50 service fee automatically.");
            optionalService = false;
        }

        double cost = vehicle.calculateRentalCost(days, optionalService);
        String rentalId = "R" + System.currentTimeMillis();
        Rental rental = new Rental(rentalId, customer, vehicle, days, cost);
        rentals.add(rental);
        vehicle.setAvailable(false);
        System.out.println("Rental created. ID: " + rentalId + ", Cost: $" + cost);
    }

    private void returnVehicle() {
        System.out.println("\n--- Return a Vehicle ---");
        System.out.print("Rental ID: ");
        String rid = scanner.nextLine();
        Rental rental = findRentalById(rid);
        if (rental == null) {
            System.out.println("Error: Rental not found.");
            return;
        }
        if (rental.isCompleted()) {
            System.out.println("Error: Rental is already completed.");
            return;
        }
        rental.setCompleted(true);
        rental.getVehicle().setAvailable(true);
        System.out.println("Vehicle returned. Rental completed.");
    }

    private void displayAllRentals() {
        System.out.println("\n--- All Rentals ---");
        if (rentals.isEmpty()) {
            System.out.println("No rentals recorded.");
            return;
        }
        for (Rental r : rentals) {
            r.displayInfo();
        }
    }

    private void displayCustomerRentalHistory() {
        System.out.println("\n--- Customer Rental History ---");
        System.out.print("Customer ID: ");
        String cid = scanner.nextLine();
        Customer customer = findCustomerById(cid);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        boolean found = false;
        for (Rental r : rentals) {
            if (r.getCustomer().getCustomerId().equalsIgnoreCase(cid)) {
                r.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No rentals found for this customer.");
    }

    private void displayTotalRevenue() {
        System.out.println("\n--- Total Revenue ---");
        double total = 0.0;
        for (Rental r : rentals) {
            if (r.isCompleted()) {
                total += r.getTotalCost();
            }
        }
        System.out.println("Total revenue from completed rentals: $" + total);
    }

    private void flagVehicleMaintenance() {
        System.out.println("\n--- Flag Vehicle for Maintenance ---");
        System.out.print("Vehicle ID: ");
        String vid = scanner.nextLine();
        Vehicle v = findVehicleById(vid);
        if (v == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        if (findActiveRentalByVehicle(v) != null) {
            System.out.println("Error: Vehicle has an active rental. Cannot flag for maintenance.");
            return;
        }
        v.setMaintenanceRequired(true);
        System.out.println("Vehicle flagged for maintenance.");
    }

    private void displayVehiclesRequiringMaintenance() {
        System.out.println("\n--- Vehicles Requiring Maintenance ---");
        boolean found = false;
        for (Vehicle v : vehicles) {
            if (v.needsMaintenance()) {
                v.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No vehicles require maintenance.");
    }

    private void performVehicleMaintenance() {
        System.out.println("\n--- Perform Vehicle Maintenance ---");
        System.out.print("Vehicle ID: ");
        String vid = scanner.nextLine();
        Vehicle v = findVehicleById(vid);
        if (v == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        if (findActiveRentalByVehicle(v) != null) {
            System.out.println("Error: Vehicle has an active rental. Cannot perform maintenance.");
            return;
        }
        // Demonstrate interface polymorphism: use Maintainable reference
        Maintainable maintainable = v;
        maintainable.performMaintenance();
        System.out.println("Maintenance completed for vehicle: " + vid);
    }

    // Demonstrate interface-based polymorphism through Maintainable references
    private void checkMaintenanceViaInterface() {
        System.out.println("\n--- Check Maintenance via Maintainable Reference ---");
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles.");
            return;
        }
        for (Vehicle v : vehicles) {
            Maintainable m = v; // interface polymorphism
            System.out.println("Vehicle: " + v.getVehicleId() + ", Needs Maintenance: " + m.needsMaintenance());
        }
    }
}
