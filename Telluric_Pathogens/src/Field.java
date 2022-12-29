import java.util.ArrayList;

public class Field {
    private String coltureType;

    private ArrayList<WeatherData> datas;

    public Field(String coltureType, ArrayList<WeatherData> datas) {
        this.coltureType = coltureType;
        this.datas = datas;
    }

    public String getColtureType() {
        return coltureType;
    }

    public void setColtureType(String coltureType) {
        this.coltureType = coltureType;
    }

    public ArrayList<WeatherData> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<WeatherData> datas) {
        this.datas = datas;
    }
}
