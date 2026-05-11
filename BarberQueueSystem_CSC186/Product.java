// Product.java
public class Product {
    private String productId;
    private String productName;
    private double price;
    private String description;
    
    public Product(String productId, String productName, double price, String description) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
    }
    
    public void displayProduct() {
        System.out.println("Product: " + productName + " | Price: RM" + price + " | " + description);
    }
    
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
}