package organ;

import bodyRessources.ResourceType;
import mediator.Brain;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.max;

public class Lungs extends Organ {
    private final int lungCapacity = 10;
    private double breathFrequency;

    Timer breathPaceMaker;

    public Lungs(Brain mediator){
        super(mediator);
        breathFrequency = 0.2;
        breathPaceMaker = new Timer();
        breathe();
    }

    public void accelerate(double multiplier) {
        breathFrequency = max(multiplier*breathFrequency, 1);
        breathe();
    }

    public void breathe() {
        if(breathFrequency > 0) {
            activityFactor = breathFrequency*4;
            //We need to reschedule to send pump blood at the new frequency
            breathPaceMaker.cancel();
            breathPaceMaker.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    mediator.refillBlood(ResourceType.Oxygen, lungCapacity);
                }
            }, 0, (int)(1000/breathFrequency));
        }
    }
}
