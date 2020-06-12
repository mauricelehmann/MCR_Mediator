package mediator;

import bodyRessources.ChemicalRessources;
import event.GEvent;
import organ.Organ;

public class ExcitedBrainState implements BrainState {

    Brain _brain;

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
