import java.io.Console;
import java.util.Scanner;
import java.util.concurrent.CompletionService;

public class Ceelo {
    private boolean gameOver;
    private int highScore;
    private Banker banker;
    private Player p1;
    private Player p2;
    private Player p3;
    private Scanner scan;

    public Ceelo() {
        this.gameOver = false;
        this.highScore = 0;
        this.banker = new Banker();
        scan = new Scanner(System.in);
    }

    public void play() {
        int input;
        printMainMenu();
        input = scan.nextInt();
        scan.nextLine();
        ConsoleUtility.clearScreen();
        if (input == 1) {
            System.out.println(ConsoleUtility.color("GAME START", "Red"));
            wait(1.0);
            ConsoleUtility.clearScreen();
            while (!gameOver) {
                System.out.print("Banker chips: ");
                System.out.println(ConsoleUtility.color(String.valueOf(banker.getChips()), "Green"));
            }
        }

    }

    private void printMainMenu() {
        System.out.println(ConsoleUtility.color("Welcome to the Cee-lo Dice Game!", "Red"));
        System.out.println("Highscore: " + highScore);
        System.out.println("(1) Start New Game");
        System.out.println("(2) Quit");
        System.out.print("Enter your choice: ");
    }

    private void wait(double seconds) {
        try {
            Thread.sleep((int) (seconds * 1000));
        } catch (Exception e) {
            System.out.println("error");
        }

    }
}
