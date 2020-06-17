package display;

import bodyRessources.BodyResources;
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
    private static JLabel alcohol = new JLabel("Alcool init");
    private static JLabel caffeine = new JLabel("Cafféine init ");
    private static JLabel psychedelic = new JLabel("Psychotic init");

    public StatePanel(GameManager gManager){
        frame.setLayout(new FlowLayout());
        frame.setSize(CONTROLLER_SIDE,CONTROLLER_SIDE);
        this.gManager = gManager;
        frame.add(state);
        frame.add(new JLabel("Alcool").add(alcohol));    //TODO
        frame.add(caffeine);
        frame.add(psychedelic);

//        JButton button;
//        button = new JButton("Test");
//        frame.add(button);

        frame.setVisible(true);
    }

    public static void updateStateDisplay(String newState){
        state.setText(newState);
        frame.repaint();
    }

    public static void updateChemicalsDisplay(BodyResources chem){
        alcohol.setText("" + chem.getResourceAmount(ResourceType.Alcohol));
        caffeine.setText("" + chem.getResourceAmount(ResourceType.Caffein));
        psychedelic.setText("" + chem.getResourceAmount(ResourceType.Psychedelic));
        frame.repaint();
    }
}
