package mediator;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import event.Event;

/**
 * Class representing a drunk brain
 */
public class DrunkBrainState implements BrainState {

    /**
     * Transform a string into a drunkish string
     */
    private static class DrunkTalkDecorator {

        /**
         * Decorate a string to add a drunk effect to it
         * @param sentence the sentence
         * @return the drunk sentence
         */
        static String decorate(String sentence){
            StringBuilder drunkPhrase = new StringBuilder("");
            for (int i = 0; i < sentence.length(); i++) {
                char c = sentence.charAt(i);
                if(i % 2 == 0){
                    drunkPhrase.append(Character.toUpperCase(c));
                }
                drunkPhrase.append(c);
            }
            return drunkPhrase.toString();
        }
    }

    private Brain _brain;

    /**
     * Constructor
     * @param brain the master brain
     */
    public DrunkBrainState(Brain brain) {
        _brain = brain;
    }

    /**
     * Ask oxygen to the body
     */
    @Override
    public void askOxygen(double value) {
        _brain.lungs.accelerate(5+value/10);
        _brain.heart.accelerate(5+value/20);
    }

    /**
     * Ask the body to run
     */
    @Override
    public void run() {
        _brain.legs.walk();
    }

    /**
     * Notify event to the brain
     * @param event the event
     */
    @Override
    public void notifyEvent(Event event) {
        //some behavior here...
    }

    /**
     * Consume a substance
     * @param substance the substance
     */
    @Override
    public void eat(BodyResources substance) {
        //Different behavior here ...
        if(substance.getResourceAmount(ResourceType.Alcohol) > 10){
            _brain.mouth.say(DrunkTalkDecorator.decorate("On se la colle!"));
        } else {
            _brain.mouth.say(DrunkTalkDecorator.decorate("Pas assez d'alcool la Dedans... hips..."));
        }
    }

    /**
     * Make the body stress
     */
    @Override
    public void stress() {
        _brain.mouth.say(DrunkTalkDecorator.decorate("Pas besoin de stresser..."));
    }

    /**
     * Process eyes vision
     * @param event the event seen
     */
    @Override
    public void processEyesVision(Event event) {
        _brain.mouth.say(DrunkTalkDecorator.decorate("Je vois : " + event.getDescription()));
    }

    /**
     * Handle and signal its death
     */
    @Override
    public void die() {
        _brain.mouth.say(DrunkTalkDecorator.decorate("Même pas peur... argggsss..."));
    }
}
