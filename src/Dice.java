public class Dice {
    private int[] rolls;

    public Dice() {
        rolls = new int[3];
    }

    public void roll() {
        rolls[0] = (int) (Math.random() * 6) + 1;
        rolls[1] = (int) (Math.random() * 6) + 1;
        rolls[2] = (int) (Math.random() * 6) + 1;

    //    rolls[0] = 6;
    //    rolls[1] = 2;
    //    rolls[2] = 5;
    }

    public void printDice() {
        ConsoleUtility.wait(.5);
        System.out.println("First roll: " + rolls[0]);
        ConsoleUtility.wait(.5);
        System.out.println("Second roll: " + rolls[1]);
        ConsoleUtility.wait(.5);
        System.out.println("Third roll: " + rolls[2]);
        ConsoleUtility.wait(.5);
    }
    public int determineScore() {
        String strRolls = "" + rolls[0] + rolls[1] + rolls[2];
        if (strRolls.contains("4") && strRolls.contains("5") && strRolls.contains("6")) {
            return 7;
        }
        if (strRolls.contains("1") && strRolls.contains("2") && strRolls.contains("3")) {
            return 0;
        }
        if (rolls[0] == rolls[1]) {
            return rolls[2];
        }
        if (rolls[1] == rolls[2]) {
            return rolls[0];
        }
        if (rolls[0] == rolls[2]) {
            return rolls[1];
        }
        return -1;
    }
}
