public class Motorcycle extends Vehicle {
    private int engineCC;
    private boolean helmetIncluded;
    public static final double HELMET_FEE = 5.0;

    public Motorcycle(String vehicleId, String brand, String model, int year, double dailyPrice, int engineCC, boolean helmetIncluded) {
        super(vehicleId, brand, model, year, dailyPrice);
        this.engineCC = engineCC;
        this.helmetIncluded = helmetIncluded;
    }

    public int getEngineCC() { return engineCC; }
    public boolean isHelmetIncluded() { return helmetIncluded; }

    @Override
    public double calculateRentalCost(int days, boolean optionalService) {
        double base = getDailyPrice() * days;
        if (optionalService) {
            base += HELMET_FEE * days;
        }
        return base;
    }

    @Override
    public void displayInfo() {
        System.out.println("Motorcycle - ID: " + getVehicleId() + ", Brand: " + getBrand() + ", Model: " + getModel()
            + ", Year: " + getYear() + ", Price/Day: $" + getDailyPrice()
            + ", Engine: " + engineCC + " CC, Helmet: " + (helmetIncluded ? "Yes" : "No")
            + ", Available: " + (isAvailable() ? "Yes" : "No")
            + ", Maintenance: " + (isMaintenanceRequired() ? "Required" : "OK"));
    }

    @Override
    public void performMaintenance() {
        if (needsMaintenance()) {
            super.performMaintenance();
            System.out.println("Motorcycle maintenance completed: chain lubrication, brake inspection.");
        }
    }

    @Override
    public boolean needsMaintenance() {
        return isMaintenanceRequired();
    }
}
