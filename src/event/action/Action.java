package event.action;

import event.effect.Effect;
import mediator.Brain;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

/**
 * This class represents a possible action with effects
 */
public abstract class Action {
    static private Random random = new Random(); // TODO: maybe moves this into GameManager
    private ImageIcon icon;
    private String name;
    private LinkedList<Effect> effects = new LinkedList<>();

    /**
     * Constructor
     * @param name the name
     * @param iconFilename the name of the icon file
     */
    public Action(String name, String iconFilename) {
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
        String string = "[Action]: " + name;

        return string;
    }
}
