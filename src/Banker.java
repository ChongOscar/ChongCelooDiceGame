public class Banker {
    private int chips;
    private Dice dice;



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
    public void changeChips(int chips) {
        this.chips = this.chips + chips;
    }

    public boolean isDead() {
        if (chips <= 0) {
            return true;
        }
        return false;
    }
}
