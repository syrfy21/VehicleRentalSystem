public class Rental {
    private String rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private int rentalDays;
    private double totalCost;
    private boolean completed;

    public Rental(String rentalId, Customer customer, Vehicle vehicle, int rentalDays, double totalCost) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.totalCost = totalCost;
        this.completed = false;
    }

    public String getRentalId() { return rentalId; }
    public Customer getCustomer() { return customer; }
    public Vehicle getVehicle() { return vehicle; }
    public int getRentalDays() { return rentalDays; }
    public double getTotalCost() { return totalCost; }
    public boolean isCompleted() { return completed; }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void displayInfo() {
        System.out.println("Rental - ID: " + rentalId + ", Customer: " + customer.getFullName()
            + ", Vehicle: " + vehicle.getVehicleId() + ", Days: " + rentalDays
            + ", Cost: $" + totalCost + ", Status: " + (completed ? "Completed" : "Active"));
    }
}
