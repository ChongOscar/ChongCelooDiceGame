public class Banker {
    private int chips;
    private Dice dice;

    private int score;


    public Banker() {
        this.chips = 1000;
        this.dice = new Dice();
    }
    public void rollDice() {
        dice.roll();
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public void printDice() {
        dice.printDice();
    }
    public int getScore() {
        return dice.determineScore();
    }
}
