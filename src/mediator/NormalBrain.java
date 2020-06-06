package mediator;
import event.Event;
import event.action.Action;
import organ.Organ;

import java.util.LinkedList;
import java.util.Scanner;

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

        // FIXME: maybe pull up this implementation into Brain

        // Choose action response to event
        System.out.println("Que faire ?");

        LinkedList<Action> actions = event.getPossibleActions();
        for(int i = 0; i < actions.size(); ++i) {
            System.out.println(i + ") " + actions.get(i));
        }

        Boolean valid = false;
        int actionPos = 0;

        while(!valid) {
            Scanner scanner = new Scanner(System.in);
            actionPos = scanner.nextInt();

            if(actionPos < 0 || actionPos > actions.size()) {
                System.out.println("Cette action n'est pas valide.");
            } else {
                valid = true;
            }
        }

        actions.get(actionPos).applyEffects(_brain);
    }

    @Override
    public void drink() {

    }
}
