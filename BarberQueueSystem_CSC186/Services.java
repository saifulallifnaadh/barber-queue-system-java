// Services.java
public class Services {
    private String serviceId;
    private String serviceName;
    private double servicePrice;
    private int duration;
    
    public Services(String serviceId, String serviceName, double servicePrice, int duration) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.duration = duration;
    }
    
    public void displayService() {
        System.out.println("Service: " + serviceName + " | Price: RM" + servicePrice + " | Duration: " + duration + " minutes");
    }
    
    public String getServiceId() { return serviceId; }
    public String getServiceName() { return serviceName; }
    public double getServicePrice() { return servicePrice; }
}