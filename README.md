# EvChargingEnv
The EvChargingEnv library is a real time simulation environment for electrical evSimVehicle charging 
written in Java. Every value is defined in Wh or W not in kWh Through the rising number of EVs and growing number of optimizing charging systems it is use full to have a simulation environment which can work in realtime. This lib was developed during my bachelor thesis with the title "Energy-optimized charging mechanisms fore-mobility" on the University of Applied Since Upper Austria Hagenberg to test the algorithm. The link to my optimized charging algorithm will be published here.

## Documentation
The documentation of the lib is done in JavaDoc and can be exported. I will add a doxygen file to the source.

## Function
Mainly a running charging process frequently update the attached evSimVehicle and evSimBattery with a new capacity until it is full charged.
To enable the possibility of optimizing it is possible to change the charging power during a charging process. 

### Charging Station
A charging station contains some charging points where the evSimVehicle is attached. Most organisations have a couple stations where every station has around 2 charging points.
To create a station a id and the organisation name is needed. 
```
EvSimChargingStation station = new EvSimChargingStation(1,"FH OOE Hagenberg");
```

### Charging Point
The charging point is attached on a charging station and contains a evSimVehicle and a charging process. It is responsible to start, pause and stop a charging process. Also it managed the connected car. The constructor takes just a id or the id and a evSimVehicle. 

```
EvSimChargingPoint point = new EvSimChargingPoint(1);
//or
EvSimChargingPoint point = new EvSimChargingPoint(1, new EvSimVehicle(new EvSimBattery(currentCapacity, capacity), "Renault Zoe", ChargingType.AC));
//Manage the evSimVehicle
point.addVehicleToPoint(Factory.HagenbergSimulationFactory.setupRenaultZoe());
point.setDefaultChargingSpeed(12000);
point.startCharging();
```

### Vehicle and Battery
A evSimVehicle stores a evSimBattery and it is possible to set the charging technology of the evSimVehicle. To identify the evSimVehicle it is possible to set a number plate. The evSimBattery stores the current capacity, the left overt to charge and the overall capacity.
It is possible to calculate the current evSimBattery capacity in percent.
```
      double capacity = 22000;
      double currentCapacity = 8000;
      EvSimVehicle evSimVehicle = new EvSimVehicle(new EvSimBattery(currentCapacity, capacity), "Renault Zoe", "GM-235FE", ChargingType.AC);
```

### Charging Process
The charging process is running in an own thread and will update ever second the current capacity of the attached evSimBattery.
It will also calculate the estimated end time and check frequently if the charging goal is reached.
```
setChargingProcess(new EvSimChargingProcess(defaultChargingSpeed, this.evSimVehicle));
getChargingProcess().startChargingProcess();
```
A charging process will be created atomically when the charging point start charging function is called. With the the given default speed of the the charging point. It is possible to change the default speed before the running one. When a process is running it is possible to change the current charging speed.
```
point.setDefaultChargingSpeed(22000);
point.startCharging();
point.changeChargingSpeedOnPoint(12000);
```

## Usage
A quick example how to use the library, i created a factory for my usage where i create some vehicles with the real specifications to test.
```
public static void main(String[] args){
        ArrayList<EvSimChargingStation> stations = Factory.HagenbergSimulationFactory.setupEnvironmentHagenberg();

        stations.get(0).getChargingPoints().get(0).addVehicleToPoint(Factory.HagenbergSimulationFactory.setupTeslaModel3());
        stations.get(0).getChargingPoints().get(0).startCharging();
        
        EvSimChargingPoint point = stations.get(1).getNextFreeChargingPoint();
        point.addVehicleToPoint(Factory.HagenbergSimulationFactory.setupRenaultZoe());
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
```

### Creation of a ChargingStation 
```
 ArrayList<EvSimChargingStation> stations = new ArrayList<>();
         int chargingPointCounter = 1;
         for(int i = 1; i <= 3; i++){
             EvSimChargingStation station = new EvSimChargingStation(i,"FH OOE Hagenberg");
             station.getChargingPoints().add(new EvSimChargingPoint(chargingPointCounter));
             station.getChargingPoints().add(new EvSimChargingPoint(chargingPointCounter + 1));
             chargingPointCounter += 2;
             stations.add(station);
         }
```

## Update
I will add some features soon.
### BMS
The BMS can influence a charging process and will be added to a evSimVehicle. It will be possible to set a charging maximum and a time cap. Also it will regulate the charging speed over the BMS. 
### Charging loss
Currently the charging process is a linear way and will only occur in ideal situation. To encounter this it will be possible to set a charging loss. 
