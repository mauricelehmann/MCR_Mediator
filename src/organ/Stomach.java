package organ;

import bodyRessources.ChemicalRessources;
import mediator.Brain;

public class Stomach extends Organ {
    public Stomach(Brain mediator) {
        super(mediator);
    }

    public void digest(ChemicalRessources substance, ChemicalRessources brainChemical){
        System.out.println("Estomac : je digigère une substance composée de : ");
        System.out.println("Cafféine : " + substance.getCaffeinLevel());
        System.out.println("Psychédélique : " + substance.getPschoticLevel());
        System.out.println("Alcool : " + substance.getAlcoolLevel());

        notifyDisplay("L'estomac digère du stuff");

        //Update chemicals
        brainChemical.setCaffeinLevel(brainChemical.getCaffeinLevel() + substance.getCaffeinLevel());
        brainChemical.setPschoticLevel(brainChemical.getPschoticLevel() + substance.getPschoticLevel());
        brainChemical.setAlcoolLevel(brainChemical.getAlcoolLevel() + substance.getAlcoolLevel());

    }

}
