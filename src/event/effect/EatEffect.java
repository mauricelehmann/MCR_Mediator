package event.effect;

import bodyRessources.BodyResources;
import mediator.Brain;

public class EatEffect extends Effect {
    private BodyResources substance;

    public EatEffect(double probability, BodyResources substance) {
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
