package ChargingEnviroment;

import ChargingEnviroment.Enums.EvSimChargingType;
import ChargingEnviroment.Enums.EvSimPhases;

public class EvSimVehicle {
    private int id;
    private EvSimBattery evSimBattery;
    private String vehicleType;
    private EvSimChargingType evSimChargingType;
    private EvSimPhases evSimPhases;
    private String numberPlate;

    public EvSimVehicle(EvSimBattery evSimBattery, String vehicleType, EvSimChargingType evSimChargingType) {
        this.evSimBattery = evSimBattery;
        this.vehicleType = vehicleType;
        this.evSimChargingType = evSimChargingType;
        if(evSimChargingType == EvSimChargingType.AC){
            evSimPhases = EvSimPhases.three;
        } else {
            evSimPhases = null;
        }
    }

    public EvSimVehicle(EvSimBattery evSimBattery, String vehicleType, String numberPlate, EvSimChargingType evSimChargingType) {
        this.evSimBattery = evSimBattery;
        this.vehicleType = vehicleType;
        this.evSimChargingType = evSimChargingType;
        this.numberPlate = numberPlate;
        if(evSimChargingType == EvSimChargingType.AC){
            evSimPhases = EvSimPhases.three;
        } else {
            evSimPhases = null;
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

    public EvSimBattery getEvSimBattery() {
        return evSimBattery;
    }

    public void setEvSimBattery(EvSimBattery evSimBattery) {
        this.evSimBattery = evSimBattery;
    }

    public EvSimChargingType getEvSimChargingType() {
        return evSimChargingType;
    }

    public void setEvSimChargingType(EvSimChargingType evSimChargingType) {
        this.evSimChargingType = evSimChargingType;
    }

    public EvSimPhases getEvSimPhases() {
        if(evSimChargingType == EvSimChargingType.AC){
            return evSimPhases;
        } else {
            System.out.println("Not able to get phases on a AC vehicle.");
            return null;
        }
    }

    public void setEvSimPhases(EvSimPhases evSimPhases) {
        if(evSimChargingType == EvSimChargingType.AC){
            this.evSimPhases = evSimPhases;
        } else {
            System.out.println("Not able to set phases on a AC vehicle.");
            return;
        }
    }
}
