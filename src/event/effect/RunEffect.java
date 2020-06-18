package event.effect;

import mediator.Brain;

public class RunEffect extends Effect {
    public RunEffect(double probability) {
        super(probability);
    }

    @Override
    public void apply(Brain brain) {
        brain.run();
    }

    @Override
    public String toString() {
        return "[Effet]: RunEffect, probability: " + getProbability();
    }
}
