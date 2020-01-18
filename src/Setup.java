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
        point.setDefaultChargingSpeed(22000);
        point.startCharging();

        try {
            Thread.sleep(180000);
            point.changeChargingSpeedOnPoint(12000);
            stations.get(0).getChargingPoints().get(0).pauseCharging();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
