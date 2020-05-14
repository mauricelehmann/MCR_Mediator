package mediator;
import event.Event;

public class NormalBrain extends BodyMediator {
    public void notifyEvent(Event event){
        if(event.type.equals("DANGER")){
            System.out.println("Cerveau : Demande aux jambes de courir");
            legs.run();
        }
    }
}
