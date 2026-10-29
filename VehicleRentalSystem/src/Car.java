public class Car extends Vehicle {
    private int seats;
    private String transmission;
    public static final double INSURANCE_FEE = 15.0;

    public Car(String vehicleId, String brand, String model, int year, double dailyPrice, int seats, String transmission) {
        super(vehicleId, brand, model, year, dailyPrice);
        this.seats = seats;
        this.transmission = transmission;
    }

    public int getSeats() { return seats; }
    public String getTransmission() { return transmission; }

    @Override
    public double calculateRentalCost(int days, boolean optionalService) {
        double base = getDailyPrice() * days;
        if (optionalService) {
            base += INSURANCE_FEE * days;
        }
        return base;
    }

    @Override
    public void displayInfo() {
        System.out.println("Car - ID: " + getVehicleId() + ", Brand: " + getBrand() + ", Model: " + getModel()
            + ", Year: " + getYear() + ", Price/Day: $" + getDailyPrice()
            + ", Seats: " + seats + ", Transmission: " + transmission
            + ", Available: " + (isAvailable() ? "Yes" : "No")
            + ", Maintenance: " + (isMaintenanceRequired() ? "Required" : "OK"));
    }

    @Override
    public void performMaintenance() {
        if (needsMaintenance()) {
            super.performMaintenance();
            System.out.println("Car maintenance completed: oil change, tire check.");
        }
    }

    @Override
    public boolean needsMaintenance() {
        return isMaintenanceRequired();
    }
}
