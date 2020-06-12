package organ;

import mediator.BloodSystem;
import mediator.Brain;

public class Lungs extends Organ {
    private BloodSystem bloodSystem;
    private final int lungCapacity = 10;

    public Lungs(Brain mediator){
        super(mediator);
    }

    public void breathe(){
        bloodSystem.addOxygen(lungCapacity);
    }

}
