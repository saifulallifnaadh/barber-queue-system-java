// Booking.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Booking {
    private String bookingId;
    private Customer customer;
    private Services service;
    private LocalDateTime bookingTime;
    private int queueNumber;
    
    public Booking(String bookingId, Customer customer, Services service, int queueNumber) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.service = service;
        this.bookingTime = LocalDateTime.now();
        this.queueNumber = queueNumber;
    }
    
    public void displayBooking() {
        System.out.println("\n=== BOOKING DETAILS ===");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Queue Number: " + queueNumber);
        customer.displayInfo();
        if (service != null) {
            service.displayService();
        }
        System.out.println("Booking Time: " + bookingTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }
    
    // Getters
    public String getBookingId() { return bookingId; }
    public Customer getCustomer() { return customer; }
    public int getQueueNumber() { return queueNumber; }
}