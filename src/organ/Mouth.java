package organ;

import mediator.Brain;

public class Mouth extends Organ {
    public Mouth(Brain mediator) {
        super(mediator);
    }

    public void say(String sentence){
        getMediator().askOxygen(10);
        System.out.println("Bouche : " + sentence);
    }

    @Override
    public double getSize() {
        return 20;
    }
}
