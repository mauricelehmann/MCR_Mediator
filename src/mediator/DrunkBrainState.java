package mediator;

import bodyRessources.ChemicalRessources;
import event.Event;
import organ.Organ;

public class DrunkBrainState implements BrainState {


    //Transform a basic string into a drunkish string
    private static class DrunkTalkDecorator {
        public static String decorate(String phrase){
            String drunkPhrase = phrase;
            for (int i = 0; i < phrase.length(); i++) {
                char c = phrase.charAt(i);
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
    public void askOxygen(Organ asker, int value) {
        // TODO: implémenter
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
    public void consume(ChemicalRessources substance) {
        //Different behavior here ...
        if(substance.getAlcoolLevel() > 10){
            _brain.mouth.say(DrunkTalkDecorator.decorate("On se la colle!"));
            _brain.stomach.digest(substance, _brain.getBrainChemical());
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
