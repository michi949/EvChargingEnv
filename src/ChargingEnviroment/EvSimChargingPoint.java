package ChargingEnviroment;

public class EvSimChargingPoint {
    private int id;
    private EvSimVehicle evSimVehicle;
    private boolean inUse = false;
    private double defaultChargingSpeed; //Value in W
    private EvSimChargingProcess evSimChargingProcess;

    public EvSimChargingPoint(int id) {
        this.defaultChargingSpeed = 22000;
        this.id = id;
    }

    public EvSimChargingPoint(int id, EvSimVehicle evSimVehicle) {
        this.defaultChargingSpeed = 22000;
        this.id = id;
        this.evSimVehicle = evSimVehicle;
    }

    public EvSimVehicle getEvSimVehicle() {
        return evSimVehicle;
    }

    public EvSimChargingProcess getEvSimChargingProcess() {
        return evSimChargingProcess;
    }

    public void setEvSimChargingProcess(EvSimChargingProcess evSimChargingProcess) {
        this.evSimChargingProcess = evSimChargingProcess;
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

    public boolean addVehicleToPoint(EvSimVehicle evSimVehicle){
        if(isInUse()){
            return false;
        }

        this.evSimVehicle = evSimVehicle;
        setInUse(true);
        return true;
    }

    public boolean removeVehicleFromPoint(){
        if(!isInUse() && this.evSimChargingProcess != null){
            return false;
        }

        this.evSimVehicle = null;
        setInUse(false);
        return true;
    }

    public boolean startCharging(){
        if(!isInUse() && this.evSimVehicle == null){
            return false;
        }

        setEvSimChargingProcess(new EvSimChargingProcess(defaultChargingSpeed, this.evSimVehicle));
        getEvSimChargingProcess().startChargingProcess();

        return true;
    }

    public boolean pauseCharging(){
        if(!isInUse() && this.evSimVehicle == null && this.evSimChargingProcess == null){
            return false;
        }

        getEvSimChargingProcess().stopChargingProcess();

        return true;
    }

    public boolean continueCharging(){
        if(!isInUse() && this.evSimVehicle == null && this.evSimChargingProcess == null){
            return false;
        }

        getEvSimChargingProcess().startChargingProcess();

        return true;
    }

    public boolean stopCharging(){
        if(!isInUse() && this.evSimVehicle == null && this.evSimChargingProcess == null){
            return false;
        }

        getEvSimChargingProcess().stopChargingProcess();
        setEvSimChargingProcess(null);
        return true;
    }

    public boolean changeChargingSpeedOnPoint(double chargingSpeed){
        if(!isInUse() && this.evSimVehicle == null && this.evSimChargingProcess == null){
            return false;
        }

        getEvSimChargingProcess().changeChargingSpeed(chargingSpeed);

        return true;
    }
}
