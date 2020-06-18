package mediator;

import bodyRessources.BodyResources;
import event.Event;

/**
 * Class representing an excited brain
 */
public class ExcitedBrainState implements BrainState {

    /**
     * Transform a string into an excited string
     */
    private static class ExcitedTalkDecorator {

        /**
         * Decorate the string to gives an excited effect
         * @param sentence the sentence to transform
         * @return the new string
         */
        public static String decorate(String sentence) {
            return sentence.toUpperCase();
        }
    }

    private Brain _brain;

    /**
     * Constructor
     * @param brain the master brain
     */
    public ExcitedBrainState(Brain brain) {
        _brain = brain;
    }

    /**
     * Ask oxygen to the body
     */
    @Override
    public void askOxygen(double value) {
        _brain.lungs.accelerate(1+value*2); //*2 parce qu'il super EXCITEEEE
    }

    /**
     * Ask the bofy to run
     */
    @Override
    public void run() {
        _brain.mouth.say(ExcitedTalkDecorator.decorate("En avant!"));
        for (int i = 0; i < 3; i++) {
            _brain.legs.run();
        }
    }

    /**
     * Notify event to the brain
     * @param event the event
     */
    @Override
    public void notifyEvent(Event event) {
        // FIXME: maybe pull this up into brain
        _brain.getGameManager().takeAction(event);
    }

    /**
     * Consume a substance
     * @param substance the substance
     */
    @Override
    public void eat(BodyResources substance) {
        _brain.mouth.say(ExcitedTalkDecorator.decorate("Miam miam"));
        _brain.stomach.digest(substance, _brain.getBrainResources());
    }

    /**
     * Make the body stress
     */
    @Override
    public void stress(){
       _brain.heart.accelerate(10);
    }

    /**
     * Process eyes vision
     * @param event the event seen
     */
    @Override
    public void processEyesVision(Event event) {
        String description = event.getDescription().toUpperCase();
        _brain.mouth.say(ExcitedTalkDecorator.decorate("Je vois : " + description + " !!!"));
    }

    /**
     * Handle and signal its death
     */
    @Override
    public void die() {
        _brain.mouth.say(ExcitedTalkDecorator.decorate("aaah!!! Je meurs !!!"));
    }
}
