import event.Event;
import event.action.FleeAction;
import event.effect.RunEffect;
import mediator.NormalBrain;

public class Main {

    public static void main(String[] args) {
        NormalBrain brain = new NormalBrain();

        // Création d'un effet
        RunEffect runEffect = new RunEffect(1);

        // Création d'une action
        FleeAction flee = new FleeAction();
        flee.addEffect(runEffect);

        // Création d'un évènement
        Event bearEvent = new Event("Un ours apparaît.");
        bearEvent.addAction(flee);

        // Choix de l'action
        bearEvent.getPossibleActions().getFirst().applyEffects(brain);

        // brain.eyes.see(new Event());
    }
}
