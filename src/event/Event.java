package event;

import event.action.Action;
import java.util.LinkedList;

public class Event {
    public String description;
    public String hallucination;
    public LinkedList<Action> possibleActions = new LinkedList<>();

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

    public void setHallucination(String hallucination) { this.hallucination = hallucination; }

    public String getHallucination() {
        return hallucination;
    }

    public Event(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        String string = "[Evenement]: " + description;

        return string;
    }
}
