package Components;

public class Vehicle {
    int id;
    Battery battery;
    String vehicleType;
    ChargingType chargingType;
    Phases phases ;

    public Vehicle(Battery battery, String vehicleType, ChargingType chargingType) {
        this.battery = battery;
        this.vehicleType = vehicleType;
        this.chargingType = chargingType;
        if(chargingType == ChargingType.DC){
            phases = Phases.three;
        } else {
            phases = null;
        }
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }


}
