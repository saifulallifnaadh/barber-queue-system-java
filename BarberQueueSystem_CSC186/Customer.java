// Customer.java
public class Customer extends User {
    private String customerId;
    private String email;
    
    public Customer(String name, String phone, String username, String password, String customerId, String email) {
        super(name, phone, username, password);
        this.customerId = customerId;
        this.email = email;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("Customer: " + name + " | ID: " + customerId + " | Email: " + email);
    }
    
    public String getCustomerId() { return customerId; }
    public String getEmail() { return email; }
}