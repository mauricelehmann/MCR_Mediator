package mediator;

import bodyRessources.BodyRessources;
import event.Event;
import gameManager.GameManager;
import organ.Eyes;
import organ.Legs;
import organ.Lungs;
import organ.Organ;

/**
 * Le Brain est le cerveau principal, il change d'état interne selon ses propres niveaux de ressources
 */
public class Brain implements BrainState {

    private class BrainRessources {
        int caffeinLevel;
        int alcoolLevel;
        int pschoticLevel;

        public BrainRessources(){
            caffeinLevel = 0;
            alcoolLevel = 0;
            pschoticLevel = 0;
        }
    }


    GameManager gameManager;

    private BrainRessources brainRessources;

    /**
     * States
     */
    BrainState exitedBrain;
    BrainState drunkBrain;
    BrainState weirdBrain;
    BrainState normalBrain;
    BrainState currentBrain;


    /**
     * Organs
     */
    protected Lungs lungs;
    protected Legs legs;
    public Eyes eyes;


    public Brain(GameManager gameManager) {
        this.gameManager = gameManager;

        /* States */
        drunkBrain = new DrunkBrainState(this);
        weirdBrain = new WeirdBrainState(this);
        exitedBrain = new ExcitedBrainState(this);
        normalBrain = new NormalBrainState(this);
        currentBrain = normalBrain;

        /* Adding the organs */
        this.lungs = new Lungs(this);
        this.legs = new Legs(this);
        this.eyes = new Eyes(this);

        brainRessources = new BrainRessources();

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

        if(brainRessources.caffeinLevel > 100){
            currentBrain = exitedBrain;
        }
        if(brainRessources.alcoolLevel > 100){
            currentBrain = drunkBrain;
        }
        if(brainRessources.pschoticLevel > 100){
            currentBrain = weirdBrain;
        }
    }

}
