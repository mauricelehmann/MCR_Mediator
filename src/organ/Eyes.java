package organ;

import event.GEvent;
import mediator.Brain;

public class Eyes extends Organ {
    public Eyes(Brain mediator) {
        super(mediator);
    }

    public void see(GEvent event){
        System.out.println("Je vois : " + event.description);
        getMediator().notifyEvent(event);
    }

}
