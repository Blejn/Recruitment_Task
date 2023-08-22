import java.util.List;

public class ReimbursementCalculator {
    private double dailyAllowanceRate;
    private double carMileageRate;

    public ReimbursementCalculator(double dailyAllowanceRate, double carMileageRate) {
        this.dailyAllowanceRate = dailyAllowanceRate;
        this.carMileageRate = carMileageRate;
    }

    public double calculateReimbursement(BusinessTrip trip) {
        double totalReimbursement = 0.0;

        for (Receipt receipt : trip.getReceipts()) {
            totalReimbursement += receipt.getAmount();
        }

        for (int day = 1; day <= trip.getNumberOfDays(); day++) {
            if (!trip.getDisabledDays()[day - 1]) {
                totalReimbursement += dailyAllowanceRate;
            }
        }

        totalReimbursement += trip.getCarMileage() * carMileageRate;

        return totalReimbursement;
    }
}
