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
            ConsoleUtility.wait(2.0);
            ConsoleUtility.clearScreen();
            while (!gameOver) {
                printWager();
                printDiceImage();
                printBanker(2);
                if (banker.getScore() == 7) {
                    bankerWin();
                } else if (banker.getScore() == 0) {
                    bankerLose();
                } else {
                    determineOutcomes();
                }
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
            if (banker.getScore() == -1) {
                System.out.println("Banker has to re-roll");
                ConsoleUtility.wait(1.0);
                while (banker.getScore() == -1) {
                    for (int i = 0; i <= 3; i++) {
                        ConsoleUtility.clearLine();
                    }
                    banker.rollDice();
                    banker.printDice();
                }
            } else if (banker.getScore() == 7) {
                System.out.println(ConsoleUtility.color("The banker wins!", "Red"));
                ConsoleUtility.wait(1.0);
            } else if (banker.getScore() == 0) {
                System.out.println(ConsoleUtility.color("The banker loses!", "Green"));
                ConsoleUtility.wait(1.0);
            } else {
                System.out.println("The banker's score is " + banker.getScore());
                ConsoleUtility.wait(1.0);
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
            scan.nextLine();
            while (player.getWager() < 0 || player.getWager() > player.getChips()) {
                ConsoleUtility.clearLine();
                System.out.print("How much would you like to wager? ");
                player.setWager(scan.nextInt());
                scan.nextLine();
            }
            System.out.println(ConsoleUtility.color("----------------------------------------", player.getColor()));
        } else if (stage == 2) {
            System.out.println(ConsoleUtility.color("------------------PLAYER-" + player.getNumber() + "--------------", player.getColor()));
            System.out.println("Player name: " + player.getName());
            System.out.println("Chips: " + player.getChips());
            System.out.print("Press enter to roll the dice! ");
            scan.nextLine();
            player.rollDice();
            player.printDice();
            if (player.getScore() == -1) {
                System.out.println(player.getName() + " has to re-roll");
                ConsoleUtility.wait(1.0);
                while (player.getScore() == -1) {
                    for (int i = 0; i <= 3; i++) {
                        ConsoleUtility.clearLine();
                    }
                    player.rollDice();
                    player.printDice();
                }
            }
            if (player.getScore() == 7) {
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
        if (!p1.isDead()) {
            printDiceImage();
            printBanker( 1);
            printPlayer(1, p1);
            ConsoleUtility.clearScreen();
        }
        if (!p2.isDead()) {
            printDiceImage();
            printBanker( 1);
            printPlayer(1, p2);
            ConsoleUtility.clearScreen();
        }
        if (!p3.isDead()) {
            printDiceImage();
            printBanker( 1);
            printPlayer(1, p3);
            ConsoleUtility.clearScreen();
        }
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
    private void bankerWin() {
        System.out.println(ConsoleUtility.color(p1.getName() + " lost " + p1.getWager() + " chips!", p1.getColor()));
        p1.changeChips(-p1.getWager());
        ConsoleUtility.wait(1.0);
        System.out.println(ConsoleUtility.color(p2.getName() + " lost " + p2.getWager() + " chips!", p2.getColor()));
        p2.changeChips(-p2.getWager());
        ConsoleUtility.wait(1.0);
        System.out.println(ConsoleUtility.color(p3.getName() + " lost " + p3.getWager() + " chips!", p3.getColor()));
        p3.changeChips(-p3.getWager());
        ConsoleUtility.wait(1.0);
        banker.changeChips(p1.getWager() + p2.getWager() + p3.getWager());
        System.out.println();
        System.out.print("Press enter to continue");
        scan.nextLine();
    }
    private void bankerLose() {
        System.out.println(ConsoleUtility.color(p1.getName() + " won " + p1.getWager() + " chips!", p1.getColor()));
        p1.changeChips(p1.getWager());
        ConsoleUtility.wait(1.0);
        System.out.println(ConsoleUtility.color(p2.getName() + " won " + p2.getWager() + " chips!", p2.getColor()));
        p2.changeChips(p2.getWager());
        ConsoleUtility.wait(1.0);
        System.out.println(ConsoleUtility.color(p3.getName() + " won " + p3.getWager() + " chips!", p3.getColor()));
        p3.changeChips(p3.getWager());
        ConsoleUtility.wait(1.0);
        banker.changeChips(-p1.getWager() - p2.getWager() - p3.getWager());
        System.out.println();
        System.out.print("Press enter to continue");
        scan.nextLine();
    }
    private void determinePlayerWin(Player player) {
        if (banker.getScore() > player.getScore()) {
            System.out.println(ConsoleUtility.color(player.getName() + " lost " + player.getWager() + " chips!", player.getColor()));
            player.changeChips(-player.getWager());
            banker.changeChips(player.getWager());
            ConsoleUtility.wait(1.0);
        } else if (banker.getScore() < player.getScore()) {
            System.out.println(ConsoleUtility.color(player.getName() + " won " + player.getWager() + " chips!", player.getColor()));
            player.changeChips(player.getWager());
            banker.changeChips(-player.getWager());
            ConsoleUtility.wait(1.0);
        }
    }
    private void printPlayerOut(Player player) {
        System.out.println(player.getName() + "is OUT!");
    }

    private void determineOutcomes() {
        if (!p1.isDead()) {
            printPlayer(2, p1);
        }
        if (!p2.isDead()) {
            printPlayer(2, p2);
        }
        if (!p3.isDead()) {
            printPlayer(2, p3);
        }
        System.out.println();
        if (!p1.isDead()) {
            determinePlayerWin(p1);
        } else {
            printPlayerOut(p1);
        }
        if (!p2.isDead()) {
            determinePlayerWin(p2);
        } else {
            printPlayerOut(p2);
        }
        if (!p3.isDead()) {
            determinePlayerWin(p3);
        } else {
            printPlayerOut(p3);
        }
        System.out.println();
        System.out.print("Press enter to continue");
        scan.nextLine();
    }
}
