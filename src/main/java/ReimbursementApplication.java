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

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getUsername() {
        return username;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
