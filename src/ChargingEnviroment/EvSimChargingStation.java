package ChargingEnviroment;

import java.util.ArrayList;

public class EvSimChargingStation {
    private int id;
    private String owner;
    private ArrayList<EvSimChargingPoint> evSimChargingPoints;


    public EvSimChargingStation(int id, String owner) {
        this.id = id;
        this.owner = owner;
        evSimChargingPoints = new ArrayList<>();
    }

    /**
     * Charging station can only be created with those informations.
     * @param owner The owner of the station.
     * @param evSimChargingPoints Connected charging points to the station, could be multiple.
     */
    public EvSimChargingStation(String owner, ArrayList<EvSimChargingPoint> evSimChargingPoints) {
        this.owner = owner;
        this.evSimChargingPoints = evSimChargingPoints;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<EvSimChargingPoint> getEvSimChargingPoints() {
        return evSimChargingPoints;
    }

    public void setEvSimChargingPoints(ArrayList<EvSimChargingPoint> evSimChargingPoints) {
        this.evSimChargingPoints = evSimChargingPoints;
    }

    /**
     * Loops over all charging points of the station and returns the first free point.
     * @return A free ChargingPoint.
     */
    public EvSimChargingPoint getNextFreeChargingPoint(){
        for(EvSimChargingPoint point : evSimChargingPoints){
            if(!point.isInUse()){
                return point;
            }
        }
        return null;
    }
}
