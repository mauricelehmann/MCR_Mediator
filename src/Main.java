import event.Event;
import event.EventGenerator;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        /*Brain brain = new Brain();
        brain.run();

        // Création d'un effet
        RunEffect runEffect = new RunEffect(1);

        // Création d'une action
        FleeAction flee = new FleeAction();
        flee.addEffect(runEffect);

        // Création d'un évènement
        Event bearEvent = new Event("Un ours apparaît.");
        bearEvent.addAction(flee);

        // Choix de l'action
        bearEvent.getPossibleActions().getFirst().applyEffects(brain);*/

        EventGenerator eventGenerator = new EventGenerator("events.xml");
    }
}
