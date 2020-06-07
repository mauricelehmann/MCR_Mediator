package mediator;
import event.Event;
import organ.Organ;

public class NormalBrainState implements BrainState {
    private Brain _brain;

    public NormalBrainState(Brain brain){
        _brain = brain;
    }

    @Override
    public void askOxygen(Organ asker, int value) {
        _brain.lungs.pump(value);
        asker.addOxygene(value);
    }

    @Override
    public void run() {
        _brain.legs.run();
    }

    public void notifyEvent(Event event) {
        // FIXME: maybe pull this up into brain
        _brain.gameManager.takeAction(event);
    }

    @Override
    public void drink() {

    }
}
