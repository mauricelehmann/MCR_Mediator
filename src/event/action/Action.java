package event.action;

import event.effect.Effect;
import mediator.Brain;
import java.util.LinkedList;

public abstract class Action {
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
            effect.apply(bodyMediator); // FIXME: ne tient pas compte de la probabilité
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String string = "[Action]: " + name + "\n";

        if(effects == null) {
            string += "<aucun>\n";
        } else {
            for (Effect effect : effects) {
                string += effect.toString() + "\n";
            }
        }

        return string;
    }
}
