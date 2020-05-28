package event;

import event.action.Action;

import java.util.LinkedList;

public class Event {
    public String description;
    public LinkedList<Action> possibleActions = new LinkedList<>();

    public Event(String description) {
        this.description = description;
    }

    public void addAction(Action action) {
        possibleActions.add(action);
    }

    public void removeAction(Action action) {
        possibleActions.remove(action);
    }

    public LinkedList<Action> getPossibleActions() {
        return possibleActions;
    }
}
