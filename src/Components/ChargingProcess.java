package Components;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChargingProcess extends Thread {
    private Date startDate;
    private Date updateDate;
    private Date estimatedEndDate;
    private double chargingSpeed;
    private Vehicle vehicle;
    private boolean isCharging;
    private boolean isActive;
    private Timer timer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if(isCharging()){
                System.out.println(new Date());
            }
        }
    };

    public ChargingProcess(double chargingSpeed, Vehicle vehicle) {
        this.chargingSpeed = chargingSpeed;
        this.vehicle = vehicle;
    }

    public double getChargingSpeed() {
        return chargingSpeed;
    }

    public void setChargingSpeed(double chargingSpeed) {
        this.chargingSpeed = chargingSpeed;
        this.estimatePossibleEndDate();
        if(this.isCharging()){
            //todo change calculation
        }
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


    public void startChargingProcess(){
        if(chargingSpeed == 0 || vehicle == null){
            return;
        }

        setCharging(true);
        startDate = new Date();
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 1000 , 1000);
        this.estimatePossibleEndDate();
        this.start();
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
        System.out.println(vehicle.getBattery().getLeftOverCapacity());
        System.out.println(this.getChargingSpeed());
        double intervalToChargingEnd = vehicle.getBattery().getLeftOverCapacity() / this.getChargingSpeed();
        Date intervalDate = convertDoubleToDate(intervalToChargingEnd);
        if(intervalDate == null){
            System.out.println("Estimating the possible end Date is not possible.");
        } else {
            long sum = startDate.getTime() + intervalDate.getTime();
            estimatedEndDate = new Date(sum);
            System.out.println("The estimated end time for the session " + estimatedEndDate);
        }
    }

    /**
     * Converting the double time in a date format to add it to the start date.
     * @param intervalToChargingEnd Given interval to the end in double.
     * @return The interval in a Date format.
     */
    private Date convertDoubleToDate(double intervalToChargingEnd){
        Map<Integer, String> map = splitDoubleString(String.valueOf(intervalToChargingEnd));

        DateFormat hoursFormat = new SimpleDateFormat("HH");
        DateFormat minutesFormat = new SimpleDateFormat("mm");
        DateFormat secondsFormat = new SimpleDateFormat("ss");
        DateFormat milisecondsFormat = new SimpleDateFormat("SSS");
        try {
            Date hoursDate  = hoursFormat.parse(map.get(1));
            Date minutesDate  = minutesFormat.parse(map.get(2));
            Date secondsDate  = secondsFormat.parse(map.get(3));
            Date millisecondsDate = milisecondsFormat.parse(map.get(4));

            System.out.println(hoursDate.getTime() + " " + minutesDate.getTime());
            Date date = new Date(hoursDate.getTime() + minutesDate.getTime() + secondsDate.getTime() + millisecondsDate.getTime());
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<Integer, String> splitDoubleString(String s){
        Map<Integer, String> map = new HashMap<>();
        String[] array = s.split("\\.");
        String hour;
        StringBuilder minutes = new StringBuilder();
        StringBuilder seconds = new StringBuilder();
        StringBuilder milliseconds = new StringBuilder();

        hour = array[0];
        if(hour.length() == 1){
            hour = "0" + hour;
        }

        String[] leftOver = array[1].split("");
        for(int i = 0; i < leftOver.length; i++){
            if(i < 2){
                minutes.append(leftOver[i]);
            } else if(i < 4){
                seconds.append(leftOver[i]);
            } else {
                milliseconds.append(leftOver[i]);
            }
        }

        map.put(1, hour);
        map.put(2, minutes.toString());
        map.put(3, seconds.toString());
        map.put(4, milliseconds.toString());
        return map;
    }

}
