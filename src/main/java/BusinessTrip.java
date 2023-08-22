import java.time.LocalDate;
import java.util.List;

public class BusinessTrip {
    private LocalDate tripDate;
    private List<Receipt>receipts;
    private int numberOfDays;
    private boolean[] disabledDays;
    private double carDistance;
    public BusinessTrip(LocalDate tripDate,List<Receipt> receipts, int numberOfDays, boolean[] disabledDays, double carDistance){
        this.tripDate = tripDate;
        this.receipts = receipts;
        this.numberOfDays = numberOfDays;
        this.disabledDays = disabledDays;
        this.carDistance = carDistance;
    }

    public LocalDate getTripDate() {
        return tripDate;
    }

    public void setTripDate(LocalDate tripDate) {
        this.tripDate = tripDate;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public boolean[] getDisabledDays() {
        return disabledDays;
    }

    public void setDisabledDays(boolean[] disabledDays) {
        this.disabledDays = disabledDays;
    }

    public double getCarMileage() {
        return carDistance;
    }

    public void setCarMileage(double carMileage) {
        this.carDistance = carMileage;
    }
}
