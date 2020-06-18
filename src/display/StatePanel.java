package display;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import gameManager.GameManager;

import javax.swing.*;
import java.awt.*;

public class StatePanel {
    private static GameManager gManager = null;
    private static final int CONTROLLER_SIDE = 300;
    private static final JFrame frame = new JFrame("Panneau d'état");
    private static JLabel stateValue = new JLabel("Normal");
    private static JLabel alcoholLevel = new JLabel("0");
    private static JLabel caffeineLevel = new JLabel("0");
    private static JLabel psychedelicLevel = new JLabel("0");

    public StatePanel(GameManager gManager){

        frame.setSize(CONTROLLER_SIDE, CONTROLLER_SIDE);
        StatePanel.gManager = gManager;

        //Names of value
        frame.add(new JLabel("Etat du cerveau :"));
        frame.add(stateValue);

        frame.add(new JLabel("Niveau d'alcool :"));
        frame.add(alcoholLevel);

        frame.add(new JLabel("Niveau de caféine :"));
        frame.add(caffeineLevel);

        frame.add(new JLabel("Niveau de psychedélic :"));
        frame.add(psychedelicLevel);

        frame.setLayout(new GridLayout(4, 2));

        frame.setVisible(true);
    }

    public static void updateStateDisplay(String newState){
        stateValue.setText(newState);
        frame.repaint();
    }

    public static void updateChemicalsDisplay(BodyResources chem){
        alcoholLevel.setText("" + chem.getResourceAmount(ResourceType.Alcohol));
        caffeineLevel.setText("" + chem.getResourceAmount(ResourceType.Caffein));
        psychedelicLevel.setText("" + chem.getResourceAmount(ResourceType.Psychedelic));
        frame.repaint();
    }
}
