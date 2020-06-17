package mediator;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import event.Event;
import organ.Organ;

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
    public void askOxygen(Organ asker, int value) {
        _brain.lungs.pump(value + 10); //+10 parce qu'il super EXCITEEEE
        asker.getResources().refill(ResourceType.Oxygen, value + 10.);
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
    public void stress() {
        // TODO: stresser plus quand le brain est excité
        //TODO : Le perso pourrait mourrir si il devient stressé en étant deja excité ?
    }

    @Override
    public void processEyesVision(Event event) {
        String description = event.getDescription().toUpperCase();
        System.out.println("Je vois : " + description + " !!!");
    }

    @Override
    public void die() {
        _brain.mouth.say(ExcitedTalkDecorator.decorate("aaah!!! Je meurs !!!"));
    }
}
