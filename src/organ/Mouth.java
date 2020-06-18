package organ;

import mediator.Brain;

/**
 * This class represents the mouth
 */
public class Mouth extends Organ {

    /**
     * Constructor
     * @param brain
     */
    public Mouth(Brain brain) {
        super(brain);
    }

    /**
     * Make the mouth say a sentence
     * @param sentence the sentence
     */
    public void say(String sentence){
        System.out.println("Bouche : " + sentence);
        notifyDisplay("Je dis : " + sentence);
    }

    @Override
    public double getSize() {
        return 20;
    }
}
