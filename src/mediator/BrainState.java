package mediator;

import bodyRessources.ChemicalRessources;
import event.GEvent;
import organ.Organ;

public interface BrainState {

    void askOxygen(Organ asker, int value);
    void run();
    void notifyEvent(GEvent event);
    void consume(ChemicalRessources substance);

}
