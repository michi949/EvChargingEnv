import ChargingEnviroment.EvSimChargingPoint;
import ChargingEnviroment.EvSimChargingStation;
import EnergySources.EvSimSolar;
import Factory.HagenbergSimulationFactory;

import java.util.ArrayList;

public class Setup {
    public static void main(String[] args){
        ArrayList<EvSimChargingStation> stations = HagenbergSimulationFactory.setupEnvironmentHagenberg();

        stations.get(0).getEvSimChargingPoints().get(0).addVehicleToPoint(HagenbergSimulationFactory.setupTeslaModel3());
        stations.get(0).getEvSimChargingPoints().get(0).startCharging();

        EvSimChargingPoint point = stations.get(1).getNextFreeChargingPoint();
        point.addVehicleToPoint(HagenbergSimulationFactory.setupRenaultZoe());
        point.setDefaultChargingSpeed(22000);
        point.startCharging();

        double test = (double) 1080000 / 3600000;

        EvSimSolar evSimSolar = new EvSimSolar(53000, 0.45, 26.0, true);
        System.out.println(evSimSolar.dailyOutput());
        System.out.println(evSimSolar.hourOutput());

        try {
            Thread.sleep(180000);
            point.changeChargingSpeedOnPoint(12000);
            stations.get(0).getEvSimChargingPoints().get(0).pauseCharging();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
