/* GRCard.java
 * Vladlena Costescu
 * AP Computer Science AB
 * GinRummy (due 04/13/09)
 * This class adds Gin Rummy specific 
 * fields and methods to the Card class.
 */

/** Extends the <code>Card</code> class, adding Gin Rummy specific fields and methods. **/
public class GRCard extends Card {
	
	/** Accepts a <code>Suit</code> and a number with which to initialize the card.
	 * @param <code>Suit</code> suit, <code>int</code> number **/
	public GRCard(Suit suit, int number) {
		super(suit, number);
	}
	/** Accepts an existing <code>Card</code> object. 
	 * @param <code>Card</code> aCard **/
	public GRCard(Card aCard){
		super(aCard.getSuit(), aCard.getNumber());
	}
	
	/** Gets the "value" of the card (used for calculating deadwood score in Gin Rummy). 
	 * @return an <code>int</code> representing the value of the card **/
	public int getValue(){
		if(getNumber() < 11){
			return getNumber();
		}
		else{
			return 10;
		}
	}
}
