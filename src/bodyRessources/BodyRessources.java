package bodyRessources;

public class BodyRessources {

    private int oxygenLevel;
    private int caffeinLevel;

    public BodyRessources(int oxygenLevel){
        this.oxygenLevel = oxygenLevel;
        caffeinLevel = 0;
    }

    public void setOxygenLevel(int value){
        oxygenLevel = value;
    }

    public int getOxygenLevel(){
        return oxygenLevel;
    }

    public int getCaffeinLevel() {
        return caffeinLevel;
    }

    public void setCaffeinLevel(int caffeinLevel) {
        this.caffeinLevel = caffeinLevel;
    }
}
