package mediator;

import bodyRessources.ChemicalRessources;
import event.Event;
import organ.Organ;

public class DrunkBrainState implements BrainState {

    Brain _brain;

    public DrunkBrainState(Brain brain) {
        _brain = brain;
    }


    @Override
    public void askOxygen(Organ asker, int value) {

    }

    @Override
    public void run() {
        _brain.legs.walk();
    }

    @Override
    public void notifyEvent(Event event) {
        // FIXME: maybe pull this up into brain
        _brain.gameManager.takeAction(event);
    }

    @Override
    public void consume(ChemicalRessources substance) {
        //Different behavior here ...
        if(substance.getAlcoolLevel() > 10){
            System.out.println("ON sE lA cOlle!!!!");
            _brain.stomach.digest(substance, _brain.brainChemical);
        }else{
            System.out.println("PaS asSez d'aLcooooL la DeDaaans... hips...");
        }
    }

}
