package organ;

import bodyRessources.ChemicalRessources;
import mediator.Brain;

public class Stomach extends Organ {
    public Stomach(Brain mediator) {
        super(mediator);
    }

    public void digest(ChemicalRessources substance, ChemicalRessources brainChemical){

        System.out.println("Estomac : je digère une substance composée de : " + brainChemical.toString());

        //Update chemicals
        brainChemical.setCaffeinLevel(brainChemical.getCaffeinLevel() + substance.getCaffeinLevel());
        brainChemical.setPsychedelicLevel(brainChemical.getPsychedelicLevel() + substance.getPsychedelicLevel());
        brainChemical.setAlcoolLevel(brainChemical.getAlcoholLevel() + substance.getAlcoholLevel());
        brainChemical.setProteinLevel(brainChemical.getProteinLevel() + substance.getProteinLevel());
    }
}
