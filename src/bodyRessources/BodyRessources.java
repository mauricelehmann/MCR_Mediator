package bodyRessources;

public class BodyRessources {

    private int oxygenLevel;

    public BodyRessources(int oxygenLevel){
        this.oxygenLevel = oxygenLevel;
    }

    public void setOxygenLevel(int value){
        oxygenLevel = value;
    }

    public int getOxygenLevel(){
        return oxygenLevel;
    }
}