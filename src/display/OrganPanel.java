package display;

import gameManager.GameManager;
import organ.Eyes;
import organ.Organ;
import organ.Stomach;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class OrganPanel {
    private static GameManager gManager = null;
    private static final int CONTROLLER_SIDE = 500;
    private static final JFrame frame = new JFrame("Panneau d'organe");

    private static Map<String, JPanel> organPanels = new HashMap<>();

//    class testOrganPanel extends JPanel{
//        private Organ organ;
//    }

    public OrganPanel(GameManager gManager){
        frame.setLayout(new FlowLayout());
        frame.setSize(CONTROLLER_SIDE,CONTROLLER_SIDE);
        this.gManager = gManager;

//        JPanel estomac = new JPanel();
//        estomac.setLayout(new FlowLayout());
//        estomac.add(new JLabel("Estomac"));
//        frame.add(estomac);
//        organPanels.put(Stomach.class.getName(), estomac);
        createOrganPanel("Estomac", Stomach.class.getName());

        JPanel eyes = new JPanel();
        eyes.setLayout(new FlowLayout());
        eyes.add(new JLabel("Yeux"));
        frame.add(eyes);
        organPanels.put(Eyes.class.getName(), eyes);

        frame.setVisible(true);
    }

    private void createOrganPanel(String label, String organClassName){
        JPanel estomac = new JPanel();
        estomac.setLayout(new FlowLayout());
        estomac.add(new JLabel(label));
        //TODO
//        estomac.add(new JLabel())
        frame.add(estomac);
        organPanels.put(organClassName, estomac);
    }

    public static void updateOrganDisplay(String organClassName, String toDisplay){
        JPanel specificOrganPanel = organPanels.get(organClassName);
        specificOrganPanel.add(new JLabel(toDisplay));

        frame.setVisible(true);
        frame.repaint();
    }
}
