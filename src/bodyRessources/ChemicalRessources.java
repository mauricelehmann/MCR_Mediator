package bodyRessources;

public class ChemicalRessources {

    private int _caffeinLevel;
    private int _alcoolLevel;
    private int _pschoticLevel;

    public ChemicalRessources(int caffeinLevel, int alcoolLevel, int pschoticLevel){
        _caffeinLevel = caffeinLevel;
        _alcoolLevel = alcoolLevel;
        _pschoticLevel = pschoticLevel;
    }
    public int getCaffeinLevel() {
        return _caffeinLevel;
    }

    public int getAlcoolLevel() {
        return _alcoolLevel;
    }

    public int getPschoticLevel() {
        return _pschoticLevel;
    }

    public void setCaffeinLevel(int _caffeinLevel) {
        this._caffeinLevel = _caffeinLevel;
    }

    public void setAlcoolLevel(int _alcoolLevel) {
        this._alcoolLevel = _alcoolLevel;
    }

    public void setPschoticLevel(int _pschoticLevel) {
        this._pschoticLevel = _pschoticLevel;
    }
}
