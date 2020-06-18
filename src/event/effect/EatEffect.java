package event.effect;

import bodyRessources.BodyResources;
import mediator.Brain;

/**
 * This class represents an effect of eating
 */
public class EatEffect extends Effect {
    private BodyResources substance;

    /**
     * Constructor
     * @param probability the probability to succeed
     * @param substance the substance to consume
     */
    public EatEffect(double probability, BodyResources substance) {
        super(probability);
        this.substance = substance;
    }

    /**
     * Pass this effect to the brain
     * @param brain the brain
     */
    @Override
    public void apply(Brain brain) {
        brain.consume(substance);
    }

    /**
     * Get a string representation
     * @return the string
     */
    @Override
    public String toString() {
        return "[Effet]: EatEffect, probability: " + getProbability();
    }
}
