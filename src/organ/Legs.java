package organ;

import mediator.Brain;


public class Legs extends Organ {
    public Legs(Brain mediator) {
        super(mediator);
    }

    public void walk(){

    }

    public void run(){
        int oxygenLevel = getRessources().getOxygenLevel();
        //check si le niveau d'oxygene est suffisant
        if( oxygenLevel < 20){
            System.out.println("Jambes : Pas assez d'oxygène!");
            getMediator().askOxygen(this, 20);
        }
        System.out.println("Jambes : Je cours!");
        //demander au mediator réduire le niveau d'oxygène
        getRessources().setOxygenLevel(oxygenLevel - 10);
    }
}
