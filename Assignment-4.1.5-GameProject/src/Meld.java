/* Meld.java
 * Vladlena Costescu
 * AP Computer Science AB
 * GinRummy (due 04/13/09)
 * This implements a Meld, which extends GRCardSet.
 */

import java.util.ArrayList;

/** Extends the <code>GRCardSet</code> class, overriding certain methods to
 * ensure that the cards contained within form a valid meld and providing
 * some helpful methods for manipulating melds. **/
public class Meld extends GRCardSet {
	// The minimum size of a meld is set by the rules of Gin Rummy
	private int minSize = 3;
	
	/** Default constructor; takes no arguments. **/
	public Meld(){
		super();
	}
	/** Accepts an <code>ArrayList&lt;GRCard&gt;</code> object and adds the cards therein
	 * to the <code>Meld</code> if they form a valid meld. 
	 * @param <code>ArrayList&lt;GRCard&gt;</code> aMeld **/
	public Meld(ArrayList<GRCard> aMeld){
		super(aMeld);
		
		if(!this.isMeld()){
			super.set(new ArrayList<GRCard>());
		}
	}
	/** Accepts a <code>GRCardSet</code> object and adds the cards therein
	 * to the <code>Meld</code> if they form a valid meld. 
	 * @param <code>GRCardSet</code> aMeld **/
	public Meld(GRCardSet aMeld){
		this(aMeld.get());
	}
	
	/** Adds the specified card to the <code>Meld</code> only if it doesn't cause 
	 * the <code>Meld</code> to no longer meet the meld criteria. 
	 * @param <code>GRCard</code> aCard 
	 * @return a <code>boolean</code> representing whether or not the card was added **/
	public boolean add(GRCard aCard){
		// Executes if this is a run
		if(isRun()){
			// Adds aCard only if it fits into the run
			if((aCard.getNumber() == get(0).getNumber() - 1 || aCard.getNumber() == get(size() - 1).getNumber() + 1) && aCard.getSuit() == get(0).getSuit()){
				return super.add(aCard);
			}
			else{
				return false;
			}
		}
		// Executes if this is a set (implicit)
		else{
			boolean isEqual = false;
			
			// Checks if the cards are equal
			for(GRCard card : this.get()){
				if(card.equals(aCard)){
					isEqual = true;
				}
			}
			
			// Adds aCard only if it fits into the set
			if(aCard.getNumber() == get(0).getNumber() && !isEqual){
				return super.add(aCard);
			}
			else{
				return false;
			}
		}
		
	}
	/** Replaces the cards in the <code>Meld</code> only if the specified cards
	 * constitute a valid <code>Meld</code>. 
	 * @param <code>ArrayList&lt;GRCard&gt;</code> someCards
	 * @return a <code>boolean</code> representing whether or not the cards were replaced
	 * **/
	public boolean set(ArrayList<GRCard> someCards){
		GRCardSet aMeld = new GRCardSet(someCards);
		
		aMeld.mergeSort();
		
		if(aMeld.isMeld()){
			super.set(aMeld.get());
			return true;
		}
		else{
			return false;
		}
	}
	/** Always returns false, because a meld can only be modified by adding cards to the tails
	 * or replacing all of the cards therein. 
	 * @param <code>int</code> index, <code>GRCard</code> card 
	 * @return <code>false</code> **/
	public boolean set(int index, GRCard card){
		return false;
	}
	/** Removes the card specified from the <code>Meld</code> if the card exists
	 * and only if doing so doesn't cause the <code>Meld</code> to no longer 
	 * meet the meld criteria. 
	 * @param <code>GRCard</code> card 
	 * @return a <code>boolean</code> representing whether or not the card was removed **/
	public boolean remove(GRCard card){
		if(this.contains(card)){
			return remove(this.get().indexOf(this.get(card)));
		}
		else{
			return false;
		}
	}
	/** Removes the card at the specified index only if doing so doesn't cause the 
	 * <code>Meld</code> to no longer meet the meld criteria.
	 * @param <code>int</code> index 
	 * @return a <code>boolean</code> representing whether or not the card was removed **/
	public boolean remove(int index){
		if(isRemovable(index)){
			if(index >= this.size() / 2){
				return remove(this.size() - 1, index);
			}
			else{
				return remove(0, index);
			}
		}
		else{
			return false;
		}
	}
	// Recursive remove method; keeps removing cards from either end until it reaches the desired index
	private boolean remove(int startIndex, int indexToRemove){
		if(startIndex != indexToRemove){
			// -1 means first half, 1 means second half
			int whichHalf = Integer.signum(startIndex - this.size() / 2);
			
			super.remove(startIndex);
			
			if(whichHalf > 0){
				startIndex--;
			}
			else{
				indexToRemove--;
			}
			
			return remove(startIndex, indexToRemove);
		}
		else{
			return super.remove(indexToRemove);
		}
	}
	/**  **/
	public ArrayList<Meld> split(){
		if(isSplittable()){
			return split(this.size() / 2);
		}
		else{
			return null;
		}
	}
	// Recursive split method; splits the meld into smaller pieces until each piece is not splittable
	private ArrayList<Meld> split(int index){
		ArrayList<GRCard> meldCards = this.get();
		ArrayList<Meld> splitMelds = new ArrayList<Meld>();
		
		splitMelds.add(new Meld((ArrayList<GRCard>) meldCards.subList(0, index)));
		splitMelds.add(new Meld((ArrayList<GRCard>) meldCards.subList(index, meldCards.size())));
		
		for(int i = 0; i < splitMelds.size(); i++){
			if(splitMelds.get(i).isSplittable()){
				for(Meld meld : splitMelds.get(i).split()){
					splitMelds.add(i + 1, meld);
				}
				splitMelds.remove(i);
			}
		}
		
		return splitMelds;
	}
	/** Determines whether the specified card can be removed without causing the 
	 * <code>Meld</code> to no longer meet the meld criteria. The removal doesn't have
	 * to be performable in one step (i.e. the card doesn't have to be on one of the ends)
	 * to be considered possible. 
	 * @param <code>GRCard</code> card 
	 * @return a <code>boolean</code> representing whether or not the card is removable **/
	public boolean isRemovable(GRCard card){
		return this.contains(card) && isRemovable(this.get().indexOf(this.get(card)));
	}
	/** Determines whether the card at the specified index can be removed without causing
	 * the <code>Meld</code> to no longer meet the meld criteria. The removal doesn't have
	 * to be performable in one step (i.e. the card doesn't have to be on one of the ends)
	 * to be considered possible. 
	 * @param <code>int</code> index 
	 * @return a <code>boolean</code> representing whether or not the card is removable **/
	public boolean isRemovable(int index){
		return index >= 0 && index < this.size() && (this.size() - index > minSize || index >= minSize);
	}
	/** Determines whether or not the meld can be split into two melds. 
	 * @return a <code>boolean</code> representing whether or not the meld can be split **/
	public boolean isSplittable(){
		return this.size() >= 6;
	}
}
