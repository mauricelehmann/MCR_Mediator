package organ;

import mediator.Brain;

public class Mouth extends Organ {
    public Mouth(Brain mediator) {
        super(mediator);
    }

    public void say(String phrase){
        System.out.println("Bouche : " + phrase);
    }

}
