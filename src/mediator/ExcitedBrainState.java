package mediator;

import bodyRessources.BodyResources;
import event.Event;

public class ExcitedBrainState implements BrainState {

    private static class ExcitedTalkDecorator {
        public static String decorate(String sentence) {
            return sentence.toUpperCase();
        }
    }

    private Brain _brain;

    public ExcitedBrainState(Brain brain) {
        _brain = brain;
    }

    @Override
    public void askOxygen() {
        for (int i = 0; i < 3; i++) {
            _brain.lungs.breath();
        }
    }

    @Override
    public void run() {
        _brain.mouth.say(ExcitedTalkDecorator.decorate("En avant!"));
        for (int i = 0; i < 3; i++) {
            _brain.legs.run();
        }
    }

    @Override
    public void notifyEvent(Event event) {
        // FIXME: maybe pull this up into brain
        _brain.getGameManager().takeAction(event);
    }

    @Override
    public void consume(BodyResources substance) {
        _brain.mouth.say(ExcitedTalkDecorator.decorate("Miam miam"));
        _brain.stomach.digest(substance, _brain.getBrainResources());
    }

    @Override
    public void stress(){
       _brain.heart.accelerate(10);
    }

    @Override
    public void processEyesVision(Event event) {
        String description = event.getDescription().toUpperCase();
        _brain.mouth.say(ExcitedTalkDecorator.decorate("Je vois : " + description + " !!!"));
    }

    @Override
    public void die() {
        _brain.mouth.say(ExcitedTalkDecorator.decorate("aaah!!! Je meurs !!!"));
    }
}
