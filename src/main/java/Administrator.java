public class Administrator extends  User{
    public double dietCostPerDay;
    public double distanceCost;
    public Administrator(String username,double dietCostPerDay, double distanceCost){
        super(username,"admin");
        this.dietCostPerDay = dietCostPerDay;
        this.distanceCost = distanceCost;

        setDietCostPerDay(15);
        setDistanceCost(0.3);
    }
    public double getDietCostPerDay(){
        return dietCostPerDay;
    }
    public double getDistanceCost(){
        return distanceCost;
    }
    public void setDietCostPerDay(double dietCostPerDay){
        this.distanceCost = dietCostPerDay;
    }
    public void setDistanceCost(double distanceCost){
        this.distanceCost = distanceCost;
    }
}
