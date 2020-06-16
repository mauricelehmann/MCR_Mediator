package event.effect;

import bodyRessources.ChemicalRessources;
import mediator.Brain;

public class EatEffect extends Effect {
    private ChemicalRessources substance;

    public EatEffect(double probability, ChemicalRessources substance) {
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
