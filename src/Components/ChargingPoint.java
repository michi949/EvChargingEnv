package Components;

public class ChargingPoint {
    int id;
    Vehicle vehicle;
    ChargingProcess chargingProcess;
    boolean inUse = false;

    public ChargingPoint() {}

    public ChargingPoint(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ChargingProcess getChargingProcess() {
        return chargingProcess;
    }

    public void setChargingProcess(ChargingProcess chargingProcess) {
        this.chargingProcess = chargingProcess;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
