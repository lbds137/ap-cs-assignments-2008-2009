/* Deck.java
 * Vladlena Costescu
 * AP Computer Science AB
 * GinRummy (due 04/13/09)
 * This is the deck class, reused from 
 * Assignment-1.5-Blackjack but modified and improved.
 */

import java.util.ArrayList;
import java.util.Random;

/** Implements a standard deck of cards and basic methods such as 
 * <code>shuffle</code> and <code>dealNextCard</code>. **/
public class Deck {
	/** The size of a standard deck is 52. **/
	public static final int DECK_SIZE = 52;
	/** Contains all of the cards, in order by suit and then number. **/
	public static final ArrayList<Card> UNSHUFFLED_CARDS = initCards();
	private ArrayList<Card> shuffledCards = new ArrayList<Card>();
	/** Default constructor; initializes an unshuffled deck. **/
	public Deck(){
		// Sets shuffledCards equal to UNSHUFFLED_CARDS
		for(Card card : UNSHUFFLED_CARDS){
			shuffledCards.add(card);
		}
	}
	/** Initializes the deck and shuffles the cards if passed a value of <code>true</code>. 
	 * @param <code>boolean</code> shuffle **/
	public Deck(boolean shuffle){
		this();
		
		if(shuffle){
			shuffle();
		}
	}
	
	/** Shuffles the deck. **/
	public void shuffle(){
		// We want to keep track of the previous state of shuffledCards so that we can shuffle
		// even if some card(s) has(have) been removed from shuffledCards relative to UNSHUFFLED_CARDS
		ArrayList<Card> shuffledCardsPreviousState = new ArrayList<Card>();
		for(Card c : shuffledCards){
			shuffledCardsPreviousState.add(c);
		}
		
		Random randGen = new Random();
		int[] randomIntArray = new int[DECK_SIZE];
		
		// Generate an array of random numbers
		for(int i = 0; i < DECK_SIZE; i++){
			randomIntArray[i] = randGen.nextInt(DECK_SIZE);
		}
		
		// Eliminate any duplicate numbers so that the array contains only unique values
		for(int i = 0; i < DECK_SIZE; i++){
			for(int j = 0; j < DECK_SIZE; j++){
				if(randomIntArray[i] == randomIntArray[j] && i != j){
					randomIntArray[j] = randGen.nextInt(DECK_SIZE);
					i = 0;
				}
			}
		}
		
		// Use the randomIntArray to create a shuffled deck from the unshuffled deck
		for(int i = 0, index = 0; i < DECK_SIZE; i++, index++){
			// When shuffling, we don't want to introduce any cards that didn't already exist in shuffledCards
			if(shuffledCardsPreviousState.contains(UNSHUFFLED_CARDS.get(randomIntArray[i]))){
				shuffledCards.set(index, UNSHUFFLED_CARDS.get(randomIntArray[i]));
			}
			else{
				// If no action was performed on shuffledCards, we don't want to increment the index
				index--;
			}
		}
	}
	// Initializes UNSHUFFLED_CARDS
	private static ArrayList<Card> initCards(){
		ArrayList<Card> cards = new ArrayList<Card>();
		
		// Outer loop makes sure that only enough cards to fill the deck are created
		for(int numCards = 0; numCards < DECK_SIZE;){
			// Middle loop goes through each number, from 1 to (deck size / number of suits)
			for(int cardNum = 1; cardNum <= (Deck.DECK_SIZE / Suit.values().length); cardNum++){	
				// Inner loop creates four cards (one of each suit) for a given card number
				for(int cardSuitCounter = 0; cardSuitCounter < 4; cardSuitCounter++){
					cards.add(new Card(Suit.values()[cardSuitCounter], cardNum));
					numCards++;
				}
			}	
		}
		
		return cards;
	}
	/** Prints the names of the cards in the deck in the order in which the cards are stored. **/
	public void printDeck(){
		// Loop through to print the shuffled cards 
		// (if the cards are not shuffled, the cards printed will simply be in order)
		for(Card card : shuffledCards){
			System.out.println(card.getName());
		}
	}
	/** Deals the next card in the deck (the card at index 0).
	 * @return a <code>Card</code> object representing the card that was removed (dealt) **/
	public Card dealNextCard(){
		return shuffledCards.remove(0);
	}
}
