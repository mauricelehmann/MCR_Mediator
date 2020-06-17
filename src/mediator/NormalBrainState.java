package mediator;
import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
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
        asker.getResources().refill(ResourceType.Oxygen, (double)value);
    }

    @Override
    public void run() {
        _brain.legs.run();
    }

    public void notifyEvent(Event event) {
        // FIXME: maybe pull this up into brain
        _brain.getGameManager().takeAction(event);
    }

    @Override
    public void consume(BodyResources substance) {
        System.out.println("Normal brain : J'envoi la substance à l'estomac ");
        _brain.stomach.digest(substance, _brain.getBrainResources());
    }

    @Override
    public void stress() {
        // TODO: implémenter
    }

    @Override
    public void processEyesVision(Event event) {
        System.out.println("Je vois : " + event.getDescription());
    }

    @Override
    public void die() {
        _brain.mouth.say("aaarrrrrgggh...");
    }
}
