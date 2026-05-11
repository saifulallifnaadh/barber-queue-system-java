// Order.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private String orderId;
    private Customer customer;
    private Services[] services;
    private Product[] products;
    private int serviceCount;
    private int productCount;
    private double totalAmount;
    private LocalDateTime orderTime;
    private int queueNumber;
    
    public Order(String orderId, Customer customer, int queueNumber) {
        this.orderId = orderId;
        this.customer = customer;
        this.services = new Services[10];
        this.products = new Product[10];
        this.serviceCount = 0;
        this.productCount = 0;
        this.totalAmount = 0.0;
        this.orderTime = LocalDateTime.now();
        this.queueNumber = queueNumber;
    }
    
    public void addService(Services service) {
        if (serviceCount < 10) {
            services[serviceCount] = service;
            serviceCount++;
            totalAmount += service.getServicePrice();
        }
    }
    
    public void addProduct(Product product) {
        if (productCount < 10) {
            products[productCount] = product;
            productCount++;
            totalAmount += product.getPrice();
        }
    }
    
    public void displayOrder() {
        System.out.println("\n=== ORDER DETAILS ===");
        System.out.println("Order ID: " + orderId);
        System.out.println("Queue Number: " + queueNumber);
        customer.displayInfo();
        
        System.out.println("\nServices:");
        for (int i = 0; i < serviceCount; i++) {
            services[i].displayService();
        }
        
        System.out.println("\nProducts:");
        for (int i = 0; i < productCount; i++) {
            products[i].displayProduct();
        }
        
        System.out.println("\nTotal Amount: RM" + totalAmount);
        System.out.println("Order Time: " + orderTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }
    
    // Getters
    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Services[] getServices() { return services; }
    public Product[] getProducts() { return products; }
    public int getServiceCount() { return serviceCount; }
    public int getProductCount() { return productCount; }
    public double getTotalAmount() { return totalAmount; }
    public int getQueueNumber() { return queueNumber; }
}