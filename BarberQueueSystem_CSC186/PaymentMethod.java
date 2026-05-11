// PaymentMethod.java
public class PaymentMethod {
    private String paymentType;
    private String instructions;
    
    public PaymentMethod(String paymentType) {
        this.paymentType = paymentType;
        if (paymentType.equals("Cash")) {
            this.instructions = "Please pay cash at counter";
        } else {
            this.instructions = "Please pay with debit/credit card at counter";
        }
    }
    
    public void processPayment(double amount) {
        System.out.println("Payment: RM" + amount + " via " + paymentType);
        System.out.println("Instructions: " + instructions);
    }
    
    public String getPaymentType() { return paymentType; }
    public String getInstructions() { return instructions; }
}