package organ;

import event.Event;
import mediator.BodyMediator;

public class Eyes extends Organ {
    public Eyes(BodyMediator mediator) {
        super(mediator);
    }

    public void see(Event event){
        System.out.println("Je vois : " + event.description);
        getMediator().notifyEvent(event);
    }

}
