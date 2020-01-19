package EnergySources;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Solar extends Thread{
    double wattPeak;
    double temperatureCoefficient;
    double sunnyHours; //Hours in ms
    double currentTemperature;
    double cellTemperature;
    private Timer timer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            //currentTemperature = (Math.random() * ((80.0 - 0.0) + 1)) + 0.0;
        }
    };


    public Solar(double wattPeak, double temperatureCoefficient, double cellTemperature) {
        this.wattPeak = wattPeak;
        this.temperatureCoefficient = temperatureCoefficient;
        this.cellTemperature = cellTemperature;
        this.currentTemperature =  (Math.random() * ((80.0 - 0.0) + 1)) + 0.0;
        this.sunnyHours = (double) 28800000.0 / 3600000.0;
        this.start();
    }

    public Solar(double wattPeak, double temperatureCoefficient, long sunnyHoursInMilliSec, double currentTemperature, double cellTemperature) {
        this.wattPeak = wattPeak;
        this.temperatureCoefficient = temperatureCoefficient;
        this.sunnyHours = ((double) sunnyHoursInMilliSec / 3600000.0);
        this.currentTemperature = currentTemperature;
        this.cellTemperature = cellTemperature;
        this.start();
    }

    public double getWattPeak() {
        return wattPeak;
    }

    /**
     * - monokristallines Silizium -0,40%/K
     * - polykristallines Silizium -0,45%/K
     * - Cadmium-Telluid           -0,20%/K
     * - amorphes Silizium         -0,40%/K
     * @param wattPeak The
     */
    public void setWattPeak(double wattPeak) {
        this.wattPeak = wattPeak;
    }

    public double getTemperatureCoefficient() {
        return temperatureCoefficient;
    }

    public void setTemperatureCoefficient(double temperatureCoefficient) {
        this.temperatureCoefficient = temperatureCoefficient;
    }

    @Override
    public void run() {
        super.run();
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 1000 , 1080000);
    }


    public double dailyOutput(){
        double bestCase = wattPeak * sunnyHours;

        double percent = temperatureCoefficient * Math.abs(differenceBetweenTemperature());

        if(differenceBetweenTemperature() > 0){

        } else {

        }

        return 0.0;
    }

    public double hourOutput(){

        return 0.0;
    }

    private double differenceBetweenTemperature(){
        BigDecimal bd = BigDecimal.valueOf((currentTemperature - cellTemperature));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
