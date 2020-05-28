package event.effect;

import mediator.BodyMediator;

public abstract class Effect {
    double probability;

    public abstract void apply(BodyMediator bodyMediator);

    public Effect(double probability) {
        this.probability = probability;
    }
}
