import java.util.Scanner;
import javax.swing.JOptionPane;


public class BarberQueueSystem {
    // Arrays to store data
    private static Product[] products = new Product[10];
    private static Services[] services = new Services[10];
    private static Order[] orders = new Order[100];
    private static Booking[] bookings = new Booking[100];
    private static Admin[] admins = new Admin[5];
    private static Customer[] customers = new Customer[50];
    
    // Counters to track array usage
    private static int productCount = 0;
    private static int serviceCount = 0;
    private static int orderCount = 0;
    private static int bookingCount = 0;
    private static int adminCount = 0;
    private static int customerCount = 0;
    private static int queueCounter = 1900;
    
    //scanner for user input
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;
    
    public static void main(String[] args) {
        initializeData();
        
        System.out.println("=== BARBER SHOP QUEUE SYSTEM ===");
        
        //main system
        while (true) {
            currentUser = null; //reset current user
            
            //login loop
            while (currentUser == null) {
                displayLoginMenu();
                int choice = getChoice();
                
                if (choice == 1) {
                    login();
                } else if (choice == 2) {
                    createAccount();
                } else if (choice == 3) {
                    JOptionPane.showMessageDialog(null, "Thank you! System closed.");
                    return; //exit system
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
            
            

            // Main menu based on user type
            if (currentUser instanceof Admin) {
                adminMenu();
            } else if (currentUser instanceof Customer) {
                customerMenu();
            }
        }
    }
    
    private static void initializeData() {
        //Initialize sample products
        products[0] = new Product("P001", "Shampoo Head & Shoulders", 25.90, "Anti-dandruff shampoo");
        products[1] = new Product("P002", "Hair Wax Gatsby", 15.50, "Strong hold hair wax");
        products[2] = new Product("P003", "Hair Oil Coconut", 12.00, "Natural coconut hair oil");
        products[3] = new Product("P004", "Beard Oil", 22.00, "Premium beard conditioning oil");
        productCount = 4;
        
        // Initialize sample services
        services[0] = new Services("S001", "Basic Haircut", 15.00, 30);
        services[1] = new Services("S002", "Haircut + Wash", 25.00, 45);
        services[2] = new Services("S003", "Hair Styling", 35.00, 60);
        services[3] = new Services("S004", "Beard Trim", 10.00, 20);
        services[4] = new Services("S005", "Facial Treatment", 50.00, 90);
        serviceCount = 5;
        
        // Initialize default admin account
        admins[0] = new Admin("Ahmad", "013-456-7890", "admin", "admin123", "ADMIN001");
        adminCount = 1;
    }
    
    private static void displayLoginMenu() {
        System.out.println("\n=== LOGIN MENU ===");
        System.out.println("1. Login");
        System.out.println("2. Create New Account");
        System.out.println("3. Exit");
        System.out.print("Choose (1-3): ");
    }
    
    private static int getChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return choice;
    }
    
    private static void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        //check account admin
        for (int i = 0; i < adminCount; i++) {
            if (admins[i].getUsername().equals(username) && admins[i].getPassword().equals(password)) {
                currentUser = admins[i];
                System.out.println("Login successful! Welcome Admin " + admins[i].getName());
                return;
            }
        }
        
        //check account customer
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getUsername().equals(username) && customers[i].getPassword().equals(password)) {
                currentUser = customers[i];
                System.out.println("Login successful! Welcome " + customers[i].getName());
                return;
            }
        }
        
        System.out.println("Invalid username or password. Please try again.");
    }
    
    private static void createAccount() {
        System.out.println("\n=== CREATE NEW CUSTOMER ACCOUNT ===");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        //check kalau username dah ada
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getUsername().equals(username)) {
                System.out.println("Username already exists. Please choose another.");
                return;
            }
        }
        
        //cipta akaun baru
        String customerId = "CUST" + String.format("%03d", customerCount + 1);
        customers[customerCount] = new Customer(name, phone, username, password, customerId, email);
        customerCount++;
        
        System.out.println("Account created successfully! Customer ID: " + customerId);
        System.out.println("You can now login with your username and password.");
    }
    
    private static void adminMenu() {
        Admin admin = (Admin) currentUser;
        
        while (true) {
            displayAdminMenu();
            int choice = getChoice();
            
            if (choice == 1) {
                viewAllBookings();
            } else if (choice == 2) {
                viewAllOrders();
            } else if (choice == 3) {
                viewAllAccounts();
            } else if (choice == 4) {
                System.out.println("Logging out...");
                return; //pergi ke main loop (back to login menu)
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void customerMenu() {
        Customer customer = (Customer) currentUser;
        
        while (true) {
            displayCustomerMenu();
            int choice = getChoice();
            
            if (choice == 1) {
                displayProducts();
                backToMenu();
            } else if (choice == 2) {
                displayServices();
                backToMenu();
            } else if (choice == 3) {
                createOrder(customer);
            } else if (choice == 4) {
                viewMyOrders(customer);
                backToMenu();
            } else if (choice == 5) {
                System.out.println("Logging out...");
                return; //balik ke main loop (back to login menu)
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void displayAdminMenu() {
        System.out.println("\n=== ADMIN MENU ===");
        System.out.println("1. View All Customer Bookings");
        System.out.println("2. View All Orders");
        System.out.println("3. View All Accounts");
        System.out.println("4. Logout");
        System.out.print("Choose (1-4): ");
    }
    
    private static void displayCustomerMenu() {
        System.out.println("\n=== CUSTOMER MENU ===");
        System.out.println("1. View Products");
        System.out.println("2. View Services");
        System.out.println("3. Make Order & Booking");
        System.out.println("4. View My Orders");
        System.out.println("5. Logout");
        System.out.print("Choose (1-5): ");
    }
    
    private static void displayProducts() {
        System.out.println("\n=== AVAILABLE PRODUCTS ===");
        for (int i = 0; i < productCount; i++) {
            System.out.print((i + 1) + ". ");
            products[i].displayProduct();
        }
    }
    
    private static void displayServices() {
        System.out.println("\n=== AVAILABLE SERVICES ===");
        for (int i = 0; i < serviceCount; i++) {
            System.out.print((i + 1) + ". ");
            services[i].displayService();
        }
    }
    
    private static void createOrder(Customer customer) {
        String orderId = "ORD" + String.format("%03d", queueCounter);
        String bookingId = "BOOK" + String.format("%03d", queueCounter);
        
        Order order = new Order(orderId, customer, queueCounter);
        Booking booking = null;
        
        //Select services
        System.out.println("\n=== SELECT SERVICES ===");
        displayServices();
        System.out.print("Enter service number (or 0 to finish): ");
        
        while (true) {
            int serviceChoice = getChoice();
            
            if (serviceChoice == 0) break;
            
            if (serviceChoice >= 1 && serviceChoice <= serviceCount) {
                Services selectedService = services[serviceChoice - 1];
                order.addService(selectedService);
                
                // Create booking for first service
                if (booking == null) {
                    booking = new Booking(bookingId, customer, selectedService, queueCounter);
                }
                
                System.out.println("Service added: " + selectedService.getServiceName());
            } else {
                System.out.println("Invalid service number!");
            }
            System.out.print("Enter another service number (or 0 to finish): ");
        }
        
        //select product
        System.out.println("\n=== SELECT PRODUCTS (OPTIONAL) ===");
        displayProducts();
        System.out.print("Enter product number (or 0 to finish): ");
        
        while (true) {
            int productChoice = getChoice();
            
            if (productChoice == 0) break;
            
            if (productChoice >= 1 && productChoice <= productCount) {
                Product selectedProduct = products[productChoice - 1];
                order.addProduct(selectedProduct);
                System.out.println("Product added: " + selectedProduct.getProductName());
            } else {
                System.out.println("Invalid product number!");
            }
            System.out.print("Enter another product number (or 0 to finish): ");
        }
        
        //payment method selection
        System.out.println("\n=== SELECT PAYMENT METHOD ===");
        System.out.println("1. Cash");
        System.out.println("2. Debit/Credit Card");
        System.out.print("Choose (1-2): ");
        
        int paymentChoice = getChoice();
        
        PaymentMethod paymentMethod;
        if (paymentChoice == 1) {
            paymentMethod = new PaymentMethod("Cash");
        } else {
            paymentMethod = new PaymentMethod("Card");
        }
        
        paymentMethod.processPayment(order.getTotalAmount());
        
        //generate receipt
        String receiptId = "REC" + String.format("%03d", queueCounter);
        Receipt receipt = new Receipt(receiptId, order, paymentMethod);
        
        //display order details
        order.displayOrder();
        
        //display booking details if service was selected
        if (booking != null) {
            booking.displayBooking();
            bookings[bookingCount] = booking;
            bookingCount++;
        }
        
        //print receipt
        receipt.printReceipt();
        
        //store order
        orders[orderCount] = order;
        orderCount++;
        queueCounter++;
        
        System.out.println("\nOrder created successfully! Your queue number: " + (queueCounter - 1));
        backToMenu();
    }
    
    private static void viewAllBookings() {
        System.out.println("\n=== ALL CUSTOMER BOOKINGS ===");
        if (bookingCount == 0) {
            System.out.println("No bookings found.");
        } else {
            for (int i = 0; i < bookingCount; i++) {
                bookings[i].displayBooking();
                System.out.println("-".repeat(50));
            }
        }
        backToMenu();
    }
    
    private static void viewAllOrders() {
        System.out.println("\n=== ALL ORDERS ===");
        if (orderCount == 0) {
            System.out.println("No orders found.");
        } else {
            for (int i = 0; i < orderCount; i++) {
                orders[i].displayOrder();
                System.out.println("-".repeat(50));
            }
        }
        backToMenu();
    }
    
    private static void viewAllAccounts() {
        System.out.println("\n=== ALL ACCOUNTS ===");
        System.out.println("ADMINS:");
        for (int i = 0; i < adminCount; i++) {
            admins[i].displayInfo();
        }
        System.out.println("\nCUSTOMERS:");
        for (int i = 0; i < customerCount; i++) {
            customers[i].displayInfo();
        }
        backToMenu();
    }
    
    private static void viewMyOrders(Customer customer) {
        System.out.println("\n=== MY ORDERS ===");
        boolean found = false;
        
        for (int i = 0; i < orderCount; i++) {
            if (orders[i].getCustomer().getCustomerId().equals(customer.getCustomerId())) {
                orders[i].displayOrder();
                System.out.println("-".repeat(50));
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No orders found for your account.");
        }
    }
    
    private static void backToMenu() {
        System.out.println("\nPress Enter to go back to menu...");
        scanner.nextLine();
    }
}