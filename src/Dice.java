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

    public void printDice() {
        ConsoleUtility.wait(.5);
        System.out.println("First roll: " + rolls[0]);
        ConsoleUtility.wait(.5);
        System.out.println("Second roll: " + rolls[1]);
        ConsoleUtility.wait(.5);
        System.out.println("Third roll: " + rolls[2]);
    }
}
