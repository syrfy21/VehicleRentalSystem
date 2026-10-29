public abstract class Vehicle implements Maintainable {
    private String vehicleId;
    private String brand;
    private String model;
    private int year;
    private double dailyPrice;
    private boolean available;
    private boolean maintenanceRequired;

    public static final double INSURANCE_FEE = 0.0; // placeholder for common constants

    public Vehicle(String vehicleId, String brand, String model, int year, double dailyPrice) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.dailyPrice = dailyPrice;
        this.available = true;
        this.maintenanceRequired = false;
    }

    // Overloaded constructor
    public Vehicle(String vehicleId, String brand, String model, int year) {
        this(vehicleId, brand, model, year, 50.0);
    }

    public String getVehicleId() { return vehicleId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getDailyPrice() { return dailyPrice; }
    public boolean isAvailable() { return available; }
    public boolean isMaintenanceRequired() { return maintenanceRequired; }

    public void setDailyPrice(double price) {
        if (price > 0) {
            this.dailyPrice = price;
        }
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setMaintenanceRequired(boolean required) {
        this.maintenanceRequired = required;
    }

    public abstract double calculateRentalCost(int days, boolean optionalService);
    public abstract void displayInfo();

    @Override
    public void performMaintenance() {
        if (maintenanceRequired) {
            maintenanceRequired = false;
            System.out.println("Maintenance performed for vehicle: " + vehicleId);
        }
    }

    @Override
    public boolean needsMaintenance() {
        return maintenanceRequired;
    }

    public boolean canBeRented() {
        return available && !maintenanceRequired;
    }
}
