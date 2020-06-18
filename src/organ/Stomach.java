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
     * @param brainResources the current body resources
     */
    public void digest(BodyResources substance, BodyResources brainResources) {

        System.out.println("Estomac : je digère une substance composée de : " + substance.toString());

        notifyDisplay("L'estomac digère une substance composée de : " + substance.toString());

        //Update chemicals
        brainResources.refill(ResourceType.Caffein, substance.getResourceAmount(ResourceType.Caffein));
        brainResources.refill(ResourceType.Psychedelic, substance.getResourceAmount(ResourceType.Psychedelic));
        brainResources.refill(ResourceType.Alcohol, substance.getResourceAmount(ResourceType.Alcohol));
        brainResources.refill(ResourceType.Protein, substance.getResourceAmount(ResourceType.Protein));
    }
}
