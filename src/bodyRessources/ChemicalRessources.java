package bodyRessources;

public class ChemicalRessources {
    private int _caffeinLevel;
    private int _alcoholLevel;
    private int _psychedelicLevel;
    private int _proteinLevel;

    public ChemicalRessources(int caffeinLevel, int alcoolLevel, int psychedelicLevel, int proteinLevel){
        _caffeinLevel = caffeinLevel;
        _alcoholLevel = alcoolLevel;
        _psychedelicLevel = psychedelicLevel;
        _proteinLevel = proteinLevel;
    }
    public int getCaffeinLevel() {
        return _caffeinLevel;
    }

    public int getAlcoolLevel() {
        return _alcoholLevel;
    }

    public int getPschoticLevel() {
        return _psychedelicLevel;
    }

    public int getProteinLevel() {
        return _proteinLevel;
    }

    public void setCaffeinLevel(int caffeinLevel) {
        _caffeinLevel = caffeinLevel;
    }

    public void setAlcoolLevel(int alcoolLevel) {
        _alcoholLevel = alcoolLevel;
    }

    public void setPschoticLevel(int psychedelicLevel) {
        _psychedelicLevel = psychedelicLevel;
    }

    public void setProteinLevel(int proteinLevel) {
        _proteinLevel = proteinLevel;
    }

}
