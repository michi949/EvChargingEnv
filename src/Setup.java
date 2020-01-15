import Components.ChargingPoint;
import Components.ChargingStation;

import java.util.ArrayList;

public class Setup {
    public static void main(String[] args){
        ArrayList<ChargingStation> stations = HagenbergSimulationFactory.setupEnvironmentHagenberg();

        stations.get(0).getChargingPoints().get(0).addVehicleToPoint(HagenbergSimulationFactory.setupTeslaModel3());
        stations.get(0).getChargingPoints().get(0).startCharging();

        ChargingPoint point = stations.get(1).getNextFreeChargingPoint();
        point.addVehicleToPoint(HagenbergSimulationFactory.setupRenaultZoe());
        point.setDefaultChargingSpeed(12000);
        point.startCharging();


        while (true);
    }
}
