package mediator;

import display.OrganPanel;
import display.StatePanel;
import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import event.Event;
import gameManager.GameManager;
import organ.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Brain is the main brain, it change the internal state based on the resources levels
 */
public class Brain implements BrainState {
    private GameManager gameManager;

    /**
     * System (Organism) info
     */
    private BodyResources brainResources;
    private BodyResources bodyResources;
    private double biomass; //Sum of organ sizes

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
    public Heart heart;

    protected Stomach stomach;

    List<Organ> organs;

    /**
     * Constructor
     * @param gameManager its game manager
     */
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
        this.heart = new Heart(this);

        bodyResources = new BodyResources();
        brainResources = new BodyResources();

        //TODO : to refactor, it should not be the brain's resposibility to create the organs
        organs = new ArrayList<>();
        organs.add(lungs);
        organs.add(legs);
        organs.add(eyes);
        organs.add(mouth);
        organs.add(stomach);
        organs.add(heart);

        biomass = 0;
        for(Organ organ : organs)
        {
            biomass += organ.getSizeFactor();
        }
    }

    /**
     * Ask oxygen to the brain
     */
    @Override
    public void askOxygen(){
        currentBrain.askOxygen();
    }

    /**
     * Ask the body to run
     */
    @Override
    public void run() {
        currentBrain.run();
    }

    /**
     * Notify event to the brain
     * @param event the event
     */
    @Override
    public void notifyEvent(Event event) {
        currentBrain.notifyEvent(event);
    }

    /**
     * Consume given resources
     * @param substance the resources
     */
    public void consume(BodyResources substance) {
        currentBrain.consume(substance);
        updateState();
    }

    /**
     * Update the brain state based on its resources level
     */
    private void updateState(){
        /* CHANGEMENT DE STATE */
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
     * Every next turn, we reduce all the chemical resources
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

    /**
     * Signal its death to the gameManager and let the current
     * brain handle the death
     */
    public void die() {
        gameManager.playerDies();
        currentBrain.die();
    }

    /**
     * Get the current brain state
     * @return the current brain state
     */
    public String getCurrentBrainState(){
        return currentBrain.getClass().getSimpleName();
    }

    /**
     * Update the organ display
     * @param organ the organ
     * @param toDisplay the string to display
     */
    public void updateOrganDisplay(Organ organ, String toDisplay) {
        //OrganPanel.updateOrganDisplay(organ.getClass().getName(), "<html>"+ toDisplay +"</html>");
        OrganPanel.updateOrganResourcesDisplay(organ.getClass().getName(), organ.getResources());
    }

    /**
     * Get the brain resources
     * @return the resources
     */
    public BodyResources getBrainResources() {
        return brainResources;
    }

    /**
     * Get the managed organs
     * @return the organs
     */
    public List<Organ> getOrgans() {
        return organs;
    }

    /**
     * Get the game manager
     * @return the game manager
     */
    public GameManager getGameManager() {
        return gameManager;
    }

    /**
     * Request the current brain to stress
     */
    public void stress() {
        currentBrain.stress();
    }

    /**
     * Process eyes vision
     * @param event the event seen
     */
    public void processEyesVision(Event event) {
        currentBrain.processEyesVision(event);
    }

    /**
     * Makes the blood flow
     */
    public void bloodFlow() {
        for (Organ organ: organs)
        {
            //Give the organ its fair share of resources
            organ.refill((bodyResources.splitShare(organ.getResources(), organ.getSizeFactor() / biomass)));
            organ.consumeResources();
        }
    }

    /**
     * Refill the blood system of a resource type
     * @param resourceType the resource type
     * @param amount the amount
     */
    public void refillBlood(ResourceType resourceType, double amount)
    {
        bodyResources.refill(resourceType, amount);
    }
}
