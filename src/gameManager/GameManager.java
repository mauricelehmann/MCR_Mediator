package gameManager;

import display.ControlPanel;
import display.OrganPanel;
import display.StatePanel;
import event.Event;
import event.EventGenerator;
import event.action.Action;
import mediator.Brain;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a game manager.
 * the game manager has the responsability to
 * manager the game and its interaction with the player
 */
public class GameManager {
    private Brain brain;
    private EventGenerator eventGenerator;
    private Timer eventScheduler = new Timer();
    private Timer relaxer = new Timer();

    private Event currentEvent;
    private static boolean actionTaken = false;

    /**
     * Constructor
     */
    public GameManager(String eventfilename) {
        brain = new Brain(this);
        eventGenerator = new EventGenerator(eventfilename);

        new ControlPanel(this);
        new StatePanel(this);
        new OrganPanel(this, this.brain.getOrgans());
    }

    /**
     * Start the game
     */
    public void startGame() {

        brain.start();

        eventScheduler.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                currentEvent = eventGenerator.generate();
                brain.look(currentEvent);
            }
        }, 5000, 5000);

        relaxer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                brain.calmDown();
            }
        }, 10000, 10000);
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
        eventScheduler.cancel();
        eventScheduler.purge();
        brain = null;
        ControlPanel.deathScreen();
        System.exit(0);
    }
}
