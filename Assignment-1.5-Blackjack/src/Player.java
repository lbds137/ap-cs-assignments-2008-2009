/* Player.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.5-Blackjack (due 10/23/08)
 * This is the player class.
 */

public class Player {
	private int maxCardsInHand = 5;
	private Card[] hand = new Card[maxCardsInHand];
	private String playerName = "Dealer";
	private boolean isDealer = true;
	
	// If no name is provided we assume that the player is the dealer (because of the above default variable values)
	public Player(){
		
	}
	// If a name is provided we change the player name from "Dealer" to whatever the name is and set our isDealer
	// boolean to false
	public Player(String inputPlayerName){
		playerName = inputPlayerName;
		isDealer = false;
	}
	// Method to add a card to a player's hand
	public void addCard(Card addedCard) {
		// A card cannot be added if there aren't any empty slots in the hand
		if(numberOfNulls() == 0){
			System.out.println("Hand is full.");
		}
		else{
			// Loop through to find the first empty slot in the hand and add the card to that slot; after a card is
			// added, end the loop by setting i to 5
			for(int i = 0; i < maxCardsInHand; i++){	
				if(hand[i] == null){
					hand[i] = addedCard;
					i = 5;
				}
			}
		}
	}
	// Method to calculate the sum of a player's hand
	public int getSumOfHand(){
		int sumOfHand = 0;
		
		for(int i = 0; i < getActualNumberOfCardsInHand(); i++){
			// We'll treat aces as 11 initially
			if(hand[i].getNumber() == 1){
				sumOfHand += 11;
			}
			// We count all face cards as 10
			else if(hand[i].getNumber() > 10){
				sumOfHand += 10;
			}
			// All other cards are just their values
			else{
				sumOfHand += hand[i].getNumber();
			}
		}
		// If sumOfHand is greater than 21 we want to test whether there are any aces in the hand, so we can treat them
		// as 1's and thus reduce the sum of the hand
		if(sumOfHand > 21){
			sumOfHand = sumHandleAces(sumOfHand);
		}
		return sumOfHand;
	}
	// Private method to handle aces; if aces are found and sumOfHand is greater than 21, then subtract 10 from 
	// sumOfHand to reflect that we are now treating the ace(s) as 1's instead of 11's
	private int sumHandleAces(int sumOfHand){
		for(int i = 0; i < getActualNumberOfCardsInHand(); i++){
			if(hand[i].getNumber() == 1 && sumOfHand > 21){
				sumOfHand -= 10;
			}
		}
		return sumOfHand;
	}
	// Getter method for playerName
	public String getPlayerName(){
		return playerName;
	}
	// Method for printing a player's hand; if the player is the dealer, the first card is hidden
	public void printHand(){
		for(int i = 0; i < getActualNumberOfCardsInHand(); i++){
			// If the player is not the dealer we print his/her whole hand
			if(isDealer != true){
				System.out.println(hand[i].getName());
			}
			// If the player is the dealer we won't print the first card in his hand
			else{
				if(i == 0){
					System.out.println("[Hidden]");
				}
				else{
					System.out.println(hand[i].getName());
				}
			}
		}
	}
	// Method that determines how many empty slots there are in the player's hand
	private int numberOfNulls(){
		int numberOfNulls = 0;
		
		for(int i = 0; i < maxCardsInHand; i++){	
			if(hand[i] == null){
				numberOfNulls++;
			}
		}
		return numberOfNulls;
	}
	public int getMaxCardsInHand(){
		return maxCardsInHand;
	}
	public int getActualNumberOfCardsInHand(){
		return (maxCardsInHand - numberOfNulls());
	}
}