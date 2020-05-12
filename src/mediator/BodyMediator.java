package mediator;
import organ.*;
import java.util.LinkedList;


/**
 * Abstract mediator
 * TODO : discuter de si on fait :
 *  - Deux linkedlist pour stocker les organes
 *  - OU en "dur" -> un attribut pour chaque organe ? ou une genre de container d'organe ?
 */
public abstract class BodyMediator {

    /* Ressources */
    /* TODO : classe de ressource? */
    private int oxygenLevel;

    private LinkedList<ActionOrgan> actionOrgans;
    private LinkedList<SenseOrgan>  senseOrgans;
    //TODO : faire un container special pour les oganes?
    protected Lungs lungs;

    public BodyMediator(){
        /* ... */
        actionOrgans = new LinkedList<>();
        senseOrgans =  new LinkedList<>();

        /* Adding the organs */
        this.lungs = new Lungs(this);
        actionOrgans.add(lungs);

        /* Initial state */
        oxygenLevel = 100;
    }


    /* Getter and setter of the ressources */

    public int getOxygenLevel(){
        return oxygenLevel;
    }

    private void setOxygenLevel(int val){
        oxygenLevel = val;
    }

    /* Abstract methods : behaviors have to be implemented in the concret brains */
    public abstract void askOxygen(Organ asker);

}
