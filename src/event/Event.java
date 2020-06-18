package event;

import event.action.Action;
import java.util.LinkedList;

/**
 * This class represents en event
 */
public class Event {
    private String description;
    private String hallucination;
    private LinkedList<Action> possibleActions = new LinkedList<>();

    /**
     * Add a response action to an event
     * @param action the action to add
     */
    public void addAction(Action action) {
        possibleActions.add(action);
    }

    /**
     * Get the possible actions
     * @return the actions
     */
    public LinkedList<Action> getPossibleActions() {
        return possibleActions;
    }

    /**
     * Set the description
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set an alternative description that can be shown has hallucination
     * @param hallucination the hallucination description
     */
    public void setHallucination(String hallucination) { this.hallucination = hallucination; }

    /**
     * Get the hallucination
     * @return the hallucination
     */
    public String getHallucination() {
        return hallucination;
    }

    /**
     * Constructor
     * @param description the description
     */
    public Event(String description){
        this.description = description;
    }

    /**
     * Get a string representation
     * @return the string
     */
    @Override
    public String toString() {
        return "[Evenement]: " + description;
    }
}
