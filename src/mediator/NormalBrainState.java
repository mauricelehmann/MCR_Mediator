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
    public void run() {
        _brain.legs.run();
    }

    public void notifyEvent(Event event) {
        _brain.getGameManager().takeAction(event);
    }

    @Override
    public void consume(BodyResources substance) {
        System.out.println("Normal brain : J'envoi la substance à l'estomac ");
        _brain.stomach.digest(substance, _brain.getBrainResources());
    }

    @Override
    public void stress() {
        _brain.heart.accelerate(2);
    }

    @Override
    public void processEyesVision(Event event) {
        _brain.mouth.say("Je vois : " + event.getDescription());
    }

    @Override
    public void die() {
        _brain.mouth.say("aaarrrrrgggh...");
    }

    @Override
    public void askOxygen(){
        _brain.lungs.breath();
        _brain.heart.accelerate(1);
    }
}
