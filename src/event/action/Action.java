package event.action;

import event.effect.Effect;
import mediator.Brain;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

/**
 * This class represents a possible action with effects
 */
public abstract class Action {
    static private final Random random = new Random();
    private final ImageIcon icon;
    private final String name;
    private final LinkedList<Effect> effects = new LinkedList<>();

    /**
     * Constructor
     * @param name the name
     * @param iconFilename the name of the icon file
     */
     Action(String name, String iconFilename) {
        this.name = name;
        icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(iconFilename)));
     }

    /**
     * Get the action icon
     * @return the icon
     */
    public ImageIcon getIcon() {
        return icon;
    }

    /**
     * Add an effect to the action
     * @param effect the effect
     */
    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    /**
     * Apply all effects of the action, their probability of success is taken in account
     * @param brain the brain
     */
    public void applyEffects(Brain brain) {
        for(Effect effect : effects) {
            if(random.nextDouble() <= effect.getProbability()) {
                effect.apply(brain);
            }
        }
    }

    /**
     * Get the name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get a string representation
     * @return the string
     */
    @Override
    public String toString() {
        return "[Action]: " + name;
    }
}
