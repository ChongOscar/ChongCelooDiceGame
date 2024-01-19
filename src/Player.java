public class Player {
    private String name;
    private String number;
    private String color;
    private int chips;
    private int wager;
    private Dice dice;
    private boolean dead;
    private int score;

    public Player(String name, String number) {
        this.name = name;
        this.number = number;
        this.chips = 100;
        this.wager = 0;
        this.dead = false;
        this.dice = new Dice();
        this.score = -1;
        if (number.equals("1")) {
            color = "Red";
        }
        if (number.equals("2")) {
            color = "Blue";
        }
        if (number.equals("3")) {
            color = "Yellow";
        }
    }

    public void setWager(int wager) {
        this.wager = wager;
    }

    public int getWager() {
        return wager;
    }

    public void rollDice() {
        dice.roll();
    }

    public int getChips() {
        return chips;
    }

    public void changeChips(int chips) {
        this.chips = this.chips + chips;
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
    public int getScore() {
        return dice.determineScore();
    }

    public String getNumber() {
        return  number;
    }
    public String getName() {
        return name;
    }
    public String getColor() {
        return color;
    }
}
