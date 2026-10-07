/*
 * TicTacToeNodejava
 * This class both extends TicTacToeState and implements GameNodeI. You will implement the required methods.
 * 
 * Drausin Wulsin
 * AP Computer Science AB
 * Assignment 3.4
 * Landon School, 2009
 * 
 * Modified by: Vladlena Costescu
 */
 
 
import java.util.ArrayList;

public class TicTacToeNode extends TicTacToeState implements GameNodeI {
	
	// Protected fields
	protected int prevMove = -1;
	protected int bestMove = -1;
	protected ArrayList<TicTacToeNode> children = new ArrayList<TicTacToeNode>();
	
	// Constructors
	public TicTacToeNode(int aPlayer, int aPrevMove){
		curPlayer = aPlayer;
		prevMove = aPrevMove;
	}
	
	public TicTacToeNode(int aPlayer, int aPrevMove, int[][] theBoard){
		curPlayer = aPlayer;
		prevMove = aPrevMove;
		
		board = theBoard;
	}
	
	/*
	 * getBestMove() returns the best move of the children for the node
	 */
	public int getBestMove(){
		return bestMove;
	}
	
	/*
	 * getPreMove() returns the move that led to the current node
	 */
	public int getPrevMove(){
		return prevMove;
	}
	
	/*
	 * getHeurVal(int, int) is a recursive method that traverses the game tree down
	 * to a specified depth (stops when depth == 0) for a specific player
	 */
	public int getHeurVal(int depth, int rootPlayer){
		// The non-base case is that the current node is not a leaf
		if(depth > 0 && hasWinner() == 0 && !isDraw()){
			ArrayList<Integer> heurVals = new ArrayList<Integer>();
			int highestHeurVal;
			
			makeChildren();
			
			// Populate heurVals
			for(TicTacToeNode child : children){
				heurVals.add(child.getHeurVal(depth - 1, rootPlayer));
			}
			
			// Set the initial highestHeurVal to which to compare the rest of the heuristic values
			highestHeurVal = heurVals.get(0);
			bestMove = children.get(0).getPrevMove();
			
			// Determine the highest heuristic value
			for(int i = 1; i < heurVals.size(); i++){
				// If it is the root player's turn, maximize the heuristic values of the children
				if(rootPlayer == curPlayer){
					if(heurVals.get(i) >= highestHeurVal){
						highestHeurVal = heurVals.get(i);
						bestMove = children.get(i).getPrevMove();
					}
				}
				// If it is not the root player's turn, minimize the heuristic values of the children
				else{
					if(heurVals.get(i) <= highestHeurVal){
						highestHeurVal = heurVals.get(i);
						bestMove = children.get(i).getPrevMove();
					}
				}
			}
			
			return highestHeurVal;
		}
		// If the current node is a leaf, just evaluate its heuristic value by calling getGameNodeHeurVal
		else{
			return getGameNodeHeurVal(rootPlayer);
		}
	}
	
	
	/*
	 * getGameStateHeurVal() returns the heuristic value of the current GameState
	 * for the current player
	 */
	public int getGameNodeHeurVal(int rootPlayer){
		// Return a positive heuristic value if the rootPlayer is the winner in the current game state
		if(hasWinner() == rootPlayer){
			return 10;
		}
		// Return a negative heuristic value if the rootPlayer is the loser in the current game state
		else if(hasWinner() == rootPlayer * -1){
			return -10;
		}
		// Return zero if there is a draw and / or there is no winner
		else{
			return 0;
		}
		
	}
	
	
	/*
	 * makeChildren() generates the children GameStates for all possible moves
	 * as returned by getPossibleMoves(int) 
	 */
	public void makeChildren(){
		makeChildren(getPossibleMoves(curPlayer));
	}
	
	
	/*
	 * makeChildren(ArrayList<Objects>) generates the children GameStates for
	 * a given list of moves  
	 */
	public void makeChildren(ArrayList<Integer> moves){
		for(int i = 0; i < moves.size(); i++){
			children.add((TicTacToeNode) makeChild(moves.get(i)));
		}
	}
	
	/*
	 * makeChild(int) creates a new GameState from the current GameState 
	 * with a given move
	 * 
	 * Precondition: move is a valid move
	 */
	public GameNodeI makeChild(int move){
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
			
		return new TicTacToeNode(getNextPlayer(), move, newBoard);
	}
}
