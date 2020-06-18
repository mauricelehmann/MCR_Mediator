package mediator;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import event.Event;
import organ.Organ;

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
        _brain.heart.accelerate(10);
        _brain.legs.run();
    }

    /**
     * Notify event to the brain
     * @param event the event
     */
    @Override
    public void notifyEvent(Event event) {
        _brain.getGameManager().takeAction(event);
    }

    /**
     * Consume a substance
     * @param substance the substance
     */
    @Override
    public void consume(BodyResources substance) {
        substance.consume(ResourceType.Alcohol, 10.0);
        substance.consume(ResourceType.Caffein, 10.0);
        substance.consume(ResourceType.Psychedelic, -5.0);

        _brain.stomach.digest(substance, _brain.getBrainResources());
    }

    /**
     * Make the body stress
     */
    @Override
    public void stress() {
        _brain.heart.accelerate(-10);
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
    public void askOxygen() {
        _brain.lungs.breath();
        _brain.heart.accelerate(-1);
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
