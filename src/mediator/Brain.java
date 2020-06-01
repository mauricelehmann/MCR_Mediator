package mediator;

import bodyRessources.BodyRessources;
import event.Event;
import organ.Eyes;
import organ.Legs;
import organ.Lungs;
import organ.Organ;


/**
 * Le Brain est le cerveau principal, il change d'état interne selon ses propres niveaux de ressources
 */
public class Brain implements BrainState {

    BrainState exitedBrain;
    BrainState normalBrain;
    BrainState currentBrain;

    protected Lungs lungs;
    protected Legs legs;
    public Eyes eyes;


    //TODO: move this to a bodyRessource ?
    BodyRessources ressources;

    public Brain(){

        /* States */
        exitedBrain = new ExcitedBrain(this);
        normalBrain = new NormalBrain(this);
        currentBrain = normalBrain;

        /* Adding the organs */
        this.lungs = new Lungs(this);
        this.legs = new Legs(this);
        this.eyes = new Eyes(this);

        ressources = new BodyRessources(100);

    }

    @Override
    public void askOxygen(Organ asker, int value) {
        currentBrain.askOxygen(asker, value);
    }

    @Override
    public void run() {
        currentBrain.run();
    }

    @Override
    public void notifyEvent(Event event) {
        currentBrain.notifyEvent(event);
    }

    /* EXEMPLE DE CHANGEMENT DE STATE */
    public void drink(){
        //do something with the mouth

        //do something with the stomac

        if(ressources.getCaffeinLevel() > 100){
            currentBrain = exitedBrain;
        }
    }
}
