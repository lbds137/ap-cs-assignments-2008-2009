/* Deck.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.4-cards (due 10/13/08)
 * This is the deck class.
 */

import java.util.Random;

public class Deck {
	private int deckSize = 52;
	private Card[] unshuffledCards = new Card[deckSize];
	private Card[] shuffledCards = new Card[deckSize];
	
	public Deck(){
		
		// Outer loop makes sure that only enough cards to fill the deck are created
		for(int numberOfCardsCounter = 0; numberOfCardsCounter < deckSize;){
			
			// Middle loop goes through each number, from 1 to 13
			for(int cardNumberCounter = 1; cardNumberCounter < 14; cardNumberCounter++){
				
				// Inner loop creates four cards (one of each suit) for a given card number
				for(int cardSuitCounter = 1; cardSuitCounter < 5; cardSuitCounter++){
					switch(cardSuitCounter)
					{
					case 1:
						unshuffledCards[numberOfCardsCounter] = new Card(Card.Suit.Hearts, cardNumberCounter);
						numberOfCardsCounter++;
						break;
					case 2:
						unshuffledCards[numberOfCardsCounter] = new Card(Card.Suit.Diamonds, cardNumberCounter);
						numberOfCardsCounter++;
						break;
					case 3:
						unshuffledCards[numberOfCardsCounter] = new Card(Card.Suit.Spades, cardNumberCounter);
						numberOfCardsCounter++;
						break;
					case 4:
						unshuffledCards[numberOfCardsCounter] = new Card(Card.Suit.Clubs, cardNumberCounter);
						numberOfCardsCounter++;
						break;
					}
				}
			}
		}
		
		// When the deck is first created, its cards are not shuffled yet
		for(int i = 0; i < deckSize; i++){
			shuffledCards[i] = unshuffledCards[i];
		}
	}
	public Deck(boolean shuffle){
		
		// Create the deck (in order for now)
		this();
		
		// If the passed boolean is true that means we want to shuffle the deck
		if(shuffle == true){
			this.Shuffle();
		}
	}
	public void Shuffle(){
		Random randGen = new Random();
		int[] randomIntArray = new int[deckSize];
		
		// Generate an array of random numbers
		for(int i = 0; i < randomIntArray.length; i++){
			randomIntArray[i] = randGen.nextInt(deckSize);
		}
		
		// Eliminate any duplicate numbers so that the array contains only unique values
		for(int i = 0; i < randomIntArray.length; i++){
			for(int j = 0; j < randomIntArray.length; j++){
				if(randomIntArray[i] == randomIntArray[j] && i != j){
					randomIntArray[j] = randGen.nextInt(deckSize);
					i = 0;
				}
			}
		}
		
		// Use the randomIntArray to create a shuffled deck from the unshuffled deck
		for(int i = 0; i < randomIntArray.length; i++){
			shuffledCards[i] = unshuffledCards[randomIntArray[i]];
		}
	}
	public void printDeck(){
		
		// Loop through to print the shuffled cards 
		// (if the cards are not shuffled, the cards printed will simply be in order)
		
		for(int j = 0; j < deckSize; j++){
			System.out.println(/*decks[i]*/shuffledCards[j].getName());
		}
	}
	public Card dealNextCard(){
		
		// Create a new card with the value of the first card in the deck and 
		// set the first card in the deck to null (because we removed it)
		Card nextCard = new Card(shuffledCards[0].getSuit(), shuffledCards[0].getNumber());
		shuffledCards[0] = null;
		
		// Shift cards up one slot
		for(int i = 0; i < (deckSize - 1); i++){
			shuffledCards[i] = shuffledCards[(i + 1)];
		}
		
		// Set the last card to null since we moved the cards up
		shuffledCards[(deckSize - 1)] = null;
		
		// Decrement the deck size since the deck now has one less card
		deckSize--;
		
		return nextCard;
	}
}
