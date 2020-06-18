package organ;

import mediator.Brain;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Thic class represents the heart
 */
public class Heart extends Organ {
    //HeartBeat per minute
    private double beat;

    public Heart(Brain brain) {
        super(brain);
        pump();
    }

    /**
     * Makes the heart accelerate
     * @param value the acceleration value
     */
    public void accelerate(double value) {

        //Cannot have a negative beat value
        if(beat + value < 0) beat = 0;
        else beat += value;
        //If the beat is too high, the player is dead
        if(beat > 100){
            notifyDisplay("Je fais une crise cardiaque!");  //TODO kler écrasé par pompe
            brain.die();
        }
        pump();
    }

    /**
     * Makes the heart pump blood
     */
    public void pump() {
        if(beat > 0) {
            notifyDisplay("Je pompe du sang");

            brain.bloodFlow();
        }
    }
}
