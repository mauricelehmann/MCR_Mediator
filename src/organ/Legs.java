package organ;

import mediator.Brain;


public class Legs extends Organ {
    public Legs(Brain mediator) {
        super(mediator);
        activityFactor = 0.1;
    }

    public void run() {
        activityFactor = 6;
    }

    public void walk() {
        activityFactor = 2;
    }

    @Override
    public double getSize() {
        return 250;
    }
}
