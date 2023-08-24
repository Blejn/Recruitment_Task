import java.util.List;

public class ReimbursementApplication {
    private String username;
    private List<Receipt> receipts;
    private double totalAmount;

    public ReimbursementApplication(String username, List<Receipt> receipts, double totalAmount) {
        this.username = username;
        this.receipts = receipts;
        this.totalAmount = totalAmount;
    }

    // Gettery i settery...
}
