public class Phenophase {
    public int phaseNumber;
    public String description;
    public int growingDegreeDays;
    public double kc;

    public Phenophase(int phaseNumber, String description, int growingDegreeDays, double kc) {
        this.phaseNumber = phaseNumber;
        this.description = description;
        this.growingDegreeDays = growingDegreeDays;
        this.kc = kc;
    }

    public int getPhaseNumber() {
        return phaseNumber;
    }

    public void setPhaseNumber(int phaseNumber) {
        this.phaseNumber = phaseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGrowingDegreeDays() {
        return growingDegreeDays;
    }

    public void setGrowingDegreeDays(int growingDegreeDays) {
        this.growingDegreeDays = growingDegreeDays;
    }

    public double getKc() {
        return kc;
    }

    public void setKc(double kc) {
        this.kc = kc;
    }
}
