import java.util.HashMap;

public class ConsoleUtilsColor {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private ConsoleUtilsColor(){};

    public static String colorChange(String color, String charSequence){
        HashMap<String, String> colorMapper = new HashMap<>();
        colorMapper.put("black", ANSI_BLACK + charSequence);
        colorMapper.put("red", ANSI_RED + charSequence);
        colorMapper.put("green", ANSI_GREEN + charSequence);
        colorMapper.put("yellow", ANSI_YELLOW + charSequence);
        colorMapper.put("blue", ANSI_BLUE + charSequence);
        colorMapper.put("purple", ANSI_PURPLE + charSequence);
        colorMapper.put("cyan", ANSI_CYAN + charSequence);
        colorMapper.put("white", ANSI_WHITE + charSequence);
        
        return colorMapper.getOrDefault(color.toLowerCase(), ANSI_WHITE + charSequence) + ANSI_RESET;
    }
}
