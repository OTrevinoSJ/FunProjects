import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SaveGame {
    
    private int[][] board;
    private File file;
    private int player;
    private String fileName = "TicTacToeGames.json";

    public SaveGame(int[][] gameBoard, int player) {
        this.board = gameBoard;
        this.player = player;
        file = new File(fileName);
    }

    @SuppressWarnings("unchecked")
    public void saveState() {

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            // Global Array
            JSONArray allPlayers = new JSONArray();

            // A player object
            JSONObject playerObj = new JSONObject();

            // create the player key-value pair
            playerObj.put("playerWinner", this.player == 1 ? "O" : "X");

            //
            JSONObject boards = new JSONObject();
            JSONObject boardsCode = new JSONObject();
            JSONArray listOfBoardsString = new JSONArray();
            JSONArray listOfBoardsCode = new JSONArray();

            // Set up the arrays to use as values for keys
            listOfBoardsString.add(getBoardStateString());
            listOfBoardsCode.add(getBoardStateCode());

            // Adds the arrays to these keys 
            boards.put("boardsString", listOfBoardsString);
            boardsCode.put("boardsCode", listOfBoardsCode);
            
            // Create a collections object and add to player object
            playerObj.put("stringTable", boards);
            playerObj.put("codeTable", boardsCode);

            // adds to the global array
            allPlayers.add(playerObj);

            StringWriter out = new StringWriter();
            allPlayers.writeJSONString(out);
            bw.append(out.toString());

            bw.close();

            System.out.println("Saved!");
        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    private String getBoardStateString() {
        String state = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 2) {
                    state += "X";
                }else if (board[i][j] == 1) {
                    state += "O";
                } else {
                    state += "-";
                }
            }
        }
        return state;
    }

    private int getBoardStateCode() {
        String code = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 2) {
                    code += "2";
                }else if (board[i][j] == 1) {
                    code += "1";
                } else {
                    code += "0";
                }
            }
        }
        return Integer.parseInt(code);
    }

}
