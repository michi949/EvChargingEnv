package Components;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChargingProcess extends Thread {
    int id;
    Date startDate;
    Date updateDate;
    Date estimatedEndDate;
    double chargingSpeed;
    Vehicle vehicle;
    boolean isCharging;

    public ChargingProcess(double chargingSpeed, Vehicle vehicle) {
        this.chargingSpeed = chargingSpeed;
        this.vehicle = vehicle;
        this.start();
    }

    public double getChargingSpeed() {
        return chargingSpeed;
    }

    public void setChargingSpeed(double chargingSpeed) {
        this.chargingSpeed = chargingSpeed;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getEstimatedEndDate() {
        return estimatedEndDate;
    }

    public void setEstimatedEndDate(Date estimatedEndDate) {
        this.estimatedEndDate = estimatedEndDate;
    }

    public boolean isCharging() {
        return isCharging;
    }

    public void setCharging(boolean charging) {
        isCharging = charging;
    }

    /**
     * Starting a thread for the charging process.
     */
    public void run() {
        if(chargingSpeed == 0.0 && vehicle == null){
            return;
        }

        System.out.println("Starting charging process with speed " + chargingSpeed);
    }

    public void startChargingProcess(){
        startDate = new Date();
    }

    public void stopChargingProcess(){

    }

    public void pauseChargingProcess(){

    }

    public void updateCurrentBatteryCapacity(){

    }

    /**
     * Calculates the end date of the charging process with the given capacity of the battery and the charging power.
     * ChargingTime = BatteryCapacity / ChargingPower
     */
    public void estimatePossibleEndDate(){
        double intervalToChargingEnd = vehicle.getBattery().getCapacity() / this.getChargingSpeed();
        Date intervalDate = convertDoubleToDate(intervalToChargingEnd);
        if(intervalDate == null){
            System.out.println("Estimating the possible end Date is not possible.");
        } else {
            long sum = startDate.getTime() + intervalDate.getTime();
            estimatedEndDate = new Date(sum);
        }
    }

    /**
     * Converting the double time in a date format to add it to the start date.
     * @param intervalToChargingEnd Given interval to the end in double.
     * @return The interval in a Date format. 
     */
    private Date convertDoubleToDate(double intervalToChargingEnd){
        String s = String.format("%06d", (int)intervalToChargingEnd);
        DateFormat format = new SimpleDateFormat("HHmmss");
        try {
            return format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
