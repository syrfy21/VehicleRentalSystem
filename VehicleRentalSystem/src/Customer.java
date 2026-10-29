import java.util.ArrayList;

public class Customer {
    private String customerId;
    private String fullName;
    private String phone;
    private String email;
    private String licenseNumber;

    public Customer(String customerId, String fullName, String phone, String email, String licenseNumber) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.licenseNumber = licenseNumber;
    }

    public String getCustomerId() { return customerId; }
    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getLicenseNumber() { return licenseNumber; }

    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }

    public void displayInfo() {
        System.out.println("Customer - ID: " + customerId + ", Name: " + fullName
            + ", Phone: " + phone + ", Email: " + email + ", License: " + licenseNumber);
    }
}
