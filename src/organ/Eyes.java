package organ;

import event.Event;
import mediator.Brain;

public class Eyes extends Organ {
    public Eyes(Brain mediator) {
        super(mediator);
    }

    public void see(Event event){
        notifyDisplay("Les yeux : nouvel événement en vue");
        mediator.processEyesVision(event);
        mediator.notifyEvent(event);
    }

}
