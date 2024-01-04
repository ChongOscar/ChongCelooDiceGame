public class Dice {
    private int[] rolls;

    public Dice() {
        rolls = new int[3];
    }

    public void roll() {
        rolls[0] = (int) (Math.random() * 6) + 1;
        rolls[1] = (int) (Math.random() * 6) + 1;
        rolls[2] = (int) (Math.random() * 6) + 1;
    }

    public int[] getRolls() {
        return rolls;
    }
}
