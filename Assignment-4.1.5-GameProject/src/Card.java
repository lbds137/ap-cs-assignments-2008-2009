/* Card.java
 * Vladlena Costescu
 * AP Computer Science AB
 * GinRummy (due 04/13/09)
 * This is the card class, reused from 
 * Assignment-1.5 but modified and improved.
 */

/** Defines a card in terms of a <code>Suit</code> and a number. **/
public class Card {

	private int myNumber;
	private Suit mySuit;
	
	/** Accepts a <code>Suit</code> and a number with which to initialize the card.
	 * @param <code>Suit</code> suit, <code>int</code> number **/
	public Card(Suit aSuit, int aNumber){
		if(aNumber > 0 && aNumber <= (Deck.DECK_SIZE / Suit.values().length)){
			myNumber = aNumber;
			mySuit = aSuit;
		}
	}
	
	/** Gets a string representing the name of the card. 
	 * @return a <code>String</code> representing the card name **/
	public String getName(){
		return getNumberName() + " of " + getSuitName();
	}
	/** Gets the suit of the card.
	 * @return a <code>Suit</code> representing the suit of the card **/
	public Suit getSuit(){
		return mySuit;
	}
	/** Gets the string representation of the suit of the card.
	 * @return a <code>String</code> representing the name of the suit of the card **/
	public String getSuitName(){
		return mySuit.toString();
	}
	/** Gets the number of the card.
	 * @return an <code>int</code> representing the number of the card **/
	public int getNumber(){
		return myNumber;
	}
	/** Gets the string representation of the number of the card.
	 * @return a <code>String</code> representing the number of the card **/
	public String getNumberName(){
		String stringMyNumber = "";
		
		switch(myNumber)
		{
		case 1:
			stringMyNumber = "Ace";
			break;
		case 2:	
			stringMyNumber = "Two";
			break;
		case 3:
			stringMyNumber = "Three";
			break;
		case 4:
			stringMyNumber = "Four";
			break;
		case 5:
			stringMyNumber = "Five";
			break;
		case 6:
			stringMyNumber = "Six";
			break;
		case 7:
			stringMyNumber = "Seven";
			break;
		case 8:
			stringMyNumber = "Eight";
			break;
		case 9:
			stringMyNumber = "Nine";
			break;
		case 10:
			stringMyNumber = "Ten";
			break;
		case 11:
			stringMyNumber = "Jack";
			break;
		case 12:
			stringMyNumber = "Queen";
			break;
		case 13:
			stringMyNumber = "King";
			break;
		default:
			stringMyNumber = "Unimplemented";
			break;
		}
		
		return stringMyNumber;
	}
	/** Converts the card to a number (the index of the card in Deck.UNSHUFFLED_CARDS plus one).
	 * @return an <code>int</code> denoting the number representation of the card **/
	public int toNumber(){
		Suit[] suitValues = Suit.values();
		int suitNumber = 0;
		
		// Suit number is from 0 to 3
		for(int i = 0; i < suitValues.length; i++){
			if(mySuit == suitValues[i]){
				suitNumber = i;
			}
		}
		
		return suitNumber * (Deck.DECK_SIZE / suitValues.length) + myNumber;
	}
	/** Numerically compares the card to the specified card.
	 * @param <code>Card</code> aCard
	 * @return an <code>int</code>, which is negative if the card is less than 
	 * the specified card, zero if the cards are the same, and positive if the card 
	 * is greater than the specified card (as defined in the <code>toNumber()</code>
	 * method). **/
	public int compareTo(Card aCard){
		Integer thisNumber = this.toNumber();
		Integer aCardNumber = aCard.toNumber();
		
		return thisNumber.compareTo(aCardNumber);
	}
	/** Compares the suit and number values of the card and the specified card to determine 
	 * if the cards are equal to each other.
	 * @param <code>Card</code> aCard
	 * @return a <code>boolean</code> representing whether or not the cards are equal 
	 */
	public boolean equals(Card aCard){
		return this.getSuit() == aCard.getSuit() && this.getNumber() == aCard.getNumber();
	}
}
