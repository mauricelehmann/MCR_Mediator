package organ;

import mediator.Brain;

/**
 * This class represents the legs
 */
public class Legs extends Organ {

    /**
     * Constructor
     * @param brain
     */
    public Legs(Brain brain) {
        super(brain);
    }

    /**
     * Make the legs run
     */
    public void run() {
        System.out.println("Jambes : en course");
       notifyDisplay("En course!");
    }

    /**
     * Make the legs walk
     */
    public void walk() {
        System.out.println("Jambes : en marche");
        notifyDisplay("En marche");
    }
}
