package mediator;
import event.Event;
import organ.*;

/**
 * Abstract mediator
 */
public abstract class BodyMediator {

    //TODO : faire un container special pour les oganes?
    protected Lungs lungs;
    protected Legs legs;
    public Eyes eyes;


    public BodyMediator(){
        /* Adding the organs */
        this.lungs = new Lungs(this);
        this.legs = new Legs(this);
        this.eyes = new Eyes(this);


    }

    /* Abstract methods : behaviors have to be implemented in the concret brains */
    public void askOxygen(Organ asker, int value){
        lungs.pump(value);
        asker.addOxygene(value);
    }

    public abstract void notifyEvent(Event event);
}
