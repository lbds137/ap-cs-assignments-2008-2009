/*
 * TicTacToeStatejava
 * This class is the game-specific implementation of the GameStateI interface. You will implement the required methods.
 * 
 * Drausin Wulsin
 * AP Computer Science AB
 * Assignment 3.4
 * Landon School, 2009
 * 
 * Modified by: Vladlena Costescu
 */

import java.io.PrintStream;
import java.util.ArrayList;

public class TicTacToeState implements GameStateI {
	
	// Protected fields
	protected int curPlayer;
	/*
	 * curPlayer = -1 represents X
	 * curPlayer = 1 represents O
	 */
	protected int numRows = 3;
	protected int numCols = numRows;
	
	protected int[][] board = new int[numRows][numCols];
	
	// Constructors
	public TicTacToeState(){}
	
	public TicTacToeState(int aPlayer){
		curPlayer = aPlayer;
	}
	
	public TicTacToeState(int aPlayer, int[][] aBoard){
		curPlayer = aPlayer;
		board = aBoard;
	}
	
	// Gets the current game state's game board
	public int[][] getBoard() {
		return board;
	}
	
	/*
	 * display(PrintStream) prints the GameState to a specified output
	 */
	public void display(PrintStream output){
		String curLine;
		char letter = 'a';
		
		// Handles the top row, i.e. the column legend row
		String topRow = " ";
		for(int i = 1; i <= numRows; i++){
			topRow += "   " + i;
		}
		output.println(topRow + " \n");
		
		// Handles the length of the lines between rows
		String linesRow = "   ---";
		for(int i = 1; i < numCols; i++){
			linesRow += "+---";
		}
		
		for(int row = 0; row < numRows; row++){
			curLine = "";
			
			// Populate curLine with the current non-spacer row
			for(int col = 0; col < numCols; col++){
				// At the beginning of the row, add the row letter and some space
				if(col == 0){
					curLine += letter + "   ";
				}
				// Between columns, add a vertical line and a space
				else{
					curLine += "| ";
				}
				curLine += stringSymbol(board[row][col]) + " ";
			}
			
			output.println(curLine);
			
			// After every row except the last one, print linesRow
			if(row != numRows - 1){
				output.println(linesRow);
			}
			
			letter++;
		}
	}
		
	/* hasWinner() returns a non-zero number corresponding to the player number
	 * (could be -1 and 1 for a 2 player game) if that player has won the game;
	 * otherwise, hasWinner() returns 0 for no current winner 
	*/
	public int hasWinner(){
		int numInSeq = 0;
		int player = curPlayer;
		
		// Check each player for winner status
		for(int i = 0; i < 2; i++){
			// Check the rows
			for(int row = 0; row < numRows; row++){
				for(int col = 0; col < numCols; col++){
					if(board[row][col] == player){
						numInSeq++;
					}
				}
				if(numInSeq == numCols){
					return player;
				}
				else{
					numInSeq = 0;
				}
			}
			// Check the columns
			for(int col = 0; col < numCols; col++){
				for(int row = 0; row < numRows; row++){
					if(board[row][col] == player){
						numInSeq++;
					}
				}
				if(numInSeq == numRows){
					return player;
				}
				else{
					numInSeq = 0;
				}
			}
			// Check the diagonal starting at 0
			for(int row = 0, col = 0; row < numRows && col < numCols; row++, col++){	
				if(board[row][col] == player){
					numInSeq++;
				}
				if(numInSeq == numRows){
					return player;
				}
			}
			numInSeq = 0;
			
			// Check the other diagonal
			for(int row = numRows - 1, col = 0; row >= 0 && col < numCols; row--, col++){	
				if(board[row][col] == player){
					numInSeq++;
				}
				
				if(numInSeq == numRows){
					return player;
				}
			}
			numInSeq = 0;
			
			// Test the other player in the next (and last) iteration of the loop
			player *= -1;
		}
		// If nothing is returned in the course of the loop, then there is no winner, so return zero
		return 0;
		
	}
	
	/*
	 * isDraw() returns true if the game is a draw and false otherwise
	 */
	public boolean isDraw(){
		// There is a draw if and only if there is no winner and there are no available moves
		return hasWinner() == 0 && !hasMove();
	}
	
	
	/*
	 * getMoves(int) returns all of the legal moves of a certain player for the 
	 * given GameState as an ArrayList of Objects, which may be Integers or some
	 * other way of representing the move
	 */
	public ArrayList<Integer> getPossibleMoves(int Player){
		ArrayList<Integer> arList = new ArrayList<Integer>();
		
		// Loop through the rows
		for(int row = 0; row < numRows; row++){
			// Loop through the columns
			for(int col = 0; col < numCols; col++){
				// If the value of the board at the specified row and column indices is zero, 
				// that location specifies a possible move
				if(board[row][col] == 0){
					arList.add(row * numCols + col);
				}
			}
		}
		
		return arList;
	}
	
	
	/*
	 * makeChild(int) creates a new GameState from the current GameState 
	 * with a given move
	 * 
	 * Precondition: move is a valid move
	 */
	public GameStateI makeChild(int move){
		// Determine the row and column indices
		int rowIndex = move / numCols;
		int colIndex = move % numCols;
		
		int newBoard[][] = new int[numRows][numCols];
		// Loop through the rows
		for(int row = 0; row < numRows; row++){
			// Loop through the columns
			for(int col = 0; col < numCols; col++){
				// Clone the board
				newBoard[row][col] = board[row][col];
			}
		}
		
		// Perform the specified move on newBoard
		newBoard[rowIndex][colIndex] = curPlayer;
		
		return new TicTacToeState(getNextPlayer(), newBoard);
	}
	
	/*
	 * makeChild(String) creates a new GameState from the current GameState 
	 * with a given move
	 * 
	 * Precondition: move is a valid move
	 */
	public GameStateI makeChild(String move){
		return makeChild(moveToInt(move));
	}
	
	
	/*
	 * isValidMove(int) tests whether a certain move is valid given the current 
	 * GameState
	 */
	public boolean isValidMove(int move){
		// Determine the row and column indices
		int rowIndex = move / numCols;
		int colIndex = move % numCols;
		
		// Return true if the specified board location is not populated by -1 or 1 (i.e. an X or an O)
		return board[rowIndex][colIndex] == 0;
	}
	
	/*
	 * isValidMove(String) tests whether a certain move is valid given the current 
	 * GameState
	 */
	public boolean isValidMove(String move){
		return isValidMove(moveToInt(move));
	}
	
	/*
	 * moveToInt(String) takes a string representation of a move and converts it to an 
	 * int representation
	 */
	public int moveToInt(String move){
		int rowIndex;
		int colIndex;
		int intMove;
		char rowLetter = move.charAt(0);
		
		// Determine the row and column indices
		rowIndex = rowLetter - 'a';
		colIndex = Integer.parseInt(move.substring(1)) - 1;
		
		// Determine the integer that corresponds to the row and column indices (0-8)
		intMove = rowIndex * numCols + colIndex;
		
		return intMove;
	}
	
	/*
	 * moveToString(int) takes an int representation of a move and converts it to a 
	 * String representation
	 */
	public String moveToString(int move){
		// Determine the row and column indices
		int rowIndex = move / numCols;
		int colIndex = move % numCols;
		
		// Create the move string
		String strMove = "";
		strMove += ((char) ('a' + rowIndex)) + "" + (colIndex + 1);
		
		return strMove;
	}
	
	
	/*
	 * getNextPlayer() returns the int value for the next player to play
	 */
	public int getNextPlayer(){
		return curPlayer * -1;
	}
	
	/*
	 * printScore() prints the current score of the game to the specified output; 
	 * this method is intended to be overridden in a sub class if desired, otherwise 
	 * it does nothing
	 */
	public void printScore(PrintStream output){
		//Score is not a feature of tic-tac-toe
	}
	
	
	/*
	 * hasMove() returns true if the current player can move on the current game state; 
	 * otherwise, returns false; this method is intended to be overridden if necessary
	 * in a sub class
	 */
	public boolean hasMove(){
		return getPossibleMoves(curPlayer).size() > 0;
	}
	// Determines which symbol to display for a given integer representing a player
	private String stringSymbol(int player){
		String symbol = "";
		
		if(player == -1){
			symbol = "X";
		}
		else if(player == 1){
			symbol = "O";
		}
		else{
			symbol = " ";
		}
		
		return symbol;
	}
}
