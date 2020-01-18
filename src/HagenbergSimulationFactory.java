import Components.*;
import Components.Enums.ChargingType;

import java.util.ArrayList;

public class HagenbergSimulationFactory {

    public static ArrayList<ChargingStation> setupEnvironmentHagenberg(){
        ArrayList<ChargingStation> stations = new ArrayList<>();
        int chargingPointCounter = 1;
        for(int i = 1; i <= 3; i++){
            ChargingStation station = new ChargingStation(i,"FH OOE Hagenberg");
            station.getChargingPoints().add(new ChargingPoint(chargingPointCounter));
            station.getChargingPoints().add(new ChargingPoint(chargingPointCounter + 1));
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
    public static Vehicle setupNissanLeaf(){
        double capacity = 40000;
        double currentCapacity = (Math.random() * ((capacity - 3700) + 1)) + 3700;
        return new Vehicle(new Battery(currentCapacity, capacity), "Nissan Leaf", ChargingType.AC);
    }

    /**
     * Capacity = 22kWh
     * Top Charging Power = ?
     * DC = 3 Phase
     * @return The vehicle
     */
    public static Vehicle setupRenaultZoe(){
        double capacity = 22000;
        double currentCapacity = (Math.random() * ((capacity - 3700) + 1)) + 3700;
        return new Vehicle(new Battery(currentCapacity, capacity), "Renault Zoe", "GM-235FE", ChargingType.AC);
    }

    /**
     * Capacity = 31410Wh
     * Top Charging Power = ?
     * DC = 1 Phase
     * @return The vehicle
     */
    public static Vehicle setupBMWi3(){
        double capacity = 31410;
        double currentCapacity = (Math.random() * ((capacity - 3700) + 1)) + 3700;
        return new Vehicle(new Battery(currentCapacity, capacity), "BMW i3", ChargingType.AC);
    }

    /**
     * Capacity = 155748Wh
     * Top Charging Power = 80kW
     * AC
     * DC = 3 Phase
     * @return The vehicle
     */
    public static Vehicle setupTeslaModel3(){
        double capacity = 155748;
        double currentCapacity = (Math.random() * ((capacity - 3700) + 1)) + 3700;
        return new Vehicle(new Battery(currentCapacity, capacity), "BMW i3", ChargingType.AC);
    }

    /**
     * Capacity = 14kWh
     * Top Charging Power = ?
     * DC = 3 Phase
     * @return The vehicle
     */
    public static Vehicle setupVwEGolf(){
        double capacity = 14000;
        double currentCapacity = (Math.random() * ((capacity - 3700) + 1)) + 3700;
        return new Vehicle(new Battery(currentCapacity, capacity), "VW E Golf", ChargingType.AC);
    }

    /**
     * Capacity = 95kWh
     * Top Charging Power = 150kW
     * AC
     * DC = 3 Phase
     * @return The vehicle
     */
    public static Vehicle setupAudiETron(){
        double capacity = 95000;
        double currentCapacity = (Math.random() * ((capacity - 3700) + 1)) + 3700;
        return new Vehicle(new Battery(currentCapacity, capacity), "Audi E Tron", ChargingType.AC);
    }
}
