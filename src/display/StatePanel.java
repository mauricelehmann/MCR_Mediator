package display;

import gameManager.GameManager;
import mediator.NormalBrainState;

import javax.swing.*;
import java.awt.*;

public class StatePanel {
    private static GameManager gManager = null;
    private static final int CONTROLLER_SIDE = 200;
    private static final JFrame frame = new JFrame("Panneau d'état");
    private static JLabel state = new JLabel(NormalBrainState.class.getName());

    public StatePanel(GameManager gManager){
        frame.setLayout(new FlowLayout());
        frame.setSize(CONTROLLER_SIDE,CONTROLLER_SIDE);
        this.gManager = gManager;
        frame.add(state);

//        JButton button;
//        button = new JButton("Test");
//        frame.add(button);

        frame.setVisible(true);
    }

    public static void updateStateDisplay(String newState){
        state.setText(newState);
        frame.repaint();
    }
}
