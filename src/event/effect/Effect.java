package event.effect;

import mediator.Brain;

/**
 * This class represents an effect
 */
public abstract class Effect {
    private double probability;

    /**
     * Constructor
     * @param probability the probability to succeed
     */
    public Effect(double probability) {
        this.probability = probability;
    }

    /**
     * Pass this effect to the brain
     * @param brain
     */
    public abstract void apply(Brain brain);

    /**
     * Get the probability
     * @return probability the probability
     */
    public double getProbability() {
        return probability;
    }
}
