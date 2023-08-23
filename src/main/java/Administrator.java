import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Administrator {
    private DoubleProperty dietCostPerDay;
    private DoubleProperty distanceCost;

    public Administrator(String username, double dietCostPerDay, double distanceCost) {
        this.dietCostPerDay = new SimpleDoubleProperty(dietCostPerDay);
        this.distanceCost = new SimpleDoubleProperty(distanceCost);
    }

    public DoubleProperty dietCostPerDayProperty() {
        return dietCostPerDay;
    }

    public double getDietCostPerDay() {
        return dietCostPerDay.get();
    }

    public void setDietCostPerDay(double dietCostPerDay) {
        this.dietCostPerDay.set(dietCostPerDay);
    }

    public DoubleProperty distanceCostProperty() {
        return distanceCost;
    }

    public double getDistanceCost() {
        return distanceCost.get();
    }

    public void setDistanceCost(double distanceCost) {
        this.distanceCost.set(distanceCost);
    }
}
