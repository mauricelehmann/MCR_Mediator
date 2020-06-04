package event.effect;

import mediator.Brain;

public class EatEffect extends Effect {
    public EatEffect(double probability) {
        super(probability);
    }

    @Override
    public void apply(Brain bodyMediator) {
        // TODO: implement this
    }

    @Override
    public String toString() {
        return "[Effet]: EatEffect, probability: " + probability;
    }
}
