package display;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import gameManager.GameManager;
import organ.Eyes;
import organ.Organ;
import organ.Stomach;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganPanel {
    private static GameManager gManager = null;
    private static final int CONTROLLER_SIDE = 500;
    private static final JFrame frame = new JFrame("Panneau d'organe");

    private static Map<String, JPanel> organPanels = new HashMap<>();

//    class testOrganPanel extends JPanel{
//        private Organ organ;
//    }

    public OrganPanel(GameManager gManager, List<Organ> organList){
        frame.setLayout(new GridLayout(1, organList.size()));
        frame.setSize(CONTROLLER_SIDE,CONTROLLER_SIDE);
        this.gManager = gManager;

        for(Organ o : organList){
            createOrganPanel(o.getClass().getSimpleName(), o.getClass().getName());
        }

//        createOrganPanel("Estomac", Stomach.class.getName());
//
//        createOrganPanel("Yeux", Eyes.class.getName());

        frame.setVisible(true);
    }

    private void createOrganPanel(String label, String organClassName){
        JPanel estomac = new JPanel();
        estomac.setLayout(new GridLayout(ResourceType.values().length + 3,1));
        estomac.add(new JLabel(label));
        estomac.add(new JLabel("Status"));
        for(ResourceType r : ResourceType.values()){
            estomac.add(new JLabel(r.name()));
        }
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

    public static void updateOrganResourcesDisplayOld(String organClassName, String[] toDisplay){
        JPanel specificOrganPanel = organPanels.get(organClassName);
        JLabel oxygen = (JLabel) specificOrganPanel.getComponent(2);
        oxygen.setText(toDisplay[0]); //TODO
        
        frame.setVisible(true);
        frame.repaint();
    }

    public  static void updateOrganResourcesDisplay(String organClassName, BodyResources r){
        JPanel specificOrganPanel = organPanels.get(organClassName);
        JLabel resource;
        for(int i = 0; i < ResourceType.values().length; ++i){
            resource = (JLabel) specificOrganPanel.getComponent(2+i);
            resource.setText(""+r.getResourceAmount(ResourceType.values()[i]));
        }

        frame.setVisible(true);
        frame.repaint();
    }
}
