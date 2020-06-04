package event;

import java.util.ArrayList;
import java.util.HashMap;
import event.action.*;
import event.effect.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EventsParserHandler extends DefaultHandler {
    private Event currentEvent = null;
    private Action currentAction = null;
    private Effect currentEffect = null;

    private String elementValue;
    private ArrayList<Event> events = new ArrayList<>();
    private HashMap<String, Action> actions = new HashMap();

    public void startDocument() throws SAXException {
        System.out.println("Loading events...");
    }

    public void endDocument() throws SAXException {
        System.out.println("events loaded.");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException  {
        switch (qName) {
            case "event":
                currentEvent = new Event();
                break;
            case "action":
                if(attributes != null) {
                    String actionId = attributes.getValue(0);
                    String actionType = attributes.getValue(1);

                    switch(actionType) {
                        case "eat":
                            currentAction = new EatAction(actionId);
                            break;
                        case "fight":
                            currentAction = new FightAction(actionId);
                            break;
                        case "flee":
                            currentAction = new FleeAction(actionId);
                            break;
                        case "flirt":
                            currentAction = new FlirtAction(actionId);
                            break;
                        case "ignore":
                            currentAction = new IgnoreAction(actionId);
                            break;
                        case "take":
                            currentAction = new TakeAction(actionId);
                            break;
                    }
                }
                break;
            case "effect":
                if(attributes != null) {
                    String effectType = attributes.getValue(0);
                    double probability = Double.parseDouble(attributes.getValue(1));

                    switch(effectType) {
                        case "run":
                            currentEffect = new RunEffect(probability);
                            break;
                        case "die":
                            currentEffect = new DieEffect(probability);
                            break;
                        case "stress":
                            currentEffect = new StressEffect(probability);
                            break;
                        case "eat":
                            currentEffect = new EatEffect(probability);
                            break;
                    }
                }
                break;
            case "eventAction":
                if(attributes != null) {
                    String actionName = attributes.getValue(0);
                    currentEvent.addAction(actions.get(actionName));
                }
                break;
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "event":
                events.add(currentEvent);
                break;
            case "description":
                currentEvent.setDescription(elementValue);
                break;
            case "action":
                actions.put(currentAction.getName(), currentAction);
                break;
            case "effect":
                currentAction.addEffect(currentEffect);
                break;
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
