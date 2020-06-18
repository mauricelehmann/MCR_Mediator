package mediator;
import bodyRessources.BodyResources;
import event.Event;

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

    @Override
    public void askOxygen(double value) {
        _brain.lungs.accelerate(1+value/10);
        _brain.heart.accelerate(1+value/20);
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
        //some additional behavior here...
    }

    /**
     * Consume a substance
     * @param substance the substance
     */
    @Override
    public void eat(BodyResources substance) {
        System.out.println("Normal brain : J'envoi la substance à l'estomac ");
        _brain.stomach.digest(substance, _brain.getBrainResources());
    }

    /**
     * Make the body stress
     */
    @Override
    public void stress() {
        _brain.heart.accelerate(1.2);
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
}
