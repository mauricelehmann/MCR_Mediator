package mediator;

import bodyRessources.BodyResources;
import event.Event;

/**
 * Class representing a weird brain
 */
public class WeirdBrainState implements BrainState {
    private Brain _brain;

    /**
     * Constructor
     * @param brain the brain
     */
    public WeirdBrainState(Brain brain) {
        _brain = brain;
    }

    /**
     * Ask the body to run
     */
    @Override
    public void run() {
        _brain.heart.accelerate(2);
        _brain.legs.run();
    }

    /**
     * Notify event to the brain
     * @param event the event
     */
    @Override
    public void notifyEvent(Event event) {
        //some additional behavior here...
    }

    /**
     * Consume a substance
     * @param substance the substance
     */
    @Override
    public void eat(BodyResources substance) {
        _brain.stomach.digest(substance, _brain.getBrainResources());
    }

    /**
     * Make the body stress
     */
    @Override
    public void stress() {
        _brain.heart.accelerate(0.7);
    }

    /**
     * Process eyes vision
     * @param event the event seen
     */
    @Override
    public void processEyesVision(Event event) {
        if(event.getHallucination() != null && !event.getHallucination().isEmpty()) {
            _brain.mouth.say("Je vois : " + event.getHallucination());
        } else {
            _brain.mouth.say("Je vois : " + event.getDescription());
        }
    }

    /**
     * Ask oxygen to the body
     */
    @Override
    public void askOxygen(double value) {
        _brain.lungs.accelerate(value/2);
        _brain.heart.accelerate(value/2);
    }

    /**
     * Handle and signal its death
     */
    @Override
    public void die() {
         this.processEyesVision(new Event("un halo de lumière..."));
        _brain.mouth.say("J'arrive mes amis!");
    }
}
