import java.util.ArrayList;

public class Phenotable {
    private String cropType;

    private ArrayList<Phenophase> phenophases;

    public Phenotable(String cropType, ArrayList<Phenophase> phenophases) {
        this.cropType = cropType;
        this.phenophases = phenophases;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public ArrayList<Phenophase> getPhenophases() {
        return phenophases;
    }

    public void setPhenophases(ArrayList<Phenophase> phenophases) {
        this.phenophases = phenophases;
    }
}
