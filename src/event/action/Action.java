package event.action;

import event.effect.Effect;
import mediator.BodyMediator;
import java.util.LinkedList;

public abstract class Action {
    private String name;
    private LinkedList<Effect> effects = new LinkedList<>();

    public Action(String name) {
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public void applyEffects(BodyMediator bodyMediator) {
        for(Effect effect : effects) {
            effect.apply(bodyMediator); // FIXME: ne tient pas compte de la probabilité
        }
    }
}
