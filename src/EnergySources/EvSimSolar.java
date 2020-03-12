package EnergySources;

import EnergySources.Connector.WeatherConnector;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class EvSimSolar extends Thread{
    double wattPeak;
    double temperatureCoefficient;
    double sunnyHours; //Hours in ms
    Date sunrise;
    Date sunset;
    double currentTemperature;
    double cellTemperature;
    boolean useWeatherAPI;
    int clouds;
    private Timer timer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            updateWeatherData();
        }
    };


    public EvSimSolar(double wattPeak, double temperatureCoefficient, double cellTemperature, boolean useWeatherAPI) {
        this.wattPeak = wattPeak;
        this.temperatureCoefficient = temperatureCoefficient;
        this.cellTemperature = cellTemperature;
        this.useWeatherAPI = useWeatherAPI;
        this.currentTemperature =  0;
        this.sunnyHours = 0;
        this.clouds = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 24);
        this.sunrise = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 45);
        this.sunset = calendar.getTime();
        this.updateWeatherData();
        this.start();
    }

    public EvSimSolar(double wattPeak, double temperatureCoefficient, long sunnyHoursInMilliSec, double currentTemperature, double cellTemperature) {
        this.wattPeak = wattPeak;
        this.temperatureCoefficient = temperatureCoefficient;
        this.sunnyHours = ((double) sunnyHoursInMilliSec / 3600000.0);
        this.currentTemperature = currentTemperature;
        this.cellTemperature = cellTemperature;
        this.useWeatherAPI = false;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 24);
        this.sunrise = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 45);
        this.sunset = calendar.getTime();
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

    public boolean isUseWeatherAPI() {
        return useWeatherAPI;
    }

    public void setUseWeatherAPI(boolean useWeatherAPI) {
        this.useWeatherAPI = useWeatherAPI;
    }

    public double getSunnyHours() {
        return sunnyHours;
    }

    public void setSunnyHours(double sunnyHours) {
        this.sunnyHours = sunnyHours;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public void setSunrise(Date sunrise) {
        this.sunrise = sunrise;
    }

    public Date getSunset() {
        return sunset;
    }

    public void setSunset(Date sunset) {
        this.sunset = sunset;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public double getCellTemperature() {
        return cellTemperature;
    }

    public void setCellTemperature(double cellTemperature) {
        this.cellTemperature = cellTemperature;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    @Override
    public void run() {
        super.run();
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 1000 , 1080000);
    }

    /**
     * Doesnt supply in night.
     * @return
     */
    public double dailyOutput(){
        Date currentDate = new Date();
        if(currentDate.getTime() > sunrise.getTime() &&
                currentDate.getTime() < sunset.getTime()) {
            double bestCase = wattPeak * sunnyHours;
            double percent = temperatureCoefficient * Math.abs(differenceBetweenTemperature());

            if(differenceBetweenTemperature() > 0){
                return (bestCase / 100) * (100 - percent);
            } else {
                return (bestCase / 100) * (100 + percent);
            }
        } else {
         return 0;
        }
    }

    public double hourOutput(){
        if(dailyOutput() == 0){
            return 0;
        }

        double daily =  dailyOutput() / sunnyHours;
        double onePercent = daily / 100;
        double possiblePower = onePercent * (100 - this.clouds);
        return possiblePower;
    }

    private double differenceBetweenTemperature(){
        BigDecimal bd = BigDecimal.valueOf((currentTemperature - cellTemperature));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private void updateWeatherData(){
        if(useWeatherAPI){
            WeatherConnector weatherConnector = new WeatherConnector();
            Map<String, Double> map = weatherConnector.performRequest();
            this.currentTemperature = map.get("temp");
            this.sunnyHours = map.get("dayLight");
            this.clouds = map.get("cloud").intValue();
            this.sunrise = weatherConnector.getSunrise();
            this.sunset = weatherConnector.getSunset();
        } else {
            this.currentTemperature =  (Math.random() * ((80.0 - 0.0) + 1)) + 0.0;
            this.sunnyHours = (double) 28800000.0 / 3600000.0;
            this.clouds = 35;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 7);
            calendar.set(Calendar.MINUTE, 24);
            this.sunrise = calendar.getTime();
            calendar.set(Calendar.HOUR_OF_DAY, 19);
            calendar.set(Calendar.MINUTE, 45);
            this.sunset = calendar.getTime();
        }
    }
}
