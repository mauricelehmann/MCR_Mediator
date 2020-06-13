package mediator;

import bodyRessources.ChemicalRessources;
import event.Event;
import organ.Organ;

public class ExcitedBrainState implements BrainState {
    private Brain _brain;

    public ExcitedBrainState(Brain brain) {
        _brain = brain;
    }

    @Override
    public void askOxygen(Organ asker, int value) {
        _brain.lungs.pump(value + 10); //+10 parce qu'il super EXCITEEEE
        asker.addOxygene(value + 10);
    }

    @Override
    public void run() {
        // TODO: implémenter
    }

    @Override
    public void notifyEvent(Event event) {
        // FIXME: maybe pull this up into brain
        _brain.getGameManager().takeAction(event);
    }

    @Override
    public void consume(ChemicalRessources substance) {
        // TODO: implémenter
    }

    @Override
    public void stress() {
        // TODO: stresser plus quand le brain est excité
    }

    @Override
    public void processEyesVision(Event event) {
        String description = event.getDescription().toUpperCase();
        System.out.println("Je vois : " + description + " !!!");
    }
}
