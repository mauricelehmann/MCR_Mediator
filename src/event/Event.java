package event;

import event.action.Action;
import java.util.LinkedList;

public class Event {
    public String description;
    public LinkedList<Action> possibleActions = new LinkedList<>();

    public Event() {

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
        String string = "[Evenement]: " + description + "\n\n";

        if(possibleActions == null) {
            string += "<aucun>\n";
        } else {
            for (Action action : possibleActions) {
                string += (action != null ? action.toString() : "") + "\n";
            }
        }

        return string;
    }
}
