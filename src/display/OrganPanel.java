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

        createOrganPanel("Estomac", Stomach.class.getName());

        createOrganPanel("Yeux", Eyes.class.getName());

        frame.setVisible(true);
    }

    private void createOrganPanel(String label, String organClassName){
        JPanel estomac = new JPanel();
        estomac.setLayout(new GridLayout(2,1));
        estomac.add(new JLabel(label));
        estomac.add(new JLabel("Status"));
        //TODO
//        estomac.add(new JLabel())
        frame.add(estomac);
        organPanels.put(organClassName, estomac);
    }

    public static void updateOrganDisplay(String organClassName, String toDisplay){
        JPanel specificOrganPanel = organPanels.get(organClassName);
        JLabel status = (JLabel) specificOrganPanel.getComponent(1);
        status.setText(toDisplay);

        frame.setVisible(true);
        frame.repaint();
    }

    public static void updateOrganResourcesDisplay(String organClassName, String[] toDisplay){
        JPanel specificOrganPanel = organPanels.get(organClassName);
        JLabel oxygen = (JLabel) specificOrganPanel.getComponent(2);
        oxygen.setText(toDisplay[0]); //TODO
        
        frame.setVisible(true);
        frame.repaint();
    }
}
