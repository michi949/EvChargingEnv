package EnergySources;

public class Solar {
    double wattPeak;
    double weatherCoefficient;

    public Solar(double wattPeak, double weatherCoefficient) {
        this.wattPeak = wattPeak;
        this.weatherCoefficient = weatherCoefficient;
    }

    public double getWattPeak() {
        return wattPeak;
    }

    public void setWattPeak(double wattPeak) {
        this.wattPeak = wattPeak;
    }

    public double getWeatherCoefficient() {
        return weatherCoefficient;
    }

    public void setWeatherCoefficient(double weatherCoefficient) {
        this.weatherCoefficient = weatherCoefficient;
    }

    public double dailyOutput(){

        return 0.0;
    }

    public double hourOutput(){

        return 0.0;
    }
}
