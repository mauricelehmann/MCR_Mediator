package event.action;

import event.effect.Effect;
import mediator.Brain;
import java.util.LinkedList;
import java.util.Random;

public abstract class Action {
    static private Random random = new Random(); // TODO: maybe moves this into GameManager

    // TODO: each action subclass should have an image for the action button
    private String name;
    private LinkedList<Effect> effects = new LinkedList<>();

    public Action(String name) {
        this.name = name;
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
