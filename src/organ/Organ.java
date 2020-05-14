package organ;

import bodyRessources.BodyRessources;
import mediator.BodyMediator;

public abstract class Organ {

    private BodyMediator mediator;
    /* Ressources */
    private BodyRessources ressources;

    public Organ(BodyMediator mediator){
        this.mediator = mediator;
        ressources = new BodyRessources(10);
    }

    protected BodyRessources getRessources(){
        return ressources;
    }
    public BodyMediator getMediator(){
        return mediator;
    }

    public void addOxygene(int value){
        ressources.setOxygenLevel(ressources.getOxygenLevel() + value);
    }


}
