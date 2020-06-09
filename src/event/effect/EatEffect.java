package event.effect;

import bodyRessources.ChemicalRessources;
import mediator.Brain;

public class EatEffect extends Effect {
    private ChemicalRessources _substance;

    public EatEffect(double probability, ChemicalRessources substance) {
        super(probability);
        _substance = substance;
    }

    @Override
    public void apply(Brain bodyMediator) {
        // TODO: faire qu'un evenement ou on mange qqch , il y a um ChemicalRessources assosicé
        bodyMediator.consume(_substance);
    }

    @Override
    public String toString() {
        return "[Effet]: EatEffect, probability: " + probability;
    }
}
