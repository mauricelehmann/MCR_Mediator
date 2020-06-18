package organ;

import bodyRessources.ResourceType;
import mediator.Brain;

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
