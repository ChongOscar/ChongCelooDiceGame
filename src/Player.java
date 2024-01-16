public class Player {
    private String name;
    private int chips;
    private int wager;
    private Dice dice;
    private boolean dead;
    private int score;

    public Player(String name) {
        this.name = name;
        this.chips = 100;
        this.wager = 0;
        this.dead = false;
        this.dice = new Dice();
        this.score = -1;
    }

    public void setWager(int wager) {
        this.wager = wager;
    }

    public int getWager() {
        return wager;
    }

    public int[] rollDice() {
        dice.roll();
        return dice.getRolls();
    }

    public int[] getDiceRoll() {
        return dice.getRolls();
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public boolean isDead() {
        if (chips <= 0) {
            dead = true;
        }
        return dead;
    }

    public void printDice() {
        dice.printDice();
    }

    public void determineScore() {
        if (dice.getRolls()[0] == 1 && dice.getRolls()[1] == 2 && dice.getRolls()[2] == 3) {
            score = 0;
            return;
        }
        if (dice.getRolls()[0] == 4 && dice.getRolls()[1] == 5 && dice.getRolls()[2] == 6) {
            score = 7;
            return;
        }
        if (dice.getRolls()[0] == dice.getRolls()[1]) {
            score = dice.getRolls()[2];
            return;
        }
        if (dice.getRolls()[1] == dice.getRolls()[2]) {
            score = dice.getRolls()[0];
            return;
        }
        if (dice.getRolls()[0] == dice.getRolls()[2]) {
            score = dice.getRolls()[1];
            return;
        }
        score = -1;
    }
    public int getScore() {
        return score;
    }
}
