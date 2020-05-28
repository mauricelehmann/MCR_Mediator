package event.effect;

import mediator.BodyMediator;

public class RunEffect extends Effect {
    public RunEffect(double probability) {
        super(probability);
    }

    @Override
    public void apply(BodyMediator bodyMediator) {
        bodyMediator.run();
    }
}
