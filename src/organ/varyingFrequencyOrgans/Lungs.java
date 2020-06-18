package organ.varyingFrequencyOrgans;

import bodyRessources.ResourceType;
import mediator.Brain;
import organ.Organ;
import organ.varyingFrequencyOrgans.VaryingFrequencyOrgan;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.max;

public class Lungs extends VaryingFrequencyOrgan {
    private final int lungCapacity = 10;

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
