package organ;

import mediator.BodyMediator;

import java.util.Timer;

public class Heart extends Organ {
    //HeartBeat per minute
    private double beat;
    Timer paceMaker;

    public Heart(BodyMediator mediator) {
        super(mediator);
    }

    public void setBeat(double heartBeat) {
        beat = heartBeat;
    }

    public void beat() {
        while(beat > 0) {
            paceMaker.scheduleAtFixedRate(mediator.distributeResources(), 0, 1000);
        }
    }
}
