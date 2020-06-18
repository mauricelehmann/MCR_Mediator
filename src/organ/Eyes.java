package organ;

import event.Event;
import mediator.Brain;

/**
 * This class represents the eyes
 */
public class Eyes extends Organ {

    /**
     * Constructor
     * @param brain the brain
     */
    public Eyes(Brain brain) {
        super(brain);
    }

    /**
     * Makes the eyes see an event
     * @param event the event to see
     */
    public void see(Event event){
        notifyDisplay("Je vois : " + event.getDescription());
        brain.processEyesVision(event);
        brain.notifyEvent(event);
    }

    @Override
    public double getSize() {
        return 5;
    }
}
