package mediator;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import event.Event;
import organ.Organ;

public class WeirdBrainState implements BrainState {
    private Brain _brain;

    public WeirdBrainState(Brain brain) {
        _brain = brain;
    }

    @Override
    public void run() {
        _brain.heart.accelerate(10);
        _brain.legs.run();
    }

    @Override
    public void notifyEvent(Event event) {
        _brain.getGameManager().takeAction(event);
    }

    @Override
    public void consume(BodyResources substance) {
        substance.consume(ResourceType.Alcohol, 10.0);
        substance.consume(ResourceType.Caffein, 10.0);
        substance.consume(ResourceType.Psychedelic, -5.0);

        _brain.stomach.digest(substance, _brain.getBrainResources());
    }

    @Override
    public void stress() {
        _brain.heart.accelerate(-10);
    }

    @Override
    public void processEyesVision(Event event) {
        if(event.getHallucination() != null && !event.getHallucination().isEmpty()) {
            _brain.mouth.say("Je vois : " + event.getHallucination());
        } else {
            _brain.mouth.say("Je vois : " + event.getDescription());
        }
    }

    @Override
    public void askOxygen() {
        _brain.lungs.breath();
        _brain.heart.accelerate(-1);
    }

    @Override
    public void die() {
         this.processEyesVision(new Event("un halo de lumière..."));
        _brain.mouth.say("J'arrive mes amis!");
    }
}
