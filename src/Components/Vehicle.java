package Components;

import Components.Enums.ChargingType;
import Components.Enums.Phases;

public class Vehicle {
    private int id;
    private Battery battery;
    private String vehicleType;
    private ChargingType chargingType;
    private Phases phases ;
    private String numberPlate;

    public Vehicle(Battery battery, String vehicleType, ChargingType chargingType) {
        this.battery = battery;
        this.vehicleType = vehicleType;
        this.chargingType = chargingType;
        if(chargingType == ChargingType.AC){
            phases = Phases.three;
        } else {
            phases = null;
        }
    }

    public Vehicle(Battery battery, String vehicleType, String numberPlate, ChargingType chargingType) {
        this.battery = battery;
        this.vehicleType = vehicleType;
        this.chargingType = chargingType;
        this.numberPlate = numberPlate;
        if(chargingType == ChargingType.AC){
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

    public ChargingType getChargingType() {
        return chargingType;
    }

    public void setChargingType(ChargingType chargingType) {
        this.chargingType = chargingType;
    }

    public Phases getPhases() {
        if(chargingType == ChargingType.AC){
            return phases;
        } else {
            System.out.println("Not able to get phases on a AC vehicle.");
            return null;
        }
    }

    public void setPhases(Phases phases) {
        if(chargingType == ChargingType.AC){
            this.phases = phases;
        } else {
            System.out.println("Not able to set phases on a AC vehicle.");
            return;
        }
    }
}
