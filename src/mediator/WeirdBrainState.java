package mediator;

import bodyRessources.ChemicalRessources;
import event.GEvent;
import organ.Organ;

public class WeirdBrainState implements BrainState {

    Brain _brain;

    public WeirdBrainState(Brain brain) {
        _brain = brain;
    }


    @Override
    public void askOxygen(Organ asker, int value) {

    }

    @Override
    public void run() {

    }

    @Override
    public void notifyEvent(GEvent event) {
        // FIXME: maybe pull this up into brain
        _brain.gameManager.takeAction(event);
    }

    @Override
    public void consume(ChemicalRessources substance) {

    }

}
