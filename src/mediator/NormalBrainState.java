package mediator;
import bodyRessources.ChemicalRessources;
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
        _brain.getGameManager().takeAction(event);
    }

    @Override
    public void consume(ChemicalRessources substance) {
        System.out.println("Normal brain : J'envoi la substance à l'estomac ");
        _brain.stomach.digest(substance, _brain.getBrainChemical());
    }

    @Override
    public void stress() {
        // TODO: implémenter
    }

    @Override
    public void processEyesVision(Event event) {
        System.out.println("Je vois : " + event.getDescription());
    }
}
