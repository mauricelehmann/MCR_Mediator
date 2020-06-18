package mediator;

import bodyRessources.BodyResources;
import event.Event;

/**
 * Interface defining a brain state methods
 */
public interface BrainState {

    /**
     * ask oxygen to the system
     */
    void askOxygen(double value);

    /**
     * Makes the body run
     */
    void run();

    /**
     * Notify event to this brain
     * @param event the event
     */
    void notifyEvent(Event event);

    /**
     * Consume a resources inside a substance
     * @param substance the substance
     */
    void eat(BodyResources substance);

    /**
     * Makes the body  stress
     */
    void stress();

    /**
     * Process eyes vision
     * @param event the event seen
     */
    void processEyesVision(Event event);

    /**
     * Signal and handle its death
     */
    void die();
}
