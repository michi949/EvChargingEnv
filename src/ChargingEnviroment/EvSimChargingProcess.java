package ChargingEnviroment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EvSimChargingProcess extends Thread {
    private Date startDate;
    private Date updateDate;
    private Date estimatedEndDate;
    private double chargingSpeed;
    private EvSimVehicle evSimVehicle;
    private boolean isCharging;
    int lastLogUpdate = 0;

    private Timer timer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if(isCharging()){
                if(evSimVehicle.getEvSimBattery().getCurrentCapacity() >= evSimVehicle.getEvSimBattery().getCapacity()){
                    finishChargingProcess();
                }

                double currentCapacity = evSimVehicle.getEvSimBattery().getCurrentCapacity() * 3600;
                double newCapacity = currentCapacity + chargingSpeed;
                evSimVehicle.getEvSimBattery().setCurrentCapacity(newCapacity/3600);
                if(lastLogUpdate < evSimVehicle.getEvSimBattery().getCurrentCapacityInPercent()){
                    System.out.println("Date: " + new Date() + " | Current charging state is: " + evSimVehicle.getEvSimBattery().getCurrentCapacityInPercent() + "%" + " | Current Capacity: " + evSimVehicle.getEvSimBattery().getCurrentCapacity());
                    lastLogUpdate = evSimVehicle.getEvSimBattery().getCurrentCapacityInPercent();
                    //System.out.println("Current capactity of the vehicle: " + round(vehicle.getBattery().getCurrentCapacity(), 2) + "kWh from: " + round(vehicle.getBattery().getCapacity(), 2) + "kWh.");
                }
            }
        }
    };

    public EvSimChargingProcess(double chargingSpeed, EvSimVehicle evSimVehicle) {
        this.chargingSpeed = chargingSpeed;
        this.evSimVehicle = evSimVehicle;
    }

    public double getChargingSpeed() {
        return chargingSpeed;
    }

    public EvSimVehicle getEvSimVehicle() {
        return evSimVehicle;
    }

    public void setEvSimVehicle(EvSimVehicle evSimVehicle) {
        this.evSimVehicle = evSimVehicle;
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

    public void changeChargingSpeed(double chargingSpeed){
        if(!isCharging()){
            System.out.println("Charging speed changed to: " + chargingSpeed + "W.");
        } else {
            startDate = new Date();
            estimatePossibleEndDate();
            System.out.println("Change charging speed to: " + chargingSpeed + "W.");
        }
        this.chargingSpeed = chargingSpeed;
    }

    public void startChargingProcess(){
        if(chargingSpeed == 0 || evSimVehicle == null){
            return;
        }

        setCharging(true);
        startDate = new Date();
        if(timer == null){
            timer = new Timer();
            timer.scheduleAtFixedRate(timerTask, 1000 , 1000);
        }
        this.estimatePossibleEndDate();
        //System.out.println("Charging process start time: " + startDate + "with speed " + chargingSpeed + "kW and estimated end time is " + estimatedEndDate);
        this.start();
    }

    public void stopChargingProcess(){
       // System.out.println("Charging process stop time: " + new Date());
        isCharging = false;
    }

    public void finishChargingProcess(){
        isCharging = false;
        timer.purge();
        //System.out.println("Charging process has ended at " + new Date() + "and has a current capacity of " + evSimVehicle.getEvSimBattery().getCurrentCapacity());
    }

    /**
     * Calculates the end date of the charging process with the given capacity of the battery and the charging power.
     * ChargingTime = BatteryCapacity / ChargingPower
     */
    public void estimatePossibleEndDate(){
        //System.out.println(vehicle.getBattery().getLeftOverCapacity());
        //System.out.println(this.getChargingSpeed());
        double intervalToChargingEnd = evSimVehicle.getEvSimBattery().getLeftOverCapacity() / this.getChargingSpeed();
        Date intervalDate = convertDoubleToDate(intervalToChargingEnd);
        if(intervalDate == null){
            System.out.println("Estimating the possible end Date is not possible.");
        } else {
            long sum = startDate.getTime() + intervalDate.getTime();
            estimatedEndDate = new Date(sum);
            //System.out.println("The estimated end time for the session " + estimatedEndDate);
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
        hoursFormat.setTimeZone(TimeZone.getTimeZone("MEZ"));
        DateFormat minutesFormat = new SimpleDateFormat("mm");
        minutesFormat.setTimeZone(TimeZone.getTimeZone("MEZ"));
        DateFormat secondsFormat = new SimpleDateFormat("ss");
        secondsFormat.setTimeZone(TimeZone.getTimeZone("MEZ"));
        DateFormat milisecondsFormat = new SimpleDateFormat("SSS");
        milisecondsFormat.setTimeZone(TimeZone.getTimeZone("MEZ"));
        try {
            long hoursDate  = hoursFormat.parse(map.get(1)).getTime();
            long minutesDate  = minutesFormat.parse(map.get(2)).getTime();
            long secondsDate  = secondsFormat.parse(map.get(3)).getTime();

            //System.out.println(hoursDate.getTime() + " " + minutesDate.getTime() + " " + secondsDate.getTime() );
            //long dateLong = hoursDate.getTime() + minutesDate.getTime() + secondsDate.getTime() + millisecondsDate.getTime();
            Date date = new Date((hoursDate + minutesDate + secondsDate));
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


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
