package event;

import event.action.Action;
import java.util.LinkedList;

public class GEvent {
    public String description;
    public LinkedList<Action> possibleActions = new LinkedList<>();

    public GEvent() {

    }

    public void addAction(Action action) {
        possibleActions.add(action);
    }

    public LinkedList<Action> getPossibleActions() {
        return possibleActions;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String string = "[Evenement]: " + description;

        return string;
    }
}
