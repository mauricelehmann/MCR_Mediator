package organ.varyingFrequencyOrgans;

import mediator.Brain;
import organ.Organ;
import organ.varyingFrequencyOrgans.VaryingFrequencyOrgan;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.max;

public class Heart extends VaryingFrequencyOrgan {

    /**
     * Constructor : build a heart with a starting Frequency
     * @param mediator the heart's mediator
     */
    public Heart(Brain mediator) {
        super(mediator);
        frequency = 1.2; //1 beat per second is slow, but still normal !
        minFrequency = 1.0;
        task = new Runnable() {
            @Override
            public void run() {
                mediator.bloodFlow();
                if(frequency > 100){
                    notifyDisplay("Je fais une crise cardiaque!");  //TODO kler écrasé par pompe
                    brain.die();
                }
            }
        };
    }

    @Override
    public double getSize() {
        return 50;
    }
}
