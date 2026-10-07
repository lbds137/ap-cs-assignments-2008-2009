/* Player.java
 * Vladlena Costescu
 * AP Computer Science AB
 * GinRummy (due 04/13/09)
 * This is the Player class.
 */

import java.util.ArrayList;

/** Implements a player of Gin Rummy, providing useful methods and keeping track of various 
 * aspects such as the player's name, hand, melds, deadwood, and deadwood score. **/
public class Player {
	private String name;
	// The maximum hand size is dictated by the rules of Gin Rummy
	private int maxHandSize = 11;
	private GRCardSet hand = new GRCardSet(maxHandSize);
	private GRCardSet deadwood = new GRCardSet();
	
	/** Accepts a string representing the player's name. **/
	public Player(String aName){
		name = aName;
	}
	
	/** Gets the player's name.
	 * @return a <code>String</code> representing the player's name **/
	public String getName(){
		return name;
	}
	/** Gets the player's hand.
	 * @return a <code>GRCardSet</code> object containing the cards 
	 * in the player's hand **/
	public GRCardSet getHand(){
		return hand;
	}
	/** Finds and gets the melds in the plater's hand.
	 * @return an <code>ArrayList&lt;Meld&gt;</code> containing
	 * the melds in the player's hand **/
	public ArrayList<Meld> getMelds(){
		hand.findMelds();
		
		return hand.getMelds();
	}
	/** Gets the deadwood in the player's hand.
	 * @return a <code>GRCardSet</code> object containing the 
	 * deadwood cards in the player's hand **/
	public GRCardSet getDeadwood(){
		ArrayList<Meld> melds = getMelds();
		ArrayList<GRCard> meldCards = new ArrayList<GRCard>();
		for(Meld meld : melds){
			for(GRCard card : meld.get()){
				meldCards.add(card);
			}
		}
		
		// If a card in hand isn't in meldCards, it is a deadwood card
		for(GRCard card : hand.get()){
			if(!meldCards.contains(card)){
				deadwood.add(card);
			}
		}
		
		return deadwood;
	}
	/** Attempts to add the specified card to the player's hand.
	 * @param <code>GRCard</code> aCard
	 * @return a <code>boolean</code> representing whether or not 
	 * the card was added to the player's hand **/
	public boolean addToHand(GRCard aCard){
		boolean wasCardAdded = hand.add(aCard);
		hand.getMelds();
		
		return wasCardAdded;
	}
	/** Discards the specified card from the player's hand.
	 * @param <code>GRCard</code> aCard
	 * @return a <code>GRCard</code> object representing the 
	 * card that was discarded (or null if the card
	 * does not exist in the player's hand) **/
	public GRCard discard(GRCard aCard){
		GRCard card = hand.get(aCard);
		hand.remove(card);
		
		return card;
	}
	/** Gets the player's deadwood score.
	 * @return an <code>int</code> representing the player's deadwood score **/
	public int getScore(){
		getDeadwood();
		
		return deadwood.getScore();
	}
	/** Prints the melds in the player's hand. **/
	public void printMelds(){
		hand.printMelds();
	}
}
