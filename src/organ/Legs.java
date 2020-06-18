package organ;

import mediator.Brain;


public class Legs extends Organ {
    public Legs(Brain mediator) {
        super(mediator);
    }

    public void run() {
        System.out.println("Jambes : en course");
    }

    public void walk() {
        System.out.println("Jambes : en marche");
    }
}
