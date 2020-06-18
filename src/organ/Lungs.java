package organ;

import bodyRessources.ResourceType;
import mediator.Brain;

/**
 * TODO : Supprimer ? et le notifyDisplay("Respire..."); on le met ou ? dans l'autre Lungs ?
 */
public class Lungs extends Organ {
    private final int lungCapacity = 10;

    public Lungs(Brain mediator){
        super(mediator);
    }

    public void breath(){
        System.out.println("Poumons : respire...");
        brain.refillBlood(ResourceType.Oxygen, lungCapacity);
        notifyDisplay("Respire...");
    }
}
