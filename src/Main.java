import display.ControlPanel;
import gameManager.GameManager;

public class Main {

    public static void main(    String[] args) {

        GameManager gameManager = new GameManager();
        new ControlPanel(gameManager);
        gameManager.startGame();
    }
}
