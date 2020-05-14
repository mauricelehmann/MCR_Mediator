package organ;

import mediator.BodyMediator;

public class Lungs extends Organ {

    public Lungs(BodyMediator mediator){
        super(mediator);
    }

    public void pump(int amount){
        //Afficher : "je pompe"
        System.out.println("Je pompe " + amount + " d oxygène");
        //Notifier au mediator que c'est bon
    }

}
