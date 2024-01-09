public class Player {
    private String name;
    private int chips;
    private int wager;
    private Dice dice;
    private boolean dead;

    public Player(String name) {
        this.name = name;
        this.chips = 100;
        this.wager = 0;
        this.dead = false;
        this.dice = new Dice();
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
}
