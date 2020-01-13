package Components;

public class ChargingProcess extends Thread {
    int id;
    double chargingSpeed;
    Vehicle vehicle;
    boolean isCharging;

    public ChargingProcess(double chargingSpeed, Vehicle vehicle) {
        this.chargingSpeed = chargingSpeed;
        this.vehicle = vehicle;
    }

    public double getChargingSpeed() {
        return chargingSpeed;
    }

    public void setChargingSpeed(double chargingSpeed) {
        this.chargingSpeed = chargingSpeed;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void run() {
        if(chargingSpeed == 0.0 && vehicle == null){
            return;
        }
        
        System.out.println("Starting charging process with speed " + chargingSpeed);
    }

    public void startChargingProcess(){

    }

    public void stopChargingProcess(){

    }

    public void pauseChargingProcess(){

    }
}
