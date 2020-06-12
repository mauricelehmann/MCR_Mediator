import display.ControlPanel;
import display.StatePanel;
import gameManager.GameManager;

public class Main {

    public static void main(    String[] args) {

        GameManager gameManager = new GameManager();
        new ControlPanel(gameManager);
        new StatePanel(gameManager);
        gameManager.startGame();
    }
}
