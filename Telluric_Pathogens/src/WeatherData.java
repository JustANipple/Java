
public class WeatherData {
    private double temperature;

    private double humidity;

    private double rain;

    private int wetLeaf;

    public WeatherData(double temperature, double humidity, double rain) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.rain = rain;

        double vpd = ( 1 - (this.humidity/100)) * (6.11 * Math.pow(2.7182818285, ((17.47 * this.temperature) / (239 + this.temperature))));
        if(vpd < 1 || this.humidity > 90 || this.rain > 0) {
            this.wetLeaf = 1;
        } else {
            this.wetLeaf = 0;
        }
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }
}
