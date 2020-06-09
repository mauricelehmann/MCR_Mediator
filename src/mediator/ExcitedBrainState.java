package mediator;

import bodyRessources.ChemicalRessources;
import event.Event;
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
    public void notifyEvent(Event event) {

    }

    @Override
    public void consume(ChemicalRessources substance) {

    }


}
