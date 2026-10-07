/* Card.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.4-cards (due 10/13/08)
 * This is the card class.
 */

public class Card {

	private int myNumber;
	private Suit mySuit;
	
	public enum Suit {
		Hearts, Diamonds, Spades, Clubs
	}
	
	public Card(Suit aSuit, int aNumber){
		if(aNumber > 0 && aNumber < 14){
			myNumber = aNumber;
		}
		else{
			System.out.println("The card number entered was incorrect.");
		}
		mySuit = aSuit;
	}
	public String getName(){
		String numberString = null;
		String ofConnector = " of ";
		String suitString = getSuitName(mySuit);
		
		String fullNameString;
		
		switch(myNumber)
		{
		case 1:
			numberString = "Ace";
			break;
		case 2:	
			numberString = "Two";
			break;
		case 3:
			numberString = "Three";
			break;
		case 4:
			numberString = "Four";
			break;
		case 5:
			numberString = "Five";
			break;
		case 6:
			numberString = "Six";
			break;
		case 7:
			numberString = "Seven";
			break;
		case 8:
			numberString = "Eight";
			break;
		case 9:
			numberString = "Nine";
			break;
		case 10:
			numberString = "Ten";
			break;
		case 11:
			numberString = "Jack";
			break;
		case 12:
			numberString = "Queen";
			break;
		case 13:
			numberString = "King";
			break;
		}
		
		fullNameString = numberString + ofConnector + suitString;
		
		return fullNameString;	
	}
	public String getSuitName(Suit aSuit){
		String stringMySuit = "";
		
		switch(aSuit)
		{
		case Hearts:
			stringMySuit = "Hearts";
			break;
		case Diamonds:	
			stringMySuit = "Diamonds";
			break;
		case Spades:
			stringMySuit = "Spades";
			break;
		case Clubs:
			stringMySuit = "Clubs";
			break;
		}
		
		return stringMySuit;
	}
	public Suit getSuit(){
		return mySuit;
	}
	public int getNumber(){
		return myNumber;
	}
	
}
