package mediator;

import bodyRessources.ChemicalRessources;
import event.Event;
import organ.Organ;

public interface BrainState {

    void askOxygen(Organ asker, int value);
    void run();
    void notifyEvent(Event event);
    void consume(ChemicalRessources substance);
    void stress();
    void processEyesVision(Event event);
    void die();
}
