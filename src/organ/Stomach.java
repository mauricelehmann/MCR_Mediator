package organ;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import mediator.Brain;

public class Stomach extends Organ {
    public Stomach(Brain mediator) {
        super(mediator);
    }

    public void digest(BodyResources substance, BodyResources brainResources) {

        System.out.println("Estomac : je digère une substance composée de : " + substance.toString());

        notifyDisplay("L'estomac digère du stuff");

        //Update chemicals
        brainResources.refill(ResourceType.Caffein, substance.getResourceAmount(ResourceType.Caffein));
        brainResources.refill(ResourceType.Psychedelic, substance.getResourceAmount(ResourceType.Psychedelic));
        brainResources.refill(ResourceType.Alcohol, substance.getResourceAmount(ResourceType.Alcohol));
        brainResources.refill(ResourceType.Protein, substance.getResourceAmount(ResourceType.Protein));
    }
}
