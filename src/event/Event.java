package event;

import event.action.Action;
import java.util.LinkedList;

public class Event {
    //Could be interesting for events to have a limited lifetime !
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

    @Override
    public String toString() {
        String string = "[Evenement]: " + description;

        return string;
    }
}
