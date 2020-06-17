package organ;

import mediator.Brain;

import java.util.Timer;
import java.util.TimerTask;

public class Heart extends Organ {
    //HeartBeat per minute
    private double beat;

    Timer paceMaker;

    public Heart(Brain mediator) {
        super(mediator);
        pump();
    }

    public void accelerate(double value) {

        //Cannot have a negative beat value
        if(beat + value < 0) beat = 0;
        else beat += value;
        //If the beat is too high, the player is dead
        if(beat > 100){
            mediator.die();
        }
        pump();//Reschedule heartbeat with new beat frequency
    }

    public void pump() {
        if(beat > 0) {
            //We need to reschedule to send pump blood at the new frequency
            paceMaker = new Timer();
            paceMaker.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    mediator.bloodFlow();
                }
            }, 0, (int)(1000/beat));
        }
    }
}
