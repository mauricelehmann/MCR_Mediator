package event.effect;

import mediator.Brain;

public class DieEffect extends Effect {
    public DieEffect(double probability) {
        super(probability);
    }

    @Override
    public void apply(Brain bodyMediator) {
        // TODO: implement this
    }

    @Override
    public String toString() {
        return "[Effet]: DieEffect, probability: " + probability;
    }
}
