public class Truck extends Vehicle {
    private double maxLoad;
    public static final double SERVICE_FEE = 50.0;

    public Truck(String vehicleId, String brand, String model, int year, double dailyPrice, double maxLoad) {
        super(vehicleId, brand, model, year, dailyPrice);
        this.maxLoad = maxLoad;
    }

    public double getMaxLoad() { return maxLoad; }

    @Override
    public double calculateRentalCost(int days, boolean optionalService) {
        // Truck ignores optionalService boolean; always adds fixed service fee
        return (getDailyPrice() * days) + SERVICE_FEE;
    }

    @Override
    public void displayInfo() {
        System.out.println("Truck - ID: " + getVehicleId() + ", Brand: " + getBrand() + ", Model: " + getModel()
            + ", Year: " + getYear() + ", Price/Day: $" + getDailyPrice()
            + ", Max Load: " + maxLoad + " tons"
            + ", Available: " + (isAvailable() ? "Yes" : "No")
            + ", Maintenance: " + (isMaintenanceRequired() ? "Required" : "OK"));
    }

    @Override
    public void performMaintenance() {
        if (needsMaintenance()) {
            super.performMaintenance();
            System.out.println("Truck maintenance completed: engine service, brake check, load inspection.");
        }
    }

    @Override
    public boolean needsMaintenance() {
        return isMaintenanceRequired();
    }
}
