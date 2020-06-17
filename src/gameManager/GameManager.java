package gameManager;

import display.ControlPanel;
import event.Event;
import event.EventGenerator;
import event.action.Action;
import mediator.Brain;

public class GameManager {
    private final String EVENTS_FILE = "events.xml";
    private Brain brain;
    private EventGenerator eventGenerator;
    private boolean continueGame = true;
    private static boolean actionTaken = false;

    public GameManager() {
        brain = new Brain(this);
        eventGenerator = new EventGenerator(EVENTS_FILE);
    }

    public void startGame() {
        while(continueGame) {
            nextTurn();
        }
    }

    public void nextTurn() {
        // FIXME: maybe this is not clean, we probably should not access eyes directly
        if(brain != null) {
            brain.eyes.see(eventGenerator.generate());
        }

        if(brain != null) {
            brain.updateChemicalLevel();
        }
    }

    public void takeAction(Event event){
        ControlPanel.handleEvent(event);
        try{
            while(!actionTaken){
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        actionTaken = false;
    }

    public void applyAction(Action action) {
        if(brain != null) {
            action.applyEffects(brain);
            actionTaken = true;
        }
    }

    public void playerDies() {
        continueGame = false;
        brain = null;
    }
}
