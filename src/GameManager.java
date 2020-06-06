import event.EventGenerator;
import mediator.Brain;

public class GameManager {
    private final String EVENTS_FILE = "events.xml";
    private Brain brain;
    private EventGenerator eventGenerator;

    public GameManager() {
        brain = new Brain();
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
    }
}
