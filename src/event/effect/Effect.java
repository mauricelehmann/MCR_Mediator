package event.effect;

import mediator.Brain;

public abstract class Effect {
    private double probability;

    public Effect(double probability) {
        this.probability = probability;
    }

    public abstract void apply(Brain bodyMediator);

    public double getProbability() {
        return probability;
    }
}
