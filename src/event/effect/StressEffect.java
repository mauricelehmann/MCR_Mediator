package event.effect;

import mediator.Brain;

public class StressEffect extends Effect {
    public StressEffect(double probability) {
        super(probability);
    }

    @Override
    public void apply(Brain bodyMediator) {
        // TODO: implement this
    }

    @Override
    public String toString() {
        return "[Effet]: StressEffect, probability: " + probability;
    }
}