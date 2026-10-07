/* GameRunner.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.5-Blackjack (due 10/23/08)
 * This is the game runner class.
 */

import java.util.Scanner;

public class GameRunner {

	public static void main(String[] args) {
		
		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);
		
		// Display welcome message and print name query
		System.out.println("Welcome to Bane's House of Blackjack!");
		System.out.println("What is your name?");
		
		// Initialize the players and the game deck
		Player dealer = new Player();
		Player humanPlayer = new Player(keyboard.nextLine());
		Deck gameDeck = new Deck(true);
		
		// Declare variables we will need to use and set default values
		String userInput = "";
		boolean humanPlayerHits = true;
		boolean dealerWins = true;
		boolean humanPlayerWins = true;
		boolean dealerStopsHitting = false;
		boolean humanPlayerStopsHitting = false;
		boolean printDividingLines = false;
		
		// The game runner loop contains everything we will need to run the game;
		// we will stop the loop either when i reaches 6 or when both players stop hitting
		for(int i = 0; i <= dealer.getMaxCardsInHand() && (dealerStopsHitting == false || humanPlayerStopsHitting == false); i++){
			
			// Dealer only hits if the sum of his hand is less than 17
			if(dealer.getSumOfHand() < 17){
				dealer.addCard(gameDeck.dealNextCard());
			}
			else{
				dealerStopsHitting = true;
			}
			
			// The human player isn't asked to hit until he is dealt two cards (i.e. two iterations of the loop)
			if(i <= 1){
				humanPlayer.addCard(gameDeck.dealNextCard());
			}
			else{
				
				// Print the players' hands only if at least one player is still hitting
				if(humanPlayerStopsHitting == false || dealerStopsHitting == false){
					// Print the dealer's hand
					System.out.println("Dealer's hand: ");
					dealer.printHand();
					
					// Print the human player's hand
					System.out.println("\n" + humanPlayer.getPlayerName() + "'s hand: ");
					humanPlayer.printHand();
					System.out.println("\n" + humanPlayer.getPlayerName() + "'s score: " + humanPlayer.getSumOfHand());
					
					// Print the dividing lines at the end of the current loop iteration
					printDividingLines = true;
				}
				else{
					printDividingLines = false;
				}
				
				// Only ask the player if he/she wants to hit if the player has previously hit (or hasn't hit yet) and if 
				// the sum of the cards in the player's hand is less than or equal to 21
				if(humanPlayerHits == true && humanPlayer.getSumOfHand() <= 21){
					
					// Ask the human player if he/she wants to hit and validate the input (only y, n, yes, or no are accepted)
					System.out.println("Do you want to hit?");
					userInput = keyboard.nextLine();
					humanPlayerHits = Validation.checkIfYesorNo(userInput, "Please enter either \"yes\" or \"no\".");
					
					// If the player chooses to hit, add another card to the player's hand
					if(humanPlayerHits == true){
						humanPlayer.addCard(gameDeck.dealNextCard());
					}
					else{
						humanPlayerStopsHitting = true;
					}
				}
				else{
					humanPlayerStopsHitting = true;
				}
			}
			
			// Print the dividing lines only at the end of the second iteration of the loop or if printDividingLines is true
			if(printDividingLines || i == 1){
				System.out.println("\n----------------------\n");
			}
		}
		
		// Display the scores of the dealer and the human player
		System.out.println("Dealer's score: " + dealer.getSumOfHand());
		System.out.println(humanPlayer.getPlayerName() + "'s score: " + humanPlayer.getSumOfHand() + "\n");
		
		/* The series of if/else statements below checks to see who wins the game and prints an appropriate message */
		
		// If either player busts he/she loses
		if(dealer.getSumOfHand() > 21){
			dealerWins = false;
		}
		else{
			dealerWins = true;
		}
		if(humanPlayer.getSumOfHand() > 21){
			humanPlayerWins = false;
		}
		else{
			humanPlayerWins = true;
		}
		
		// The dealer wins if he hasn't busted and if his score is higher than that of the human player
		if(dealer.getSumOfHand() > humanPlayer.getSumOfHand() && dealerWins != false){
			dealerWins = true;
			humanPlayerWins = false;
			System.out.println("The dealer wins the game.");
		}
		// The human player wins if he/she hasn't busted and if his/her score is higher than that of the dealer
		else if(humanPlayer.getSumOfHand() > dealer.getSumOfHand() && humanPlayerWins != false){
			humanPlayerWins = true;
			dealerWins = false;
			System.out.println(humanPlayer.getPlayerName() + " wins the game.");
		}
		// If both players have busted, then print an appropriate message
		else if(dealerWins == false && humanPlayerWins == false){
			System.out.println("Nobody wins the game.");
		}
		// If the sums of the hands of the players are equal, then neither player wins the game
		else if(dealer.getSumOfHand() == humanPlayer.getSumOfHand()){
			dealerWins = false;
			humanPlayerWins = false;
			System.out.println("Nobody wins the game.");
		}
		// If the human player busted but the dealer didn't, then the dealer wins
		else if(dealer.getSumOfHand() <= 21 && humanPlayerWins == false){
			dealerWins = true;
			System.out.println("The dealer wins the game.");
		}
		// If the dealer busted but the human player didn't, then the human player wins
		else if(humanPlayer.getSumOfHand() <= 21 && dealerWins == false){
			humanPlayerWins = true;
			System.out.println(humanPlayer.getPlayerName() + " wins the game.");
		}
		
		System.out.println("\n----------------------\n\nGAME OVER");
	}
}
