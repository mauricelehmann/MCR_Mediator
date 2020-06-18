package gameManager;

import display.ControlPanel;
import display.OrganPanel;
import display.StatePanel;
import event.Event;
import event.EventGenerator;
import event.action.Action;
import mediator.Brain;

/**
 * Represents a game manager.
 * the game manager has the responsability to
 * manager the game and its interaction with the player
 */
public class GameManager {
    private final String EVENTS_FILE = "events.xml";
    private Brain brain;
    private EventGenerator eventGenerator;
    private boolean continueGame = true;
    private static boolean actionTaken = false;

    /**
     * Constructor
     */
    public GameManager() {
        brain = new Brain(this);
        eventGenerator = new EventGenerator(EVENTS_FILE);

        new ControlPanel(this);
        new StatePanel(this);
        new OrganPanel(this, this.brain.getOrgans());
    }

    /**
     * Start the game
     */
    public void startGame() {
        while(continueGame) {
            nextTurn();
        }
    }

    /**
     * Next turn makes a new event happen
     * and update the levels of resources
     */
    public void nextTurn() {
        // FIXME: maybe this is not clean, we probably should not access eyes directly
        if(brain != null) {
            brain.eyes.see(eventGenerator.generate());
        }

        if(brain != null) {
            brain.updateChemicalLevel();
        }
    }

    /**
     * Prompt the user for an action
     * in response to an event
     * @param event the event
     */
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

    /**
     * Apply the action's effects chosen by the user
     * @param action the action
     */
    public void applyAction(Action action) {
        if(brain != null) {
            action.applyEffects(brain);
            actionTaken = true;
        }
    }

    /**
     * Makes the game end
     */
    public void playerDies() {
        continueGame = false;
        brain = null;
        ControlPanel.deathScreen();
    }
}
