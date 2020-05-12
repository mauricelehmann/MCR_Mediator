package organ;

import mediator.BodyMediator;

public class Lungs extends ActionOrgan {

    public Lungs(BodyMediator mediator){
        super(mediator);
    }

    public void pump(int amount){
        //Afficher : "je pompe"
        //Notifier au mediator que c'est bon
    }

}
