package mediator;

import event.Event;
import organ.Organ;

public interface BrainState {

    void askOxygen(Organ asker, int value);
    void run();
    void notifyEvent(Event event);
    void drink();

}
