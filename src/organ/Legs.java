package organ;

import mediator.BodyMediator;
import organ.ActionOrgan;

public class Legs extends ActionOrgan {
    public Legs(BodyMediator mediator) {
        super(mediator);
    }

    public boolean run(){
        //check si le niveau d'oxygene est suffisant

        //si pas assez, return false

        //sinon

        //dire qu'on cours au mediator

        //demander au mediator réduire le niveau d'oxygène

        return true;
    }
}
