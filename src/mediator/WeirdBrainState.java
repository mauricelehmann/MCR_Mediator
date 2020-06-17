package mediator;

import bodyRessources.BodyResources;
import event.Event;
import organ.Organ;

public class WeirdBrainState implements BrainState {
    private Brain _brain;

    public WeirdBrainState(Brain brain) {
        _brain = brain;
    }


    @Override
    public void askOxygen(Organ asker, int value) {
        // TODO: implémenter
    }

    @Override
    public void run() {
        // TODO: implémenter
    }

    @Override
    public void notifyEvent(Event event) {
        // FIXME: maybe pull this up into brain
        _brain.getGameManager().takeAction(event);
    }

    @Override
    public void consume(BodyResources substance) {
        // TODO: implémenter
    }

    @Override
    public void stress() {
        // TODO: implémenter
    }

    @Override
    public void processEyesVision(Event event) {
        if(event.getHallucination() != null && !event.getHallucination().isEmpty()) {
            System.out.println("Je vois : " + event.getHallucination());
        } else {
            System.out.println("Je vois : " + event.getDescription());
        }
    }

    @Override
    public void die() {
        //TODO
    }
}
