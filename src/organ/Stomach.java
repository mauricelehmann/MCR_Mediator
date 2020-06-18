package organ;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import mediator.Brain;

/**
 * This class represent a stomach
 */
public class Stomach extends Organ {

    /**
     * Constructor
     * @param brain
     */
    public Stomach(Brain brain) {
        super(brain);
    }

    /**
     * Digest a given substance and add it to the body
     * @param substance the substance
     * @param systemResources the current body resources
     */
    public void digest(BodyResources substance, BodyResources systemResources) {

        System.out.println("Estomac : je digère une substance composée de : " + substance.toString());

        notifyDisplay("L'estomac digère une substance composée de : " + substance.toString());

        //Update chemicals
        systemResources.refill(substance);
    }

    @Override
    public double getSize() {
        return 150;
    }
}
