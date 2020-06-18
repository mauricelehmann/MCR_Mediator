package organ.varyingFrequencyOrgans;

import bodyRessources.ResourceType;
import mediator.Brain;

/**
 * Lungs mangage the refill of oxygen in the system
 */
public class Lungs extends VaryingFrequencyOrgan {
    private final int lungCapacity = 100;

    public Lungs(Brain mediator){
        super(mediator);
        frequency = 0.2;
        minFrequency = 0.1;
        task = new Runnable() {
            @Override
            public void run() {
                mediator.refillBlood(ResourceType.Oxygen, lungCapacity);
            }
        };
    }

    @Override
    public double getSize() {
        return 100;
    }
}
