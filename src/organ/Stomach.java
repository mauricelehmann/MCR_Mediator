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
        brainChemical.setPschoticLevel(brainChemical.getPschoticLevel() + substance.getPschoticLevel());
        brainChemical.setAlcoolLevel(brainChemical.getAlcoolLevel() + substance.getAlcoolLevel());
        brainChemical.setProteinLevel(brainChemical.getProteinLevel() + substance.getProteinLevel());
    }
}
