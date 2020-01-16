package Components;

public class ChargingPoint {
    private int id;
    private Vehicle vehicle;
    private boolean inUse = false;
    private double defaultChargingSpeed; //Value in W
    private ChargingProcess chargingProcess;

    public ChargingPoint(int id) {
        this.defaultChargingSpeed = 22000;
        this.id = id;
    }

    public ChargingPoint(int id, Vehicle vehicle) {
        this.defaultChargingSpeed = 22000;
        this.id = id;
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
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

    public double getDefaultChargingSpeed() {
        return defaultChargingSpeed;
    }

    public void setDefaultChargingSpeed(double defaultChargingSpeed) {
        this.defaultChargingSpeed = defaultChargingSpeed;
    }

    public boolean addVehicleToPoint(Vehicle vehicle){
        if(isInUse()){
            return false;
        }

        this.vehicle = vehicle;
        setInUse(true);
        return true;
    }

    public boolean removeVehicleFromPoint(){
        if(!isInUse() && this.chargingProcess != null){
            return false;
        }

        this.vehicle = null;
        setInUse(false);
        return true;
    }

    public boolean startCharging(){
        if(!isInUse() && this.vehicle == null){
            return false;
        }

        setChargingProcess(new ChargingProcess(defaultChargingSpeed, this.vehicle));
        getChargingProcess().startChargingProcess();

        return true;
    }

    public boolean pauseCharging(){
        if(!isInUse() && this.vehicle == null && this.chargingProcess == null){
            return false;
        }

        getChargingProcess().stopChargingProcess();

        return true;
    }

    public boolean continueCharging(){
        if(!isInUse() && this.vehicle == null && this.chargingProcess == null){
            return false;
        }

        getChargingProcess().startChargingProcess();

        return true;
    }

    public boolean stopCharging(){
        if(!isInUse() && this.vehicle == null && this.chargingProcess == null){
            return false;
        }

        getChargingProcess().stopChargingProcess();
        setChargingProcess(null);
        return true;
    }

    public boolean changeChargingSpeedOnPoint(double chargingSpeed){
        if(!isInUse() && this.vehicle == null && this.chargingProcess == null){
            return false;
        }

        getChargingProcess().changeChargingSpeed(chargingSpeed);

        return true;
    }
}
