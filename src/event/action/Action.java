package event.action;

import event.effect.Effect;
import mediator.Brain;
import java.util.LinkedList;

public abstract class Action {
    private String name;
    private LinkedList<Effect> effects = new LinkedList<>();

    public Action(String name) {
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public void applyEffects(Brain bodyMediator) {
        for(Effect effect : effects) {
            effect.apply(bodyMediator); // FIXME: ne tient pas compte de la probabilité
        }
    }
}
