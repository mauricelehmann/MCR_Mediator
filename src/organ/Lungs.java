package organ;
import mediator.Brain;

public class Lungs extends Organ {

    public Lungs(Brain mediator){
        super(mediator);
    }

    public void pump(int amount){
        //Afficher : "je pompe"
        System.out.println("Lungs : Je pompe " + amount + " d oxygène");
        //Notifier au mediator que c'est bon
    }
}
