package mediator;
import event.Event;
import organ.*;

import java.util.ArrayList;

/**
 * Abstract mediator
 */
public abstract class Brain implements BodyMediator{

    //TODO : faire un container special pour les oganes?
    protected Lungs lungs;
    protected Legs legs;
    public Eyes eyes;
    public Heart heart;


    public Brain(){
        /* Adding the organs */
        this.lungs = new Lungs(this);
        this.legs = new Legs(this);
        this.eyes = new Eyes(this);
        //TODO : to refactor, it should not be the brain's resposibility to create the organs

    }

    /* Abstract methods : behaviors have to be implemented in the concret brains */
    public void askOxygen() throws Exception {
        lungs.breathe();
        heart.accelerate(1.5);
    }

    public abstract void notifyEvent(Event event);
}
