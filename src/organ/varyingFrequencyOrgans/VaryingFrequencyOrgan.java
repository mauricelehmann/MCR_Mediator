package organ.varyingFrequencyOrgans;

import mediator.Brain;
import organ.Organ;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.max;

public abstract class VaryingFrequencyOrgan extends Organ {

    protected double minFrequency;
    protected double frequency;
    protected Timer paceMaker;
    protected Runnable task;

    public VaryingFrequencyOrgan(Brain mediator) {
        super(mediator);
    }

    /**
     * Modify the frequency of the organ's task
     * @param multiplier frequency multiplier
     */
    public void accelerate(double multiplier) {

        frequency = max(multiplier*frequency, minFrequency);//Cannot go under a certain frequency
        reSchedule();//Reschedule heartbeat with new beat frequency
    }

    public void reSchedule() {
        if(frequency > 0) {
            if(paceMaker != null)
            {
                paceMaker.cancel();
                paceMaker.purge();
            }
            activityFactor = frequency*2;
            Timer newPaceMaker = new Timer();
            //We need to reschedule to send pump blood at the new frequency
            newPaceMaker.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    task.run();
                }
            }, 0, (int) (1000 / frequency));
            paceMaker = newPaceMaker;
        }
    }
}
