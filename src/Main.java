import gameManager.GameManager;

public class Main {

    public static void main(String[] args) {
        GameManager gameManager = new GameManager("events.xml");
        gameManager.startGame();

    }
}
