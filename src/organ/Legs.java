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
        activityFactor = 0.1;
    }

    /**
     * Make the legs run
     */
    public void run() {
        activityFactor = 6;
        System.out.println("Jambes : en course");
       notifyDisplay("En course!");
    }

    /**
     * Make the legs walk
     */
    public void walk() {
        activityFactor = 2;
        System.out.println("Jambes : en marche");
        notifyDisplay("En marche");
    }

    @Override
    public double getSize() {
        return 250;
    }
}
