import java.util.Scanner;

public class TicTac {

    private static int[][] board = new int[][]
    {
        {0,0,0},
        {0,0,0},
        {0,0,0}
    };

    public static void main(String[] args) throws InterruptedException{

        // Open a scanner for user input
        Scanner kb = new Scanner(System.in);

        // Prints the initial board
        printBoardLayout();

        // Makes a fake waiter
        fakeWaitTimer();
        
        // Displays the board
        displayBoard();

        // [Control logic]

        // This helps switch between player one and player two
        boolean toggle = true;
        int currentPlayer = -1;

        // This holds the winner flag
        boolean winner = false;

        // A loop that continues until board is full
        while (!isBoardFull()) {
            // toggle between players
            if (toggle) {
                toggle = false;
                currentPlayer = 1;
            }
            else {
                toggle = true;
                currentPlayer = 2;
            }

            // somehow place the piece at postion
            placeMarker(kb, currentPlayer);

            // displays board
            displayBoard();

            // checks for winner and breaks out when there is one
            if (checkWinner(currentPlayer)) {
                winner = true;
                break;
            };
        }

        // Announces the winner if there is one
        announceWinner(winner, currentPlayer);
        
        
    }

    /*
     * This method is in charge of place a marker on the board
     */
    private static void placeMarker(Scanner kb, int currentPlayer){

        // if place of placement is 0 then place; else ask again

        int row = 0;
        int col = 0;

        if (currentPlayer == 1) {
            System.out.print("Player O's Turn: ");
        } else {
            System.out.print("Player X's Turn: ");
        }
        

        switch (kb.next().toLowerCase()) {
            case "tl":
                row = 0;
                col = 0;
                break;
            case "tm":
                row = 0;
                col = 1;
                break;
            case "tr":
                row = 0;
                col = 2;
                break;
            case "ml":
                row = 1;
                col = 0;
                break;
            case "mm":
                row = 1;
                col = 1;
                break;
            case "mr":
                row = 1;
                col = 2;
                break;
            case "bl":
                row = 2;
                col = 0;
                break;
            case "bm":
                row = 2;
                col = 1;
                break;
            case "br":
                row = 2;
                col = 2;
                break;
            default:
                row = -1;
                col = -1;
                break;
        }
        if (row == -1 && col == -1) {
            System.out.println("You did not enter a valid location");
            placeMarker(kb, currentPlayer);
        }
        else if (board[row][col] != 0) {
            System.out.println("You can not place at that postion. The spot is taken already. Try again");
            placeMarker(kb, currentPlayer);
        }
        else {
            board[row][col] = currentPlayer;
        }

    }

    /*
     * This method prints the board layout
     */
    private static void printBoardLayout(){
        System.out.println("\nHere are the placement symbols\nType one as input for example, when it asks for a locaton just type lm or mr etc. Good Luck! \n\n  Layout:");
        System.out.println("\t tl | tm | tr \n\t -------------- \n\t ml | mm | mr  (Choose one spot w/ these markers) \n\t -------------- \n\t bl | bm | br ");
    }

    /*
     * This method is for aesthetic purposes only. This method has no real effect on the game as a whole.
     */
    private static void fakeWaitTimer() {
        System.out.println("Game will start in 3 seconds...");
        try {
            Thread.sleep(3*1000);
        } catch (Exception e) {
        }
        System.out.println("Go!\n");

    }
    
    /*
     * This method checks for the winner
     */
    private static boolean checkWinner(int player){

        // Self reflection about code steps
        // Winning conditions
        // three across row (top,mid,bottom)
        // three across col (left,mid,right)
        // X (top-left, bottom-right) & (top-right, bottom-left)


        // Checks all rows top to bottom
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == player && board[i][j] == player && board[i][j] == player) {
                    counter++;
                }
            }
            if (counter == 3) return true;
            counter = 0;
        }

        counter = 0;
        // Checks all columns left to right
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[j][i] == player && board[j][i] == player && board[j][i] == player) {
                    counter++;
                }
            }
            if (counter == 3) return true;
            counter = 0;
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        else if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        };

        // If this line reaches then no winner is found
        return false;
    }

    /*
     * This method checks to see if the board is full
     */
    private static boolean isBoardFull(){
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1 || board[i][j] == 2) count++;
            }
        }
        return count == 9 ? true : false;
    }

    /*
     * This method announces the winner of the game at the end of the game
     */
    private static void announceWinner(boolean winner, int currentPlayer){
        if (winner) {
            System.out.println("Congratulation, Player " + currentPlayer + " you won!");
        } else {
            System.out.println("OOPS, no one won, welp");
        }
    }

    /*
     * This method displays the current board
     */
    private static void displayBoard() {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 2) {
                    System.out.print(" X ");
                } else if (board[i][j] == 1) {
                    System.out.print(" O ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


}