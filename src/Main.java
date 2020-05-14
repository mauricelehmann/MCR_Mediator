import event.Event;
import mediator.NormalBrain;

public class Main {

    public static void main(String[] args) {

        NormalBrain brain = new NormalBrain();

        brain.eyes.see(new Event());


    }
}
