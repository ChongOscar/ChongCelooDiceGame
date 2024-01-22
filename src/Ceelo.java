
import java.util.Scanner;

public class Ceelo {
    private boolean gameOver;
    private String highScore;
    private Banker banker;
    private Player p1;
    private Player p2;
    private Player p3;
    private Scanner scan;

    public Ceelo() {
        this.gameOver = false;
        this.highScore = "N/A";
        this.banker = new Banker();
        this.scan = new Scanner(System.in);
    }

    public void play() {
        ConsoleUtility.clearScreen();
        int input;
        printMainMenu();
        input = scan.nextInt();
        scan.nextLine();
        ConsoleUtility.clearScreen();
        getNames();
        if (input == 1) {
            System.out.println(ConsoleUtility.color("GAME START", "Green"));
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
                    playerRolls();
                }
                gameOver = isGameOver();

            }
            if (p1.isDead() && p2.isDead() && p3.isDead()) {
                ConsoleUtility.clearScreen();
                System.out.println(ConsoleUtility.color("GAME OVER", "Red"));
                System.out.println(ConsoleUtility.color("The Banker wins!", "Red"));
            } else if (banker.isDead()) {
                determineGameWinner();
            }
            System.out.println();
            System.out.println("Press enter to return to the main menu");
            scan.nextLine();
            gameOver = false;
            banker = new Banker();
            play();
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
        if (stage == 1) {//wager stage
            System.out.println(ConsoleUtility.color("------------------BANKER----------------", "Green"));
            System.out.println("Chips: " + banker.getChips());
            System.out.println(ConsoleUtility.color("----------------------------------------", "Green"));
        }
        if (stage == 2) { //dice roll stage
            System.out.println(ConsoleUtility.color("------------------BANKER----------------", "Green"));
            System.out.println("Chips: " + banker.getChips());
            System.out.println();
            System.out.println("The Banker rolls: ");
            banker.rollDice();
            banker.printDice();
            if (banker.getScore() == -1) {
                System.out.println("Banker has to re-roll");
                ConsoleUtility.wait(1.0);
                while (banker.getScore() == -1) { //clears last 3 rolls 
                    for (int i = 0; i <= 3; i++) {
                        ConsoleUtility.clearLine();
                    }
                    banker.rollDice();
                    banker.printDice();
                    if (banker.getScore()== -1) {
                        System.out.println("Banker has to re-roll");
                    }
                    ConsoleUtility.wait(1.0);
                }
            }
             if (banker.getScore() == 7) {
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
        if (stage == 1) { // wager stage
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
        } else if (stage == 2) { //dice roll stage
            System.out.println(ConsoleUtility.color("------------------PLAYER-" + player.getNumber() + "--------------", player.getColor()));
            System.out.println("Player name: " + player.getName());
            System.out.println("Chips: " + player.getChips());
            System.out.print("Press enter to roll the dice! ");
            scan.nextLine();
            System.out.println();
            System.out.println("The Player rolls:");
            player.rollDice();
            player.printDice();
            if (player.getScore() == -1) {
                System.out.println(player.getName() + " has to re-roll");
                ConsoleUtility.wait(1.0);
                while (player.getScore() == -1) { // clears last 3 rolls
                    for (int i = 0; i <= 3; i++) {
                        ConsoleUtility.clearLine();
                    }
                    player.rollDice();
                    player.printDice();
                    if (player.getScore() == -1) {
                        System.out.println(player.getName() + " has to re-roll");
                    }
                    ConsoleUtility.wait(1.0);
                }
            }
            if (player.getScore() == 7) {
                System.out.println(ConsoleUtility.color("The player wins!", "Green"));
                ConsoleUtility.wait(1.0);
            } else if (player.getScore() == 0) {
                System.out.println(ConsoleUtility.color("The player loses!", "Red"));
                ConsoleUtility.wait(1.0);
            } else {
                System.out.println("The player's score is " + player.getScore());
                ConsoleUtility.wait(1.0);
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
        if (p1.isDead()) {
            System.out.println(ConsoleUtility.color(p1.getName() + " is OUT!", p1.getColor()));
        }
        ConsoleUtility.wait(1.0);
        System.out.println(ConsoleUtility.color(p2.getName() + " lost " + p2.getWager() + " chips!", p2.getColor()));
        p2.changeChips(-p2.getWager());
        if (p2.isDead()) {
            System.out.println(ConsoleUtility.color(p2.getName() + " is OUT!", p2.getColor()));
        }
        ConsoleUtility.wait(1.0);
        System.out.println(ConsoleUtility.color(p3.getName() + " lost " + p3.getWager() + " chips!", p3.getColor()));
        p3.changeChips(-p3.getWager());
        if (p3.isDead()) {
            System.out.println(ConsoleUtility.color(p3.getName() + " is OUT!", p3.getColor()));
        }
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
        } else if (banker.getScore() <= player.getScore()) {
            System.out.println(ConsoleUtility.color(player.getName() + " won " + player.getWager() + " chips!", player.getColor()));
            player.changeChips(player.getWager());
            banker.changeChips(-player.getWager());
            ConsoleUtility.wait(1.0);
        }
    }

    private void playerRolls() {
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
        } 
        if (p1.isDead()) {
            System.out.println(ConsoleUtility.color(p1.getName() + " is OUT!", p1.getColor()));
        }
        if (!p2.isDead()) {
            determinePlayerWin(p2);
        } 
        if (p2.isDead()) {
            System.out.println(ConsoleUtility.color(p2.getName() + " is OUT!", p2.getColor()));
        }
        if (!p3.isDead()) {
            determinePlayerWin(p3);
        } 
        if (p3.isDead()) {
            System.out.println(ConsoleUtility.color(p3.getName() + " is OUT!", p3.getColor()));
        }
        System.out.println();
        System.out.print("Press enter to continue");
        scan.nextLine();
    }

    private void determineGameWinner() {
        if ((p1.getChips() > p2.getChips()) && (p1.getChips() > p3.getChips())) {
            ConsoleUtility.clearScreen();
            System.out.println(ConsoleUtility.color("GAME OVER", "Red"));
            System.out.println(ConsoleUtility.color(p1.getName() + " wins!", p1.getColor()));
            highScore = p1.getName() + ": " + p1.getChips() + " chips";
        } else if ((p2.getChips() > p1.getChips()) && (p2.getChips() > p3.getChips())) {
            ConsoleUtility.clearScreen();
            System.out.println(ConsoleUtility.color("GAME OVER", "Red"));
            System.out.println(ConsoleUtility.color(p2.getName() + " wins!", p2.getColor()));
            highScore = p2.getName() + ": " + p2.getChips() + " chips";
        } else if ((p3.getChips() > p2.getChips()) && (p3.getChips() > p2.getChips())) {
            ConsoleUtility.clearScreen();
            System.out.println(ConsoleUtility.color("GAME OVER", "Red"));
            System.out.println(ConsoleUtility.color(p3.getName() + " wins!", p3.getColor()));
            highScore = p3.getName() + ": " + p3.getChips() + " chips";
        } else {
            System.out.println(ConsoleUtility.color("It was a tie!", "Purple"));
        }
    }

    private boolean isGameOver() {
        if ((p1.isDead() && p2.isDead() && p3.isDead()) || banker.isDead()) {
            return true;
        }
        return false;
    }
}
