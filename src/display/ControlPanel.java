package display;

import event.Event;
import event.EventGenerator;
import event.action.Action;
import gameManager.GameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

public class ControlPanel
{
    private static GameManager gManager = null;
    private static final int CONTROLLER_SIDE = 200;
    private static final JFrame frame = new JFrame("Panneau de contrôle");
    private static final String ripImageFile = "rip.jpeg";

    public ControlPanel(GameManager gManager){
        frame.setLayout(new FlowLayout());
        frame.setSize(CONTROLLER_SIDE,CONTROLLER_SIDE);
        ControlPanel.gManager = gManager;

//        JButton button;
//        button = new JButton("Test");
//        frame.add(button);

        frame.setVisible(true);
    }

    public static void deathScreen() {
        // URL ripImageURL = EventGenerator.class.getClassLoader().getResource(ripImageFile);

        frame.getContentPane().removeAll();

        /*try {
            BufferedImage ripImage = ImageIO.read(ripImageURL);
            frame.getGraphics().drawImage(ripImage, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    public static void handleEvent(Event event){
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();

        JLabel eventLabel = new JLabel(event.getDescription());
        frame.add(eventLabel);

        JButton button;
        LinkedList<Action> actions = event.getPossibleActions();
        for(int i = 0; i < actions.size(); ++i) {
            System.out.println(i + ") " + actions.get(i));
            button = new JButton(actions.get(i).getIcon());
            button.setBorder(BorderFactory.createEmptyBorder());
            int finalI = i;
            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) { gManager.applyAction(actions.get(finalI)); }
            });

            frame.add(button);
        }

        frame.setVisible(true);
    }
}
