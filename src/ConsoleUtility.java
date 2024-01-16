import java.util.Map;
import java.util.HashMap;
public class ConsoleUtility {
    public static Map<String, String> colors = new HashMap<>();
    static {
        colors.put("Black", "\033[0;30m");
        colors.put("Red", "\033[0;31m");
        colors.put("Green", "\033[0;32m");
        colors.put("Yellow", "\033[0;33m");
        colors.put("Blue", "\033[0;34m");
        colors.put("Purple", "\033[0;35m");
        colors.put("Cyan", "\033[0;36m");
        colors.put("White", "\033[0;37m");

    }
    public static final String RESET = "\033[0m";      // Reset

    // CLEARSCREEN ONLY WORKS IN TERMINAL, NOT IN INTELLIJ'S RUN CONSOLE
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String color(String str, String color) {
        return colors.get(color) + str + RESET;
    }

    public static void wait(double seconds) {
        try {
            Thread.sleep((int) (seconds * 1000));
        } catch (Exception e) {
            System.out.println("error");
        }

    }
}
