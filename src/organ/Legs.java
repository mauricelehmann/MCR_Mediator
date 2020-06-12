package organ;

import mediator.Brain;


public class Legs extends Organ {
    public Legs(Brain mediator) {
        super(mediator);
    }

    public void run() throws InterruptedException {

        /*
        //check si le niveau d'oxygene est suffisant
        while( getRessources().getOxygenLevel() < 20){
            System.out.println("Jambes : Pas assez d'oxygène!");
            getMediator().askOxygen();
            Thread.sleep(2000);
        }
        System.out.println("Je cours!");
        //demander au mediator réduire le niveau d'oxygène
        getRessources().setOxygenLevel(getRessources().getOxygenLevel() - 10);
        */
        //TODO : Replace obsolete code
    }
}
