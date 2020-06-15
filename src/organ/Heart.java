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
    }

    public void accelerate(double multiplier) throws Exception {
        beat *= multiplier;
        pump();//Reschedule heartbeat with new beat frequency
    }

    public void pump() throws Exception {
        if(beat > 0) {
            //We need to reschedule to send pump blood at the new frequency
            paceMaker.cancel();
            paceMaker.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    mediator.bloodFlow();
                }
            }, 0, (int)(1000/beat));
        }
        else
        {
            throw new Exception("Heart is stopped !");//TODO : Make exception classes and handle them
        }
    }
}
