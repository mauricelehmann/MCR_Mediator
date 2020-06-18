package mediator;

import bodyRessources.BodyResources;
import event.Event;

public interface BrainState {

    void askOxygen(double value);
    void run();
    void notifyEvent(Event event);
    void eat(BodyResources substance);
    void stress();
    void processEyesVision(Event event);
    void die();
}
