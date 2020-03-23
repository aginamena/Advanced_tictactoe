import java.util.*;
/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game.
 * 
 * @author Lynn Marshall
 * @version November 8, 2012
 * 
 * @author Agina Oghenemena Benaiah
 * @version March 23, 2019
 */

public abstract class TicTacToe
{
    public static final String PLAYER_X = "X"; // player using "X"
    public static final String PLAYER_O = "O"; // player using "O"
    public static final String EMPTY = " ";  // empty cell
    public static final String TIE = "T"; // game ended in a tie

    private String player;   // current player (PLAYER_X or PLAYER_O)

    private String winner;   // winner: PLAYER_X, PLAYER_O, TIE, EMPTY = in progress

    private int numFreeSquares; // number of squares still free

    private String board[][]; // 3x3 array representing the board

    /** 
     * Constructs a new Tic-Tac-Toe board.
     */
    public TicTacToe()
    {
        board = new String[3][3];
        clearBoard();
        
    }

    /**
     * Sets everything up for a new game.  Marks all squares in the Tic Tac Toe board as empty,
     * and indicates no winner yet, 9 free squares and the current player is player X.
     */
    private void clearBoard()
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
        winner = EMPTY;
        numFreeSquares = 9;
        player = PLAYER_X;     // Player X always has the first turn.
    }

    /**
     * Returns true if filling the given square gives us a winner, and false
     * otherwise.
     *
     * @param int row of square just set
     * @param int col of square just set
     * 
     * @return true if we have a winner, false otherwise
     */
    private boolean haveWinner(int row, int col) 
    {
        // unless at least 5 squares have been filled, we don't need to go any further
        // (the earliest we can have a winner is after player X's 3rd move).

        if (numFreeSquares>4) return false;

        // Note: We don't need to check all rows, columns, and diagonals, only those
        // that contain the latest filled square.  We know that we have a winner 
        // if all 3 squares are the same, as they can't all be blank (as the latest
        // filled square is one of them).

        // check row "row"
        if ( board[row][0].equals(board[row][1]) &&
        board[row][0].equals(board[row][2]) ) return true;

        // check column "col"
        if ( board[0][col].equals(board[1][col]) &&
        board[0][col].equals(board[2][col]) ) return true;

        // if row=col check one diagonal
        if (row==col)
            if ( board[0][0].equals(board[1][1]) &&
            board[0][0].equals(board[2][2]) ) return true;

        // if row=2-col check other diagonal
        if (row==2-col)
            if ( board[0][2].equals(board[1][1]) &&
            board[0][2].equals(board[2][0]) ) return true;

        // no winner yet
        return false;
    }

    /**
     * Prints the board to standard out using toString().
     */
    public void print() 
    {
        // something needs to be added here
        System.out.println(this);
        //System.out.println(board);
    }

    /**
     * Returns a string representing the current state of the game.  This should look like
     * a regular tic tac toe board, and be followed by a message if the game is over that says
     * who won (or indicates a tie).
     *
     * @return String representing the tic tac toe game state
     */
    public String toString() 
    {
        String t  = "";
        boolean winner = false;
        String s = "";
        int j;
        boolean v;
        for(int i = 0; i<board.length; i++){

            for( j = 0; j <board.length; j++){

                v = board[i][j].equals("X") || board[i][j].equals("O");
                if(!v && j+1== board.length ){
                    t += "     ";
                    //t +="---------------\n"; //actually one of these gets executed
                }
                else if(!v && j+1!= board.length ){
                    t += "     |";

                }
                else if(v && j+1== board.length ){
                    t += "  "+ board[i][j];
                    //t +="---------------\n";//actually one of these gets executed
                }
                else if(v && j+1!= board.length ){
                    t +="  "+ board[i][j] + "  |";
                }

                winner = haveWinner(i,j);
                if(winner && i == 2){
                    t +="\n"+ board[i][j] + " wins";
                    winner = false;
                }

            }

            if(i+1 !=board.length){
                t += "\n----------------\n";
            }

        }

        return t; // this needs to be updated
    }

    /**
     * gets the current player
     * 
     * @return Returns the current player playing the game
     */
    public String getPlayer(){
        return player;
    }

    /**
     * Plays the tictactoe game with the row and column specified
     * 
     * @param row the row we want to click
     * @param col the column we want to click
     */
    public void playGame2(int row, int col){

        if (row>=0 && row<=2 && col>=0 && col<=2 && board[row][col]==EMPTY
        && winner == EMPTY){
            board[row][col] = player;        // fill in the square with player
            numFreeSquares--;            // decrement number of free squares

            // see if the game is over
            if (haveWinner(row,col)) 
                winner = player; // must be the player who just went
            else if (numFreeSquares==0) 
                winner = TIE; // board is full so it's a tie

            // change to other player (this won't do anything if game has ended)
            if (player==PLAYER_X){ 
                player=PLAYER_O;

            }
            else {
                player=PLAYER_X;

            }

        }

    }

    /**
     * check to see if there's a winner
     * 
     * @return returns ture if there's a winner else false otherwise
     */
    public boolean check(){

        return winner == EMPTY;

    }

    /**
     * check to see if there's a tie
     * 
     * @return returns true is there's a tie else false otherwise
     */

    public boolean checkTie(){
        return numFreeSquares == 0;
    }
    
    /**
     * clear the game board
     */
    public void clearScreen(){
        clearBoard();
    }

    /**
     * check to see who won in the game
     * 
     * @return returns the winner of each round. It's either X or O or T(tie)
     */
    public String  whoWon(){
        if(!check()){
            return winner;
        }
        return null;
    }
    
    /**
     * gets the winner for each round
     * 
     * @return returns the winner (X or O or T)
     */
    public String getWinner(){
        return winner;
    }
    
    /**
     * gets the number of free squres 
     * 
     * @return returns the number of unused square in the gamme
     */
    public int getNumFreeSquares(){
        
        return numFreeSquares;
    }
    
}

