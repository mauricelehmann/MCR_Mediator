package organ;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import mediator.Brain;

public class Stomach extends Organ {
    public Stomach(Brain mediator) {
        super(mediator);
    }

    public void digest(BodyResources substance, BodyResources systemResources) {

        System.out.println("Estomac : je digère une substance composée de : " + substance.toString());

        notifyDisplay("L'estomac digère du stuff");

        //Update chemicals
        systemResources.refill(substance);
    }

    @Override
    public double getSize() {
        return 150;
    }
}
