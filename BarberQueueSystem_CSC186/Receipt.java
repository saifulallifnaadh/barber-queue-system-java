// Receipt.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class Receipt {
    private String receiptId;
    private Order order;
    private PaymentMethod paymentMethod;
    private LocalDateTime printTime;
    
    public Receipt(String receiptId, Order order, PaymentMethod paymentMethod) {
        this.receiptId = receiptId;
        this.order = order;
        this.paymentMethod = paymentMethod;
        this.printTime = LocalDateTime.now();
    }
    
    public void printReceipt() {
        // Print receipt to screen
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           AHMAD'S BARBER SHOP");
        System.out.println("              No. 123, Jalan Raya");
        System.out.println("                 Kuala Lumpur");
        System.out.println("              Tel: 03-1234-5678");
        System.out.println("=".repeat(50));
        
        System.out.println("Receipt ID: " + receiptId);
        System.out.println("Date: " + printTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        System.out.println("Queue Number: " + order.getQueueNumber());
        System.out.println("-".repeat(50));
        
        // Customer Info
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Customer ID: " + order.getCustomer().getCustomerId());
        System.out.println("Phone: " + order.getCustomer().getPhone());
        System.out.println("Email: " + order.getCustomer().getEmail());
        System.out.println("-".repeat(50));
        
        // Order Details
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("\nSERVICES:");
        
        for (int i = 0; i < order.getServiceCount(); i++) {
            Services service = order.getServices()[i];
            System.out.println(service.getServiceName() + " - RM" + service.getServicePrice());
        }
        
        System.out.println("\nPRODUCTS:");
        for (int i = 0; i < order.getProductCount(); i++) {
            Product product = order.getProducts()[i];
            System.out.println(product.getProductName() + " - RM" + product.getPrice());
        }
        
        System.out.println("-".repeat(50));
        System.out.println("TOTAL AMOUNT: RM" + order.getTotalAmount());
        System.out.println("Payment Method: " + paymentMethod.getPaymentType());
        System.out.println("Instructions: " + paymentMethod.getInstructions());
        System.out.println("-".repeat(50));
        System.out.println("         THANK YOU FOR YOUR VISIT!");
        System.out.println();
        System.out.println("    PLEASE WAIT INSIDE THE SHOP. YOUR");
        System.out.println("      NUMBER WILL BE CALLED SHORTLY.");
        System.out.println("   IF NO RESPONSE AFTER 3 CALLS, YOUR");
        System.out.println("         QUEUE NUMBER WILL BE CANCELLED");
        System.out.println("=".repeat(50));
        
        // Save to file
        saveReceiptToFile();
    }
    
    private void saveReceiptToFile() {
        try {
            File dir = new File("receipts");
            if (!dir.exists()) {
                dir.mkdir();
            }
            
            String filename = "receipts/receipt_" + receiptId + "_" + 
                             printTime.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";
            
            FileWriter writer = new FileWriter(filename);
            
            // Write all receipt content to file
            writer.write("=".repeat(50) + "\n");
            writer.write("           AHMAD'S BARBER SHOP\n");
            writer.write("              No. 123, Jalan Raya\n");
            writer.write("                 Kuala Lumpur\n");
            writer.write("              Tel: 03-1234-5678\n");
            writer.write("=".repeat(50) + "\n");
            
            writer.write("Receipt ID: " + receiptId + "\n");
            writer.write("Date: " + printTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "\n");
            writer.write("Queue Number: " + order.getQueueNumber() + "\n");
            writer.write("-".repeat(50) + "\n");
            
            writer.write("Customer: " + order.getCustomer().getName() + "\n");
            writer.write("Customer ID: " + order.getCustomer().getCustomerId() + "\n");
            writer.write("Phone: " + order.getCustomer().getPhone() + "\n");
            writer.write("Email: " + order.getCustomer().getEmail() + "\n");
            writer.write("-".repeat(50) + "\n");
            
            writer.write("Order ID: " + order.getOrderId() + "\n");
            writer.write("\nSERVICES:\n");
            
            for (int i = 0; i < order.getServiceCount(); i++) {
                Services service = order.getServices()[i];
                writer.write(service.getServiceName() + " - RM" + service.getServicePrice() + "\n");
            }
            
            writer.write("\nPRODUCTS:\n");
            for (int i = 0; i < order.getProductCount(); i++) {
                Product product = order.getProducts()[i];
                writer.write(product.getProductName() + " - RM" + product.getPrice() + "\n");
            }
            
            writer.write("-".repeat(50) + "\n");
            writer.write("TOTAL AMOUNT: RM" + order.getTotalAmount() + "\n");
            writer.write("Payment Method: " + paymentMethod.getPaymentType() + "\n");
            writer.write("Instructions: " + paymentMethod.getInstructions() + "\n");
            writer.write("-".repeat(50) + "\n");
            writer.write("         THANK YOU FOR YOUR VISIT!\n");
            writer.write("\n");
            writer.write("    PLEASE WAIT INSIDE THE SHOP. YOUR\n");
            writer.write("      NUMBER WILL BE CALLED SHORTLY.\n");
            writer.write("   IF NO RESPONSE AFTER 3 CALLS, YOUR\n");
            writer.write("         QUEUE NUMBER WILL BE CANCELLED\n");
            writer.write("=".repeat(50) + "\n");
            
            writer.close();
            
        } catch (IOException e) {
            // Silent error handling
        }
    }
}