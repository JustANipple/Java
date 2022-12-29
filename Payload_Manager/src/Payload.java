import java.time.LocalDateTime;

public class Payload {

    private LocalDateTime dateTime;
    private int battery;
    private double ec;
    private double humidity;
    private double temperature;

    public Payload(LocalDateTime dateTime,int battery, double ec, double humidity, double temperature) {
        this.dateTime = dateTime;
        this.battery = battery;
        this.ec = ec;
        this.humidity = humidity;
        this.temperature = temperature;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getBattery() {
        return battery;
    }

    public double getEc() {
        return ec;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return  dateTime.getHour() + ":" +
                dateTime.getMinute() + ":" +
                dateTime.getSecond() +
                ";  " + battery +
                ";  " + ec +
                ";  " + humidity +
                ";  " + temperature;
    }
}
