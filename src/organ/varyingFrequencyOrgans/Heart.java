package organ;

import mediator.Brain;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.max;

public class Heart extends Organ {
    //HeartBeat per minute
    private double beat;

    Timer paceMaker;

    public Heart(Brain mediator) {
        super(mediator);
        beat = 1.2; //1 beat per second is slow, but still normal !
    }

    public void accelerate(double multiplier) throws Exception {
        beat = max(multiplier*beat, 1);//Cannot go under a certain frequency
        pump();//Reschedule heartbeat with new beat frequency
    }

    public void pump() {
        if(beat > 0) {
            activityFactor = beat*2;
            //We need to reschedule to send pump blood at the new frequency
            paceMaker.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    mediator.bloodFlow();
                }
            }, 0, (int)(1000/beat));
        }
    }

    @Override
    public double getSize() {
        return 50;
    }
}
