package mediator;

import bodyRessources.BodyResources;
import event.Event;
import organ.Organ;

public interface BrainState {

    void askOxygen() ;
    void run() ;
    void notifyEvent(Event event);
    void consume(BodyResources substance);
    void stress() ;
    void processEyesVision(Event event);
    void die();
}
