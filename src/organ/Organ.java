package organ;

import bodyRessources.BodyRessources;
import mediator.Brain;

public abstract class Organ {

    private Brain mediator;
    /* Ressources */
    private BodyRessources ressources;

    public Organ(Brain mediator){
        this.mediator = mediator;
        ressources = new BodyRessources(10);
    }

    protected BodyRessources getRessources(){
        return ressources;
    }
    public Brain getMediator(){
        return mediator;
    }

    public void addOxygene(int value){
        ressources.setOxygenLevel(ressources.getOxygenLevel() + value);
    }


}
