package mediator;

import display.OrganPanel;
import display.StatePanel;
import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import event.Event;
import gameManager.GameManager;
import organ.*;
import organ.varyingFrequencyOrgans.Heart;
import organ.varyingFrequencyOrgans.Lungs;

import java.util.*;

/**
 * Brain is the main brain, it change the internal state based on the resources levels
 */
public class Brain extends Organ implements BrainState {

    private GameManager gameManager;
    private BodyResources brainResources;

    /**
     * System (Organism) info
     */
    private BodyResources bodyResources;
    private double biomass;//Sum of organ sizes
    private Timer bodyClock;

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
    protected Heart heart;
    protected Lungs lungs;
    protected Legs legs;
    protected Mouth mouth;
    protected Eyes eyes;
    protected Stomach stomach;

    List<Organ> organs;

    /**
     * Constructor
     * @param gameManager its game manager
     */
    public Brain(GameManager gameManager) {
        super(null);
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

        bodyResources = new BodyResources(Arrays.asList(ResourceType.Oxygen, ResourceType.Protein), 50);
        brainResources = new BodyResources();

        organs = new ArrayList<>();
        organs.add(lungs);
        organs.add(legs);
        organs.add(eyes);
        organs.add(heart);
        organs.add(stomach);
        organs.add(mouth);

        biomass = 0;
        for(Organ organ : organs)
        {
            biomass += organ.getSize();
        }
    }

    public void start(){
        bodyClock = new Timer();
        bodyClock.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            for(Organ organ : organs)
            {
                organ.consumeResources();
            }
            }
        }, 1000, 1000);

        heart.reSchedule();
        lungs.reSchedule();
    }

    /**
     * Ask oxygen to the brain
     */
    @Override
    public void askOxygen(double value) {
        currentBrain.askOxygen(value);
    }

    /**
     * Ask the body to run
     */
    @Override
    public void run() {
        currentBrain.run();
    }

    public void look(Event event) {
        eyes.see(event);
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
     * Eat given resources
     * @param substance the resources
     */
    public void eat(BodyResources substance) {
        stomach.digest(substance, getBodyResources());
        currentBrain.eat(substance);
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
        if(organ == null)
        {
            OrganPanel.updateOrganResourcesDisplay("System", this.getBodyResources());
        }
        else
        {
            OrganPanel.updateOrganResourcesDisplay(organ.getClass().getName(), organ.getResources());
        }
    }

    /**
     * Get the brain resources
     * @return the resources
     */
    public BodyResources getBrainResources() {
        return brainResources;
    }

    /**
     * Get the Organism's resources
     * @return the resources
     */
    public BodyResources getBodyResources() {
        return bodyResources;
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
     * Slows heartbeat and respiratory rate
     */
    public void calmDown() {
        heart.accelerate(0.9);
        lungs.accelerate(0.7);
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
            organ.refill((bodyResources.splitShare(organ.getResources(), organ.getSize() / (biomass*10))));
            notifyDisplay("");
        }
    }

    /**
     * Refill the blood system of a resource type
     * @param resourceType the resource type
     * @param amount the amount
     */
    public void refillBlood(ResourceType resourceType, double amount)
    {
        bodyResources.fill(resourceType, amount);
    }

    @Override
    public void notifyDisplay(String toDisplay) {
        updateOrganDisplay(null, "");
    }
}
