//user.java
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public abstract class User {
    protected String name;
    protected String phone;
    protected String username;
    protected String password;
    
    public User(String name, String phone, String username, String password) {
        this.name = name;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }
    
    public abstract void displayInfo();
    
    // Getters
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}