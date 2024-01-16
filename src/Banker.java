public class Banker {
    private int chips;
    private Dice dice;


    public Banker() {
        this.chips = 1000;
        this.dice = new Dice();
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

    public void printDice() {
        dice.printDice();
    }
}
