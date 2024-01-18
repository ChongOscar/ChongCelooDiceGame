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

    public Ceelo() {
        this.gameOver = false;
        this.highScore = 0;
        this.banker = new Banker();
        this.scan = new Scanner(System.in);
    }

    public void play() {
        int input;
        printMainMenu();
        input = scan.nextInt();
        scan.nextLine();
        ConsoleUtility.clearScreen();
        getNames();
        if (input == 1) {
            System.out.println(ConsoleUtility.color("GAME START", "Red"));
            ConsoleUtility.wait(1.0);
            ConsoleUtility.clearScreen();
            while (!gameOver) {
                printWager();
                printDiceImage();
                printBanker(2);
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


    private void printDiceImage() {
        System.out.println("""
                            ____
                           /\\' .\\    _____
                          /: \\___\\  / .  /\\
                          \\' / . / /____/..\\
                           \\/___/  \\'  '\\  /
                                    \\'__'\\/
                """);
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
            banker.rollDice();
            banker.printDice();
            if (banker.getScore() == 6) {
                System.out.println(ConsoleUtility.color("The banker wins!", "Red"));
            } else if (banker.getScore() == 0) {
                System.out.println(ConsoleUtility.color("The banker loses!", "Green"));
            } else {
                System.out.println("The banker's score is " + banker.getScore());
            }
            System.out.println(ConsoleUtility.color("----------------------------------------", "Green"));
        }
    }

    private void printPlayer(int stage, Player player) {
        if (stage == 1) {
            System.out.println(ConsoleUtility.color("------------------PLAYER-" + player.getNumber() + "--------------", player.getColor()));
            System.out.println("Player name: " + player.getName());
            System.out.println("Chips: " + player.getChips());
            System.out.print("How much would you like to wager? ");
            player.setWager(scan.nextInt());
            System.out.println(ConsoleUtility.color("----------------------------------------", player.getColor()));
        } else if (stage == 2) {
            System.out.println(ConsoleUtility.color("------------------PLAYER-" + player.getNumber() + "--------------", player.getColor()));
            System.out.println("Player name: " + player.getName());
            System.out.println("Chips: " + player.getChips());
            System.out.print("Press enter to roll the dice! ");
            scan.nextLine();
            player.rollDice();
            player.printDice();
            if (player.getScore() == 6) {
                System.out.println(ConsoleUtility.color("The player wins!", "Green"));
            } else if (player.getScore() == 0) {
                System.out.println(ConsoleUtility.color("The player loses!", "Red"));
            } else {
                System.out.println("The player's score is " + player.getScore());
            }
            System.out.println(ConsoleUtility.color("----------------------------------------", player.getColor()));
        }
    }
    private void printWager() {
        printDiceImage();
        printBanker( 1);
        printPlayer(1, p1);
        ConsoleUtility.clearScreen();
        printDiceImage();
        printBanker( 1);
        printPlayer(1, p2);
        ConsoleUtility.clearScreen();
        printDiceImage();
        printBanker( 1);
        printPlayer(1, p3);
        ConsoleUtility.clearScreen();
    }
    private void getNames() {
        printDiceImage();
        System.out.print(ConsoleUtility.color("Player 1, enter your name : ", "Red"));
        p1 = new Player(scan.nextLine(), "1");
        System.out.print(ConsoleUtility.color("Player 2, enter your name : ", "Blue"));
        p2 = new Player(scan.nextLine(), "2");
        System.out.print(ConsoleUtility.color("Player 3, enter your name : ", "Yellow"));
        p3 = new Player(scan.nextLine(), "3");
    }
}
