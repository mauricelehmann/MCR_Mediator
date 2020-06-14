package mediator;

import bodyRessources.ChemicalRessources;
import event.Event;
import gameManager.GameManager;
import organ.*;

/**
 * Le Brain est le cerveau principal, il change d'état interne selon ses propres niveaux de ressources
 */
public class Brain implements BrainState {
    private GameManager gameManager;
    private ChemicalRessources brainChemical;

    /**
     * States
     */
    private BrainState exitedBrain;
    private BrainState drunkBrain;
    private BrainState weirdBrain;
    private BrainState normalBrain;
    private BrainState currentBrain;

    /**
     * Organs
     */
    protected Lungs lungs;
    protected Legs legs;
    protected Mouth mouth;
    public Eyes eyes;

    protected Stomach stomach;

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
        this.stomach = new Stomach(this);
        this.mouth = new Mouth(this);

        brainChemical = new ChemicalRessources(0, 0, 0, 0);

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

    public void consume(ChemicalRessources substance){
        currentBrain.consume(substance);
        updtateState();
    }

    private void updtateState(){
        /* EXEMPLE DE CHANGEMENT DE STATE */
        if(brainChemical.getCaffeinLevel() > 100) {
            System.out.println("Changement d'état du cerveau : le cerveau est excité");
            currentBrain = exitedBrain;
        }
        else if(brainChemical.getAlcoolLevel() > 100) {
            System.out.println("Changement d'état du cerveau : le cerveau est bourré");
            currentBrain = drunkBrain;
        }
        else if(brainChemical.getPschoticLevel() > 100) {
            System.out.println("Changement d'état du cerveau : le cerveau est bizarre...");
            currentBrain = weirdBrain;
        } else {
            currentBrain = normalBrain;
        }
    }

    /**
     * Every next turn, we reduce all the chemical ressources
     * and check if we have to change the state
     */
    public void updateChemicalLevel() {
        //Reduce all brains chemicals level
        if(brainChemical.getAlcoolLevel() > 0)
            brainChemical.setAlcoolLevel(brainChemical.getAlcoolLevel() - 1);
        if(brainChemical.getCaffeinLevel() > 0)
            brainChemical.setCaffeinLevel(brainChemical.getCaffeinLevel() - 1);
        if(brainChemical.getPschoticLevel() > 0)
            brainChemical.setPschoticLevel(brainChemical.getPschoticLevel() - 1);

    }

    public void die() {
        // TODO: signaler au GameManager que le personnage est mort ???
        currentBrain.die();
    }

    public ChemicalRessources getBrainChemical() {
        return brainChemical;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void stress() {
        // TODO: currentBrain devrait augmenter rythme cardiaque
        currentBrain.stress();
    }

    public void processEyesVision(Event event) {
        currentBrain.processEyesVision(event);
    }
}
