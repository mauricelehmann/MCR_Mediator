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

public abstract class Action {
    static private Random random = new Random(); // TODO: maybe moves this into GameManager
    private ImageIcon icon;

    // TODO: each action subclass should have an image for the action button
    private String name;
    private LinkedList<Effect> effects = new LinkedList<>();

    public Action(String name, String iconName) {
        this.name = name;
        icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(iconName)));
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public void applyEffects(Brain bodyMediator) {
        for(Effect effect : effects) {
            if(random.nextDouble() <= effect.getProbability()) {
                effect.apply(bodyMediator);
            }
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String string = "[Action]: " + name;

        return string;
    }
}
