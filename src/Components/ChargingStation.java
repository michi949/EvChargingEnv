package Components;

import java.util.ArrayList;

public class ChargingStation {
    int id;
    String owner;
    ArrayList<ChargingPoint> chargingPoints;

    /**
     * Charging station can only be created with those informations.
     * @param id The identifyer of the station and get be used for multiple stations.
     * @param owner The owner of the station.
     * @param chargingPoints Connected charging points to the station, could be multiple.
     */
    public ChargingStation(int id, String owner, ArrayList<ChargingPoint> chargingPoints) {
        this.id = id;
        this.owner = owner;
        this.chargingPoints = chargingPoints;
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

    public ArrayList<ChargingPoint> getChargingPoints() {
        return chargingPoints;
    }

    public void setChargingPoints(ArrayList<ChargingPoint> chargingPoints) {
        this.chargingPoints = chargingPoints;
    }
}
