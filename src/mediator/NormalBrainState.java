package mediator;
import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import event.Event;
import organ.Organ;

/**
 * Class representing a normal brain
 */
public class NormalBrainState implements BrainState {
    private Brain _brain;

    /**
     * Constructor
     * @param brain the master brain
     */
    public NormalBrainState(Brain brain){
        _brain = brain;
    }

    /**
     * Ask the body to run
     */
    @Override
    public void run() {
        _brain.legs.run();
    }

    /**
     * Notify event to the brain
     * @param event the event
     */
    public void notifyEvent(Event event) {
        _brain.getGameManager().takeAction(event);
    }

    /**
     * Consume a substance
     * @param substance the substance
     */
    @Override
    public void consume(BodyResources substance) {
        System.out.println("Normal brain : J'envoi la substance à l'estomac ");
        _brain.stomach.digest(substance, _brain.getBrainResources());
    }

    /**
     * Make the body stress
     */
    @Override
    public void stress() {
        _brain.heart.accelerate(2);
    }

    /**
     * Process eyes vision
     * @param event the event seen
     */
    @Override
    public void processEyesVision(Event event) {
        _brain.mouth.say("Je vois : " + event.getDescription());
    }

    /**
     * Handle and signal its death
     */
    @Override
    public void die() {
        _brain.mouth.say("aaarrrrrgggh...");
    }

    /**
     * Ask oxygen to the body
     */
    @Override
    public void askOxygen(){
        _brain.lungs.breath();
        _brain.heart.accelerate(1);
    }
}
