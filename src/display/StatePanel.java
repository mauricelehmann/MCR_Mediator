package display;

import bodyRessources.BodyRessources;
import bodyRessources.ResourceType;
import gameManager.GameManager;
import mediator.NormalBrainState;

import javax.swing.*;
import java.awt.*;

public class StatePanel {
    private static GameManager gManager = null;
    private static final int CONTROLLER_SIDE = 200;
    private static final JFrame frame = new JFrame("Panneau d'état");
    private static JLabel state = new JLabel(NormalBrainState.class.getName());
    private static JLabel alcool = new JLabel("Alcool init");
    private static JLabel caffeine = new JLabel("Cafféine init ");
    private static JLabel psychotic = new JLabel("Psychotic init");

    public StatePanel(GameManager gManager){
        frame.setLayout(new FlowLayout());
        frame.setSize(CONTROLLER_SIDE,CONTROLLER_SIDE);
        this.gManager = gManager;
        frame.add(state);
        frame.add(new JLabel("Alcool").add(alcool));    //TODO
        frame.add(caffeine);
        frame.add(psychotic);

//        JButton button;
//        button = new JButton("Test");
//        frame.add(button);

        frame.setVisible(true);
    }

    public static void updateStateDisplay(String newState){
        state.setText(newState);
        frame.repaint();
    }

    public static void updateChemicalsDisplay(BodyRessources chem){
        alcool.setText("" + chem.getResourceAmount(ResourceType.Alcohol));
        caffeine.setText("" + chem.getResourceAmount(ResourceType.Caffein));
        psychotic.setText("" + chem.getResourceAmount(ResourceType.Psychedelic));
        frame.repaint();
    }
}
