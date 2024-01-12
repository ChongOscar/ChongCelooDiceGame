import java.io.Console;
import java.sql.SQLOutput;
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
    private int round;

    public Ceelo() {
        this.gameOver = false;
        this.highScore = 0;
        this.banker = new Banker();
        this.scan = new Scanner(System.in);
        this.round = 1;
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
                printStart();
                printBanker( 1);
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

    private void printStart() {
        System.out.println("""
                            ____
                           /\\' .\\    _____
                          /: \\___\\  / .  /\\
                          \\' / . / /____/..\\
                           \\/___/  \\'  '\\  /
                                    \\'__'\\/
                """);
        System.out.println(ConsoleUtility.color("Round : " + round, "Red"));
    }

    private void printBanker(int stage) {
        if (stage == 1) {
            System.out.println(ConsoleUtility.color("------------------BANKER----------------", "Green"));
            System.out.println("Chips: " + banker.getChips());
            System.out.println(ConsoleUtility.color("----------------------------------------", "Green"));
        }
        if (stage == 2) {
            System.out.println(ConsoleUtility.color("------------------BANKER----------------", "Green"));
            System.out.println("Chips: " + banker.getChips());
            System.out.println();
            System.out.println("The Banker Rolls: ");
            banker.printDice();
            System.out.println(ConsoleUtility.color("----------------------------------------", "Green"));
        }
    }

    private void printPlayer() {

    }
}
