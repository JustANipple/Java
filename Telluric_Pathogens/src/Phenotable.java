import java.util.ArrayList;

public class Phenotable {
    private String coltureType;

    private ArrayList<Phenophase> phenophases;

    public Phenotable(String coltureType, ArrayList<Phenophase> phenophases) {
        this.coltureType = coltureType;
        this.phenophases = phenophases;
    }

    public String getColtureType() {
        return coltureType;
    }

    public void setColtureType(String coltureType) {
        this.coltureType = coltureType;
    }

    public ArrayList<Phenophase> getPhenophases() {
        return phenophases;
    }

    public void setPhenophases(ArrayList<Phenophase> phenophases) {
        this.phenophases = phenophases;
    }
}
