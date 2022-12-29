import java.lang.reflect.Array;

public class PathogenModel {
    private String pathogenName;
    private String cropType;
    private String modelName;
    private int fromPhenophase;
    private int toPhenophase;
    private int fromDailyTemperature;
    private int toDailyTemperature;
    private int wetLeafHours;
    private int stopWetLeafAfterHours;
    private int DailyHumidity;
    private double dailyRain;

    public PathogenModel(String pathogenName, String cropType, String modelName, int fromPhenophase, int toPhenophase, int fromDailyTemperature, int toDailyTemperature, int wetLeafHours, int stopWetLeafAfterHours, int dailyHumidity, double dailyRain) {
        this.pathogenName = pathogenName;
        this.cropType = cropType;
        this.modelName = modelName;
        this.fromPhenophase = fromPhenophase;
        this.toPhenophase = toPhenophase;
        this.fromDailyTemperature = fromDailyTemperature;
        this.toDailyTemperature = toDailyTemperature;
        this.wetLeafHours = wetLeafHours;
        this.stopWetLeafAfterHours = stopWetLeafAfterHours;
        DailyHumidity = dailyHumidity;
        this.dailyRain = dailyRain;
    }

    public String getPathogenName() {
        return pathogenName;
    }

    public void setPathogenName(String pathogenName) {
        this.pathogenName = pathogenName;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getFromPhenophase() {
        return fromPhenophase;
    }

    public void setFromPhenophase(int fromPhenophase) {
        this.fromPhenophase = fromPhenophase;
    }

    public int getToPhenophase() {
        return toPhenophase;
    }

    public void setToPhenophase(int toPhenophase) {
        this.toPhenophase = toPhenophase;
    }

    public int getFromDailyTemperature() {
        return fromDailyTemperature;
    }

    public void setFromDailyTemperature(int fromDailyTemperature) {
        this.fromDailyTemperature = fromDailyTemperature;
    }

    public int getToDailyTemperature() {
        return toDailyTemperature;
    }

    public void setToDailyTemperature(int toDailyTemperature) {
        this.toDailyTemperature = toDailyTemperature;
    }

    public int getWetLeafHours() {
        return wetLeafHours;
    }

    public void setWetLeafHours(int wetLeafHours) {
        this.wetLeafHours = wetLeafHours;
    }

    public int getStopWetLeafAfterHours() {
        return stopWetLeafAfterHours;
    }

    public void setStopWetLeafAfterHours(int stopWetLeafAfterHours) {
        this.stopWetLeafAfterHours = stopWetLeafAfterHours;
    }

    public int getDailyHumidity() {
        return DailyHumidity;
    }

    public void setDailyHumidity(int dailyHumidity) {
        DailyHumidity = dailyHumidity;
    }

    public double getDailyRain() {
        return dailyRain;
    }

    public void setDailyRain(double dailyRain) {
        this.dailyRain = dailyRain;
    }
}
