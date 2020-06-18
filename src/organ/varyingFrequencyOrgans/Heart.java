package organ.varyingFrequencyOrgans;

import mediator.Brain;

/**
 * Heart manage the bloodflow and the puls frequency
 */
public class Heart extends VaryingFrequencyOrgan {

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
