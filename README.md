# EvChargingEnv
The EvChargingEnv library is a real time simulation environment for electrical vehicle charging 
written in Java. Every value is defined in Wh or W not in kWh Through the rising number of EVs and growing number of optimizing charging systems it is use full to have a simulation environment which can work in realtime. This lib was developed during my bachelor thesis with the title "Energy-optimized charging mechanisms fore-mobility" on the University of Applied Since Upper Austria Hagenberg to test the algorithm. The link to my optimized charging algorithm will be published here.

## Documentation
The documentation of the lib is done in JavaDoc and can be exported. I will add a doxygen file to the source.

## Function
Mainly a running charging process frequently update the attached vehicle and battery with a new capacity until it is full charged.
To enable the possibility of optimizing it is possible to change the charging power during a charging process. 

### Charging Station
A charging station contains some charging points where the vehicle is attached. Most organisations have a couple stations where every station has around 2 charging points.
To create a station a id and the organisation name is needed. 
```
ChargingStation station = new ChargingStation(1,"FH OOE Hagenberg");
```

### Charging Point
The charging point is attached on a charging station and contains a vehicle and a charging process. It is responsible to start, pause and stop a charging process. Also it managed the connected car. The constructor takes just a id or the id and a vehicle. 

```
ChargingPoint point = new ChargingPoint(1);
//or
ChargingPoint point = new ChargingPoint(1, new Vehicle(new Battery(currentCapacity, capacity), "Renault Zoe", ChargingType.DC));
//Manage the vehicle
point.addVehicleToPoint(HagenbergSimulationFactory.setupRenaultZoe());
point.setDefaultChargingSpeed(12000);
point.startCharging();
```

### Vehicle

### Battery

### Charging Process

## Usage


### Creation of a ChargingStation 
```
 ArrayList<ChargingStation> stations = new ArrayList<>();
         int chargingPointCounter = 1;
         for(int i = 1; i <= 3; i++){
             ChargingStation station = new ChargingStation(i,"FH OOE Hagenberg");
             station.getChargingPoints().add(new ChargingPoint(chargingPointCounter));
             station.getChargingPoints().add(new ChargingPoint(chargingPointCounter + 1));
             chargingPointCounter += 2;
             stations.add(station);
         }
```

## Update