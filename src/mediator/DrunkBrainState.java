package mediator;

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

    }

    @Override
    public void drink() {

    }
}
