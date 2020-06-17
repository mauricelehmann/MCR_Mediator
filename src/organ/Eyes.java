package organ;

import event.Event;
import mediator.Brain;

public class Eyes extends Organ {
    public Eyes(Brain mediator) {
        super(mediator);
    }

    public void see(Event event){
        System.out.println("Je vois : " + event.description);
        getMediator().notifyEvent(event);
    }

}
