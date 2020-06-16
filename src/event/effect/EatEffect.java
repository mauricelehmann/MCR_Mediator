package event.effect;

import bodyRessources.BodyRessources;
import mediator.Brain;

public class EatEffect extends Effect {
    private BodyRessources substance;

    public EatEffect(double probability, BodyRessources substance) {
        super(probability);
        this.substance = substance;
    }

    @Override
    public void apply(Brain bodyMediator) {
        bodyMediator.consume(substance);
    }

    @Override
    public String toString() {
        return "[Effet]: EatEffect, probability: " + getProbability();
    }
}
