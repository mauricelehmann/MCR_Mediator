package mediator;

import display.OrganPanel;
import display.StatePanel;
import bodyRessources.BodyRessources;
import bodyRessources.ResourceType;
import event.Event;
import gameManager.GameManager;
import organ.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Le Brain est le cerveau principal, il change d'état interne selon ses propres niveaux de ressources
 */
public class Brain implements BrainState {

    private GameManager gameManager;

    /**
     * System (Organism) info
     */
    private BodyRessources brainResources;
    private BodyRessources bodyResources;
    private double biomass;//Sum of organ sizes

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

    List<Organ> organs;

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

        bodyResources = new BodyRessources();
        brainResources = new BodyRessources();

        //TODO : to refactor, it should not be the brain's resposibility to create the organs
        organs = new ArrayList<>();
        organs.add(lungs);
        organs.add(legs);
        organs.add(eyes);

        biomass = 0;
        for(Organ organ : organs)
        {
            biomass += organ.getSizeFactor();
        }
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

    public void consume(BodyRessources substance) {
        currentBrain.consume(substance);
        updateState();
    }

    private void updateState(){
        /* EXEMPLE DE CHANGEMENT DE STATE */
        if(brainResources.getResourceAmount(ResourceType.Caffein) > 100) {
            System.out.println("Changement d'état du cerveau : le cerveau est excité");
            currentBrain = exitedBrain;
        } else if(brainResources.getResourceAmount(ResourceType.Alcohol) > 100) {
            System.out.println("Changement d'état du cerveau : le cerveau est bourré");
            currentBrain = drunkBrain;
        } else if(brainResources.getResourceAmount(ResourceType.Psychedelic) > 100) {
            System.out.println("Changement d'état du cerveau : le cerveau est bizarre...");
            currentBrain = weirdBrain;
        } else {
            currentBrain = normalBrain;
        }

        StatePanel.updateStateDisplay(getCurrentBrainState());
        StatePanel.updateChemicalsDisplay(brainResources);
    }

    /**
     * Every next turn, we reduce all the chemical ressources
     * and check if we have to change the state
     */
    public void updateChemicalLevel() {
        //Reduce all brains chemicals level
        if(brainResources.getResourceAmount(ResourceType.Alcohol) > 0)
            brainResources.setResourceAmount(ResourceType.Alcohol, brainResources.getResourceAmount(ResourceType.Alcohol) - 1);
        if(brainResources.getResourceAmount(ResourceType.Caffein) > 0)
            brainResources.setResourceAmount(ResourceType.Caffein, brainResources.getResourceAmount(ResourceType.Caffein) - 1);
        if(brainResources.getResourceAmount(ResourceType.Psychedelic) > 0)
            brainResources.setResourceAmount(ResourceType.Psychedelic, brainResources.getResourceAmount(ResourceType.Psychedelic) - 1);
    }

    public void die() {
        // TODO: signaler au GameManager que le personnage est mort ???
//        StatePanel.updateChemicalsDisplay(brainChemical);
        currentBrain.die();
    }

    public String getCurrentBrainState(){
        return currentBrain.getClass().getName();
    }

    public void updateOrganDisplay(Organ organ, String toDisplay) {
        OrganPanel.updateOrganDisplay(organ.getClass().getName(), toDisplay);
    }

    public BodyRessources getBrainResources() {
        return brainResources;
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

    public void bloodFlow() {
        for (Organ organ: organs)
        {
            //Give the organ its fair share of resources
            organ.refill((bodyResources.splitShare(organ.getRessources(), organ.getSizeFactor() / biomass)));
        }
    }

    public void refillBlood(ResourceType resourceType, double amount)
    {
        bodyResources.refill(resourceType, amount);
    }
}
