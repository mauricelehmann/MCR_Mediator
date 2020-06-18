package gameManager;

import display.ControlPanel;
import event.Event;
import event.EventGenerator;
import event.action.Action;
import mediator.Brain;

import java.util.Timer;
import java.util.TimerTask;
import java.util.TooManyListenersException;

public class GameManager {
    private final String EVENTS_FILE = "events.xml";
    private Brain brain;
    private EventGenerator eventGenerator;
    private Event currentEvent;
    private static boolean actionTaken = false;

    public GameManager() {
        brain = new Brain(this);
        eventGenerator = new EventGenerator(EVENTS_FILE);
    }

    public void startGame() {
        Timer eventScheduler = new Timer();
        Timer bodyUpdateScheduler = new Timer();

        brain.start();

        eventScheduler.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                currentEvent = eventGenerator.generate();
                brain.look(currentEvent);
            }
        }, 5000, 5000);

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
