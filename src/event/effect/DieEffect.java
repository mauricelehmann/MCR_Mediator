package event.effect;

import mediator.Brain;

/**
 * This class represents an effect making the body die
 */
public class DieEffect extends Effect {

    /**
     * Constructor
     * @param probability the probability of succeed
     */
    public DieEffect(double probability) {
        super(probability);
    }

    /**
     * Pass this to the brain
     * @param brain the brain
     */
    @Override
    public void apply(Brain brain) {
        brain.die();
    }

    /**
     * The string representation
     * @return the representation
     */
    @Override
    public String toString() {
        return "[Effet]: DieEffect, probability: " + getProbability();
    }
}
