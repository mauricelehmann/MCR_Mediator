package organ.varyingFrequencyOrgans;

import mediator.Brain;
import organ.Organ;
import organ.varyingFrequencyOrgans.VaryingFrequencyOrgan;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.max;

public class Heart extends VaryingFrequencyOrgan {

    public Heart(Brain mediator) {
        super(mediator);
        frequency = 1.2; //1 beat per second is slow, but still normal !
        minFrequency = 1.0;
        task = new TimerTask() {
            @Override
            public void run() {
                mediator.bloodFlow();
            }
        };
    }

    @Override
    public double getSize() {
        return 50;
    }
}
