// Admin.java
public class Admin extends User {
    private String adminId;
    
    public Admin(String name, String phone, String username, String password, String adminId) {
        super(name, phone, username, password);
        this.adminId = adminId;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("Admin: " + name + " | ID: " + adminId);
    }
    
    public String getAdminId() { return adminId; }
}