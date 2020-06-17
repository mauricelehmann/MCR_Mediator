package organ;

import bodyRessources.ResourceType;
import mediator.Brain;

public class Lungs extends Organ {
    private final int lungCapacity = 10;

    public Lungs(Brain mediator){
        super(mediator);
    }

    public void breath(){
        mediator.refillBlood(ResourceType.Oxygen, lungCapacity);
    }

    public void pump(int i) {
    }
}
