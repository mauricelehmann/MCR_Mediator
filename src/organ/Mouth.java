package organ;

import mediator.Brain;

public class Mouth extends Organ {
    public Mouth(Brain mediator) {
        super(mediator);
    }

    public void say(String sentence){
        getMediator().askOxygen();
        System.out.println("Bouche : " + sentence);
        notifyDisplay("Je dis : " + sentence);
    }

}
