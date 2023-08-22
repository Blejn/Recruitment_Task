import java.time.LocalDate;

public class Receipt {
    private String name;
    private double amount;
    private LocalDate date;
    private String expenseType;

    public Receipt(String name, double amount, LocalDate date, String expenseType) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.expenseType = expenseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", expenseType='" + expenseType + '\'' +
                '}';
    }
}
