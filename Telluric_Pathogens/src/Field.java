import java.util.ArrayList;

public class Field {
    private String cropType;

    private ArrayList<WeatherData> datas;

    public Field(String cropType, ArrayList<WeatherData> datas) {
        this.cropType = cropType;
        this.datas = datas;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public ArrayList<WeatherData> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<WeatherData> datas) {
        this.datas = datas;
    }
}
