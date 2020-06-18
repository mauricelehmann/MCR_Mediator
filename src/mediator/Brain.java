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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Le Brain est le cerveau principal, il change d'état interne selon ses propres niveaux de ressources
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

        bodyResources = new BodyResources();
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

    @Override
    public void askOxygen(double value) {
        currentBrain.askOxygen(value);
    }

    @Override
    public void run() {
        currentBrain.run();
    }

    public void look(Event event) {
        eyes.see(event);
    }

    public void calmDown() {
        //TODO : A implémenter ! Après tout faut quand même que notre personnage puisse se calmer !
    }

    @Override
    public void notifyEvent(Event event) {
        currentBrain.notifyEvent(event);
    }

    public void eat(BodyResources substance) {
        currentBrain.eat(substance);
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

    public BodyResources getBrainResources() {
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
            organ.refill((bodyResources.splitShare(organ.getResources(), organ.getSize() / biomass)));
        }
    }

    public void refillBlood(ResourceType resourceType, double amount)
    {
        bodyResources.fill(resourceType, amount);
    }
}
