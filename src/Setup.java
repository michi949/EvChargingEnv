import Components.ChargingPoint;
import Components.ChargingStation;
import EnergySources.Solar;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Setup {
    public static void main(String[] args){
        ArrayList<ChargingStation> stations = HagenbergSimulationFactory.setupEnvironmentHagenberg();

        stations.get(0).getChargingPoints().get(0).addVehicleToPoint(HagenbergSimulationFactory.setupTeslaModel3());
        stations.get(0).getChargingPoints().get(0).startCharging();

        ChargingPoint point = stations.get(1).getNextFreeChargingPoint();
        point.addVehicleToPoint(HagenbergSimulationFactory.setupRenaultZoe());
        point.setDefaultChargingSpeed(22000);
        point.startCharging();

        double test = (double) 1080000 / 3600000;

        Solar solar = new Solar(560000, 0.45, 26.0);
        System.out.println(solar.dailyOutput());
        System.out.println(solar.hourOutput());

        try {
            Thread.sleep(180000);
            point.changeChargingSpeedOnPoint(12000);
            stations.get(0).getChargingPoints().get(0).pauseCharging();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
