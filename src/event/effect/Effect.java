package event.effect;

import mediator.Brain;

public abstract class Effect {
    double probability;

    public abstract void apply(Brain bodyMediator);

    public Effect(double probability) {
        this.probability = probability;
    }

    public double getProbability() {
        return probability;
    }
}
