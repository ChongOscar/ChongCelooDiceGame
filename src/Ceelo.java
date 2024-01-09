public class Ceelo {
    private boolean gameOver;
    private int highScore;
    private Banker banker;
    private Player p1;
    private Player p2;
    private Player p3;

    public Ceelo() {
        this.gameOver = false;
        this.highScore = 0;
        this.banker = new Banker();
    }

    public void play() {
        printMenu();
    }

    private void printMenu() {
        System.out.println(ConsoleUtility.color("Welcome to the Cee-lo Dice Game!", "Red"));

    }
}
