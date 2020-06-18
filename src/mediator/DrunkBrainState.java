package mediator;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import event.Event;
import organ.Organ;

public class DrunkBrainState implements BrainState {

    //Transform a basic string into a drunkish string
    private static class DrunkTalkDecorator {
        public static String decorate(String sentence){
            String drunkPhrase = sentence;
            for (int i = 0; i < sentence.length(); i++) {
                char c = sentence.charAt(i);
                if(i % 2 == 0){
                    drunkPhrase += Character.toUpperCase(c);
                }
                drunkPhrase += c;
            }
            return drunkPhrase;
        }
    }



    private Brain _brain;

    public DrunkBrainState(Brain brain) {
        _brain = brain;
    }


    @Override
    public void askOxygen() {

    }

    @Override
    public void run() {
        _brain.legs.walk();
    }

    @Override
    public void notifyEvent(Event event) {
        // FIXME: maybe pull this up into brain
        _brain.getGameManager().takeAction(event);
    }

    @Override
    public void consume(BodyResources substance) {
        //Different behavior here ...
        if(substance.getResourceAmount(ResourceType.Alcohol) > 10){
            _brain.mouth.say(DrunkTalkDecorator.decorate("On se la colle!"));
            _brain.stomach.digest(substance, _brain.getBrainResources());
        } else {
            _brain.mouth.say(DrunkTalkDecorator.decorate("Pas assez d'alcool la Dedans... hips..."));
        }
    }

    @Override
    public void stress() {
        _brain.mouth.say(DrunkTalkDecorator.decorate("Pas besoin de stresser..."));
    }

    @Override
    public void processEyesVision(Event event) {
        _brain.mouth.say(DrunkTalkDecorator.decorate("Je vois : " + event.getDescription()));
    }

    @Override
    public void die() {
        _brain.mouth.say(DrunkTalkDecorator.decorate("Même pas peur... argggsss..."));
    }
}
