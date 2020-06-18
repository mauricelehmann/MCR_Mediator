package display;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import gameManager.GameManager;
import organ.Organ;

import javax.swing.*;
import java.awt.*;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganPanel {

    private static GameManager gManager = null;
    private static final int CONTROLLER_SIDE = 500;
    private static final JFrame frame = new JFrame("Panneau d'organe");

    private static Map<String, JLabel> oxygeneLevel = new HashMap<>();
    private static Map<String, JLabel> proteinLevel = new HashMap<>();


    public OrganPanel(GameManager gManager, List<Organ> organList){

        frame.setSize(CONTROLLER_SIDE,CONTROLLER_SIDE);

        this.gManager = gManager;

        frame.add(new JLabel("Organes"));
        frame.add(new JLabel("Niveau oxygène"));
        frame.add(new JLabel("Niveau proteine"));

        for(Organ o : organList){
            createOrganPanel(o.getClass().getSimpleName(), o.getClass().getName());
        }

        frame.setLayout(new GridLayout(organList.size() + 1, 3));
        frame.setVisible(true);
    }

    private void createOrganPanel(String label, String organClassName){

        frame.add(new JLabel(label));

        JLabel oxygeneLevel = new JLabel("0");
        frame.add(oxygeneLevel);
        OrganPanel.oxygeneLevel.put(organClassName, oxygeneLevel);

        JLabel proteinLevel = new JLabel("0");
        frame.add(proteinLevel);
        OrganPanel.proteinLevel.put(organClassName, proteinLevel);

    }

    public  static void updateOrganResourcesDisplay(String organClassName, BodyResources r){

        oxygeneLevel.get(organClassName).setText("" + r.getResourceAmount(ResourceType.Oxygen));
        proteinLevel.get(organClassName).setText("" + r.getResourceAmount(ResourceType.Protein));

        frame.setVisible(true);
        frame.repaint();
    }

}
