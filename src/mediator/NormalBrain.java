package mediator;
import event.Event;
import organ.Organ;

public class NormalBrain implements BrainState {
    private Brain _brain;

    public NormalBrain(Brain brain){
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
