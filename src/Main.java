import display.ControlPanel;
import gameManager.GameManager;

public class Main {

    public static void main(    String[] args) {
        new ControlPanel();
        GameManager gameManager = new GameManager();
        gameManager.startGame();
    }
}
