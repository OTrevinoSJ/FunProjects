import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class HangManGame {

    // Global (class) var
    static String word = "tarik"; // I could make a list of random words and choose one randomly to assign to this variable
    static int ATTEMPTS = 6;

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame(){

        // Initializations
        Scanner kb = new Scanner(System.in);
        char[] dashes = makeArrayDashes(word);
        ArrayList<String> wrongGuesses = new ArrayList<>();
        int guesses = -1;

        // Print first board (first)
        initialShow(dashes);

        //&& !isWordCompleted(dashes)
        while (!isWordCompleted(dashes)) {

            if (guesses >= ATTEMPTS - 1) break;

            // Prints the board to the player
            printBoard(guesses);

            // A wrongs title
            System.out.println(ConsoleUtilsColor.colorChange("red", "Wrongs: "));

            // Prints the wrong inputs
            printWrongs(wrongGuesses);
            
            // Get user input and also validates the input given to keep in range of the program
            System.out.print("\nEnter a letter: ");
            String letter = kb.nextLine();
            letter = validateLatter(letter, kb);
            System.out.println();
            
            // Update the array with a letter
            boolean updated = updateArray(dashes, letter);

            // Prints the array with dashes or letter filled in
            System.out.print(ConsoleUtilsColor.colorChange("green", "(Word): "));
            printArray(dashes);

            // // Tells the player if guess was right or wrong
            guesses = tellPlayer(updated, wrongGuesses, letter, guesses);
        }

        if (guesses == ATTEMPTS - 1) printBoard(guesses);
        else System.out.println(ConsoleUtilsColor.colorChange("green", "Well Done!"));

        // ask player for input

        // check if input letter is in word (trav. the array find indecies of letters and update dashes in the array)

        // look at word for the letters and there positions
            // loop through the word and grab each index of valid given letter

        // update the dashes with the letters at their positions in the word
        
        // if word matches filledInWord before attempts is maxed out
            // then player wins
        // else player loses

    }

    /*
     * This method makes the dashes
     */
    private static char[] makeArrayDashes(String word) {
        char[] arr = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            arr[i] = '-';
        }
        return arr;
    }

    /*
     * This method updates the array and also tells if the array was updated or not
     */
    private static boolean updateArray(char[] dashes, String letter) {
        boolean flag = false;
        for (int i = 0; i < dashes.length; i++) {
            if (String.valueOf(word.charAt(i)).equalsIgnoreCase(letter)) {
                dashes[i] = letter.charAt(0);
                flag = true;
            }
        }
        return flag;
    }

    /*
     * This method tells the player that the guess was right or wrong
     */
    private static int tellPlayer(boolean updated, ArrayList<String> wrongGuesses, String letter, int guesses){
        if (updated) {
            System.out.println(ConsoleUtilsColor.colorChange("green", "----Correct!----"));
        }
        else {
            if (isAlreadyUsed(wrongGuesses, letter)) {
                System.out.println(ConsoleUtilsColor.colorChange("red", "Already Guessed That Letter"));
            } else {
                System.out.println(ConsoleUtilsColor.colorChange("red", "----Wrong!----"));
                wrongGuesses.add(letter);
                guesses++;
            }
            
        }
        return guesses;
    }

    /*
     * This method prints the array of letters for player to see current correct letters
     */
    private static void printArray(char[] letters){
        for (int i = 0; i < letters.length; i++) {
            System.out.print(String.valueOf(letters[i]).toLowerCase());
        }
        System.out.println();
    }

    /*
     * This method returns a true or false is the guessed word is finished or not finished
     */
    private static boolean isWordCompleted(char[] letters) {
        String str = "";
        for (char letter : letters) {
            str += String.valueOf(letter);
        }
        return str.equalsIgnoreCase(word) ? true : false;
    }

    /*
     * This method is for validating the user input given
     */
    private static String validateLatter(String letter, Scanner kb){
        while ((letter.length() <= 0 || letter.length() > 1) || (letter.charAt(0) < 65 || letter.charAt(0) > 122)) {
            System.out.println(ConsoleUtilsColor.colorChange("red", "You entered one or more letter or not a letter at all! Try again?"));
            letter = kb.nextLine();
        }
        return letter;
    }

    /*
     * This prints the wrongs array to the user
     */
    private static void printWrongs(ArrayList<String> list){

        System.out.print("[");
        String lettersStr = "";
        for (String letter : list) {
            lettersStr += letter + " ";
        }
        System.out.println(lettersStr.trim() + "]");
    }

    /*
     * This method is for the initial screening of the game
     */
    private static void initialShow(char[] dashes){
        System.out.print("\n" + ConsoleUtilsColor.colorChange("green", "(Word): "));
        printArray(dashes);
        System.out.println(ConsoleUtilsColor.colorChange("yellow", "(" + word.length() + " letters)"));
    }

    /*
     * This method lets the program know if the letter the user uses was already guessed
     */
    private static boolean isAlreadyUsed(ArrayList<String> list, String letter) {
        return list.contains(letter);
    }

    /*
     * This prints the game board with the player
     */
    private static void printBoard(int attempt) {

        if (attempt == -1) {
            System.out.println("///////////////\r\n" + //
            "|\t|\r\n" + //
            "|\t\r\n" + //
            "|\r\n" + //
            "|\t\r\n" + //
            "|\t\r\n" + //
            "===============");
        } else {
            File file = new File("hangmanStages.txt");
            ArrayList<String> stages = new ArrayList<>();

            try {
                String line;
                String currentStage = "";
                BufferedReader br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    currentStage += line + "\n";
                    if (currentStage.contains(",")) {
                        stages.add(currentStage.trim());
                        currentStage = "";
                    }
                }

                for (int i = 0; i < stages.size(); i++) {
                    stages.set(i, stages.get(i).substring(i, stages.get(i).length() - 1));
                }

                if (attempt == 5) {
                    System.out.println(ConsoleUtilsColor.colorChange("red", stages.get(attempt)));
                } else {
                    System.out.println(stages.get(attempt));
                }
                

            } catch (Exception e) {
            }
        }

        
        
        
    }


}