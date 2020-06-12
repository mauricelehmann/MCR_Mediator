package mediator;

import bodyRessources.BodyRessources;
import organ.Organ;

import java.util.ArrayList;

public class BloodSystem implements BodyMediator {

    ArrayList<Organ> organs;

    private BodyRessources resources;
    private double biomass;//Sum of organ sizes

    public BloodSystem(ArrayList<Organ> organs){
        this.organs = organs;
        resources = new BodyRessources();

        biomass = 0;
        for(Organ organ : organs)
        {
            biomass += organ.getSizeFactor();
        }
    }

    public void flow() {
        for (Organ organ: organs)
        {
            //Give the organ its fair share of resources
            organ.refill((resources.splitShare(organ.getSizeFactor()/biomass)));
        }
    }
}
