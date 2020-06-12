package display;

import event.GEvent;
import event.action.Action;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ControlPanel
{
    private static final int CONTROLLER_SIDE = 200;
    private static final JFrame frame = new JFrame("Panneau de contrôle");;

    public ControlPanel(){
        frame.setLayout(new FlowLayout());
        frame.setSize(CONTROLLER_SIDE,CONTROLLER_SIDE);

//        JButton button;
//        button = new JButton("Test");
//        frame.add(button);

        frame.setVisible(true);
    }

    public static void handleEvent(GEvent event){
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();

        JButton button;

        LinkedList<Action> actions = event.getPossibleActions();
        for(int i = 0; i < actions.size(); ++i) {
            System.out.println(i + ") " + actions.get(i));
            button = new JButton(actions.get(i).getName());
            frame.add(button);
        }

        frame.setVisible(true);
    }


}
