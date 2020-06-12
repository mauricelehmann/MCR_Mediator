package gameManager;

import display.ControlPanel;
import event.GEvent;
import event.EventGenerator;
import event.action.Action;
import mediator.Brain;

import java.util.LinkedList;
import java.util.Scanner;

public class GameManager {
    private final String EVENTS_FILE = "events.xml";
    private Brain brain;
    private EventGenerator eventGenerator;
    private static boolean actionTaken = false;

    public GameManager() {
        brain = new Brain(this);
        eventGenerator = new EventGenerator(EVENTS_FILE);
    }

    public void startGame() {
        Boolean continueGame = true;

        while(continueGame) {
            nextTurn();
        }
    }

    public void nextTurn() {
        // FIXME: maybe this is not clean, we probably should not access eyes directly
        brain.eyes.see(eventGenerator.generate());
        brain.updtateChemicalLevel();
    }

    public void takeAction(GEvent event){
        ControlPanel.handleEvent(event);
        try{
            while(!actionTaken){
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        actionTaken = false;

//        // Choose action response to event
//        System.out.println("Que faire ?");
//
//        LinkedList<Action> actions = event.getPossibleActions();
//        for(int i = 0; i < actions.size(); ++i) {
//            System.out.println(i + ") " + actions.get(i));
//        }
//
//        Boolean valid = false;
//        int actionPos = 0;
//
//        while(!valid) {
//            Scanner scanner = new Scanner(System.in);
//            actionPos = scanner.nextInt();
//
//            if(actionPos < 0 || actionPos >= actions.size()) {
//                System.out.println("Cette action n'est pas valide.");
//            } else {
//                valid = true;
//            }
//        }
//
//        actions.get(actionPos).applyEffects(brain);

    }

    public void applyAction(Action action){
        action.applyEffects(brain);
        actionTaken = true;
    }
}
