package mediator;

import bodyRessources.BodyRessources;
import event.Event;
import organ.Organ;

public interface BrainState {

    void askOxygen(Organ asker, int value);
    void run();
    void notifyEvent(Event event);
    void consume(BodyRessources substance);
    void stress();
    void processEyesVision(Event event);
    void die();
}
