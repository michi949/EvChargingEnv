package Components;

public class Battery {
    int id;
    double currentCapacity;
    double capacity;
    double leftOverCapacity;

    public Battery(double currentCapacity, double capacity) {
        this.currentCapacity = currentCapacity;
        this.capacity = capacity;
        this.setLeftOverCapacity();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(double currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getLeftOverCapacity() {
        return leftOverCapacity;
    }

    public void setLeftOverCapacity() {
        this.leftOverCapacity = capacity - currentCapacity;
    }
}
