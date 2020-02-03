package Factory;

import ChargingEnviroment.*;
import ChargingEnviroment.Enums.EvSimChargingType;

import java.util.ArrayList;

public class HagenbergSimulationFactory {

    public static ArrayList<EvSimChargingStation> setupEnvironmentHagenberg(){
        ArrayList<EvSimChargingStation> stations = new ArrayList<>();
        int chargingPointCounter = 1;
        for(int i = 1; i <= 3; i++){
            EvSimChargingStation station = new EvSimChargingStation(i,"FH OOE Hagenberg");
            station.getEvSimChargingPoints().add(new EvSimChargingPoint(chargingPointCounter));
            station.getEvSimChargingPoints().add(new EvSimChargingPoint(chargingPointCounter + 1));
            chargingPointCounter += 2;
            stations.add(station);
        }
        return stations;
    }


    /**
     * Capacity = 40kWh
     * Top Charging Power = 40kW
     * DC = 3 Phase
     * @return The vehicle
     */
    public static EvSimVehicle setupNissanLeaf(){
        double capacity = 40000;
        double currentCapacity = (Math.random() * ((capacity - 3700) + 1)) + 3700;
        return new EvSimVehicle(new EvSimBattery(currentCapacity, capacity), "Nissan Leaf", EvSimChargingType.AC);
    }

    /**
     * Capacity = 22kWh
     * Top Charging Power = ?
     * DC = 3 Phase
     * @return The vehicle
     */
    public static EvSimVehicle setupRenaultZoe(){
        double capacity = 22000;
        double currentCapacity = (Math.random() * ((capacity - 3700) + 1)) + 3700;
        return new EvSimVehicle(new EvSimBattery(currentCapacity, capacity), "Renault Zoe", "GM-235FE", EvSimChargingType.AC);
    }

    /**
     * Capacity = 31410Wh
     * Top Charging Power = ?
     * DC = 1 Phase
     * @return The vehicle
     */
    public static EvSimVehicle setupBMWi3(){
        double capacity = 31410;
        double currentCapacity = (Math.random() * ((capacity - 3700) + 1)) + 3700;
        return new EvSimVehicle(new EvSimBattery(currentCapacity, capacity), "BMW i3", EvSimChargingType.AC);
    }

    /**
     * Capacity = 155748Wh
     * Top Charging Power = 80kW
     * AC
     * DC = 3 Phase
     * @return The vehicle
     */
    public static EvSimVehicle setupTeslaModel3(){
        double capacity = 155748;
        double currentCapacity = (Math.random() * ((capacity - 3700) + 1)) + 3700;
        return new EvSimVehicle(new EvSimBattery(currentCapacity, capacity), "TeslaModel 3", EvSimChargingType.AC);
    }

    /**
     * Capacity = 14kWh
     * Top Charging Power = ?
     * DC = 3 Phase
     * @return The vehicle
     */
    public static EvSimVehicle setupVwEGolf(){
        double capacity = 14000;
        double currentCapacity = (Math.random() * ((capacity - 3700) + 1)) + 3700;
        return new EvSimVehicle(new EvSimBattery(currentCapacity, capacity), "VW E Golf", EvSimChargingType.AC);
    }

    /**
     * Capacity = 95kWh
     * Top Charging Power = 150kW
     * AC
     * DC = 3 Phase
     * @return The vehicle
     */
    public static EvSimVehicle setupAudiETron(){
        double capacity = 95000;
        double currentCapacity = (Math.random() * ((capacity - 3700) + 1)) + 3700;
        return new EvSimVehicle(new EvSimBattery(currentCapacity, capacity), "Audi E Tron", EvSimChargingType.AC);
    }
}
