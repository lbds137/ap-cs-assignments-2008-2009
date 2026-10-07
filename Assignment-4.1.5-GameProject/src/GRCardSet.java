/* GRCardSet.java
 * Vladlena Costescu
 * AP Computer Science AB
 * GinRummy (due 04/13/09)
 * This class implements a GRCardSet, 
 * which is backed by an ArrayList of GRCard objects.
 */

import java.util.ArrayList;
import java.util.HashMap;

/** A wrapper class for a set of <code>GRCard</code> objects; provides useful methods
 * for card manipulations in Gin Rummy. **/
public class GRCardSet{
	// The underlying data structure of a GRCardSet is an ArrayList of GRCard objects
	private ArrayList<GRCard> cards;
	// We want to keep track of the melds in cards
	private ArrayList<Meld> melds = new ArrayList<Meld>();
	// If the situation calls for a maximum size, we want to keep track of it
	private int maxSize = -1;
	
	/** Default constructor; takes no arguments. **/
	public GRCardSet(){
		cards = new ArrayList<GRCard>();
	}
	/** Accepts an integer representing the maximum allowed size of the <code>GRCardSet</code>.
	 * @param <code>int</code> maximumSize **/
	public GRCardSet(int maximumSize){
		cards = new ArrayList<GRCard>();
		maxSize = maximumSize;
	}
	/** Accepts an <code>ArrayList&lt;GRCard&gt;</code>, which is stored in a private field. 
	 * @param <code>ArrayList&lt;GRCard&gt;</code> someCards **/
	public GRCardSet(ArrayList<GRCard> someCards){
		cards = someCards;
	}
	/** Accepts an <code>ArrayList&lt;GRCard&gt;</code>, which is stored in a private field, 
	 * and an integer representing the maximum allowed size of the <code>GRCardSet</code>.
	 * @param <code>ArrayList&lt;GRCard&gt;</code> someCards, <code>int</code> maximumSize **/
	public GRCardSet(ArrayList<GRCard> someCards, int maximumSize){
		maxSize = maximumSize;
		
		if(!(someCards.size() > maximumSize)){
			cards = someCards;
		}
	}
	
	/** Adds a card (only if it does not cause cards to exceed the maximum allowed size and if it is not already 
	 * present) and sorts the cards if the card was added. Returns <code>true</code> if the card was added;
	 * otherwise, returns <code>false</code>.
	 * @param <code>GRCard</code> card
	 * @return a <code>boolean</code> representing whether or not the card was added
	 * **/
	public boolean add(GRCard card){
		if(cards.size() == maxSize || this.contains(card)){
			return false;
		}
		else{
			cards.add(card);
			mergeSort();
			
			return true;
		}
	}
	/** Gets the underlying <code>ArrayList</code> of cards that stores the cards in the GRCardSet. 
	 * @return an <code>ArrayList</code> of <code>GRCard</code> objects **/
	public ArrayList<GRCard> get(){
		return cards;
	}
	/** Takes a card and returns the equivalent card in the <code>GRCardSet</code>, if any 
	 * (otherwise, returns <code>null</code>). This method does not assume that the two cards 
	 * are instances of the same <code>GRCard</code> object, only that there exists at least one 
	 * card in the <code>GRCardSet</code> such that <code>card.equals(aCard) == true</code>.
	 * @param <code>GRCard</code> aCard
	 * @return a <code>GRCard</code> object <strong>or</strong> <code>null</code>
	 * **/
	public GRCard get(GRCard aCard){
		for(GRCard card : cards){
			if(card.equals(aCard)){
				return card;
			}
		}
		
		return null;
	}
	/** Returns the card located at the specified index. Returns <code>null</code> 
	 * if the index is invalid. 
	 * @param <code>int</code> index
	 * @return a <code>GRCard</code> object
	 * **/
	public GRCard get(int index){
		try{
			return cards.get(index);
		}
		catch(Exception e){
			return null;
		}
	}
	/** Returns the melds associated with the <code>GRCardSet</code>. This method does 
	 * not call the <code>findMelds()</code> method. 
	 * @return an <code>ArrayList&lt;Meld&gt;</code> object **/
	public ArrayList<Meld> getMelds(){
		return melds;
	}
	/** Gets the "score" of the cards in the <code>GRCardSet</code>, as specified in the 
	 * <code>getValue()</code> method in the <code>GRCard</code> class. 
	 * @return an <code>int</code> representing the score of the cards in the <code>GRCardSet</code> **/
	public int getScore(){
		int score = 0;
		
		for(GRCard card : cards){
			score += card.getValue();
		}
		
		return score;
	}
	/** Replaces the cards in the <code>GRCardSet</code>. 
	 * @param <code>ArrayList&lt;GRCard&gt;</code> someCards
	 * @return <code>true</code>
	 * **/
	public boolean set(ArrayList<GRCard> someCards){
		cards = someCards;
		
		return true;
	}
	/** Replaces the card at the specified index. 
	 * @param <code>int</code> index, <code>GRCard</code> card 
	 * @return a <code>boolean</code> representing whether or not the card was replaced
	 * (<code>false</code> if the specified index is invalid) **/
	public boolean set(int index, GRCard card){
		try{
			cards.set(index, card);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	/** Sets the maximum allowed size of the <code>GRCardSet</code>.
	 * @param <code>int</code> maximumSize **/
	public void setMaxSize(int maximumSize){
		maxSize = maximumSize;
	}
	/** Removes the card specified from the <code>GRCardSet</code> if the card exists.
	 * @param <code>GRCard</code> card 
	 * @return a <code>boolean</code> representing whether or not the card was removed **/
	public boolean remove(GRCard card){
		return this.contains(card) && remove(cards.indexOf(this.get(card)));
	}
	/** Removes the card at the specified index.
	 * @param <code>int</code> index 
	 * @return a <code>boolean</code> representing whether or not the card was removed
	 * (<code>false</code> if the specified index is invalid) **/
	public boolean remove(int index){
		try{
			cards.remove(index);
			findMelds();
			
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	/** Removes all of the cards in the <code>GRCardSet</code> and returns them. 
	 * @return an <code>ArrayList&lt;GRCard&gt;</code> object containing the cards removed**/
	public ArrayList<GRCard> removeAll(){
		ArrayList<GRCard> cardsRemoved = cards;
		cards = new ArrayList<GRCard>();
		
		return cardsRemoved;
	}
	/** Return <code>true</code> if the <code>GRCardSet</code> contains the specified card; 
	 * otherwise, returns false. 
	 * @param <code>GRCard</code> aCard 
	 * @return a <code>boolean</code> representing whether or not the <code>GRCardSet</code> 
	 * contains the specified card **/
	public boolean contains(GRCard aCard){
		return get(aCard) != null;
	}
	/** Gets the size of the <code>GRCardSet</code>.
	 * @return an <code>int</code> representing the size of the <code>GRCardSet</code>**/
	public int size(){
		return cards.size();
	}
	/** Finds the melds associated with the cards in the <code>GRCardSet</code>. **/
	public void findMelds(){
		melds = new ArrayList<Meld>();
		
		// Adds sets to melds
		for(GRCardSet meld : findSets()){
			melds.add(new Meld(meld));
		}
		// Adds runs to melds
		for(GRCardSet meld : findRuns()){
			melds.add(new Meld(meld));
		}
		
		resolveConflictMelds();
	}
	/** Prints the names of the cards contained in the <code>GRCardSet</code>. **/
	public void print(){
		for(GRCard card : cards){
			System.out.println(card.getName());
		}
	}
	/** Prints the melds contained in the <code>GRCardSet</code>. This method calls
	 * <code>findMelds()</code> before printing the melds. **/
	public void printMelds(){
		findMelds();
		
		for(Meld meld : melds){
			System.out.println("-------------");
			meld.print();
			System.out.println("-------------");
		}
	}
	/** Returns <code>true</code> if the <code>GRCardSet</code> is a <code>Meld</code>;
	 * otherwise, returns <code>false</code>.
	 * @return a <code>boolean</code> representing whether or not the <code>GRCardSet</code>
	 * is a <code>Meld</code> **/
	public boolean isMeld(){
		return isSet() || isRun();
	}
	/** Returns <code>true</code> if the <code>GRCardSet</code> is a Gin Rummy set; 
	 * otherwise, returns <code>false</code>.
	 * @return a <code>boolean</code> representing whether or not the <code>GRCardSet</code>
	 * is a set **/
	public boolean isSet(){
		return findSets().size() == 1;
	}
	/** Returns <code>true</code> if the <code>GRCardSet</code> is a run; 
	 * otherwise, returns <code>false</code>.
	 * @return a <code>boolean</code> representing whether or not the <code>GRCardSet</code>
	 * is a run **/
	public boolean isRun(){
		return findRuns().size() == 1;
	}
	// Returns the Gin Rummy sets found in the GRCardSet, if any.
	private ArrayList<GRCardSet> findSets(){
		int numMatches = 0;
		GRCardSet cardsClone = new GRCardSet();
		ArrayList<Integer> positions = new ArrayList<Integer>();
		GRCardSet meldCards = new GRCardSet();
		ArrayList<GRCardSet> someMelds = new ArrayList<GRCardSet>();
		
		// Initialize cardsClone
		for(GRCard card : cards){
			cardsClone.add(card);
		}
		
		for(int i = 0; i < cardsClone.size(); i++){
			// Inner loop compares the current outer loop card with the rest of the cards in cards
			for(int j = 0; j < cardsClone.size(); j++){
				// Tests if the cards are not the same card, are not null, and are equal to each other
				if(i != j && cardsClone.get(i) != null && cardsClone.get(j) != null && cardsClone.get(i).getNumber() == cardsClone.get(j).getNumber()){
					numMatches++;
					positions.add(j);
				}
			}
			// If there are at least 3 cards of the same number as the card at index i, consider
			// the cards a meld and add them to the someMelds ArrayList
			if(numMatches >= 2){
				positions.add(i);
				for(int j = 0; j < positions.size(); j++){	
					meldCards.add(cards.get(positions.get(j)));
					cardsClone.set(positions.get(j), null);
				}
				meldCards.mergeSort();
				
				someMelds.add(meldCards);
			}
			
			// Reset values
			numMatches = 0;
			positions = new ArrayList<Integer>();
			meldCards = new GRCardSet();
		}
		
		return someMelds;
	}
	// Returns runs found in the GRCardSet, if any.
	private ArrayList<GRCardSet> findRuns(){
		int numMatches = 0;
		ArrayList<Integer> positions = new ArrayList<Integer>();
		GRCardSet meldCards = new GRCardSet();
		ArrayList<GRCardSet> someMelds = new ArrayList<GRCardSet>();
		
		// It's difficult to explain what happens here, but it works
		for(int i = 1; i < cards.size(); i++){
			if(cards.get(i).toNumber() == cards.get(i - 1).toNumber() + 1 && cards.get(i).getSuit() == cards.get(i - 1).getSuit()){
				numMatches++;
				positions.add(i - 1);
				if(i == cards.size() - 1 || cards.get(i + 1).toNumber() != cards.get(i).toNumber() + 1){
					positions.add(i);
				}
			}
			else{
				// Reset values
				numMatches = 0;
				positions = new ArrayList<Integer>();
			}
			if(numMatches >= 2 && (i == cards.size() - 1 || cards.get(i + 1).toNumber() != cards.get(i).toNumber() + 1)){
				for(int j = 0; j < positions.size(); j++){	
					meldCards.add(cards.get(positions.get(j)));
				}
				meldCards.mergeSort();
				
				someMelds.add(meldCards);
				
				// Reset values
				numMatches = 0;
				positions = new ArrayList<Integer>();
				meldCards = new GRCardSet();
			}
			
		}
		
		return someMelds;
	}
	private void resolveConflictMelds(){
		// Cards in melds that belong to multiple melds
		GRCardSet overlapCards = findConflictCards();
		// Conflict melds
		ArrayList<Meld> conflictMelds = new ArrayList<Meld>();
		// Temporary conflict card
		GRCard tempCard;
		// Temporary meld
		Meld tempMeld;
		
		for(int i = 0; i < overlapCards.get().size(); i++){
			tempCard = overlapCards.get(i);
			
			for(int j = 0; findConflictMelds().get(tempCard) != null && j < findConflictMelds().get(tempCard).size(); j++){
				tempMeld = findConflictMelds().get(tempCard).get(j);
				
				if(tempMeld.contains(tempCard) && !tempMeld.isRemovable(tempCard)){
					conflictMelds.add(tempMeld);
				}
				else{
					tempMeld.remove(tempCard);
					conflictMelds = new ArrayList<Meld>();
					i = 0;
				}
			}
		}
		// Conflict melds that have the highest score out of all the melds associated with their conflict card
		ArrayList<Meld> highestScoreMelds = findHighestScoreConflictMelds();
		// Meld stored temporarily in iterations of the loops below
		tempMeld = new Meld();
		
		for(int i = 0; i < melds.size(); i++){
			tempMeld = getMelds().get(i);
			
			// If tempMeld is in conflictMelds but not in highestScoreMelds, remove it from melds
			if(conflictMelds.contains(tempMeld) && !highestScoreMelds.contains(tempMeld)){
				melds.remove(tempMeld);
				
				// Melds has shrunk by one, so we want to decrement the index to adjust for this
				i--;
			}
		}
	}
	// Returns an ArrayList of the conflict melds in melds with the highest score (see getScore(someMelds))
	private ArrayList<Meld> findHighestScoreConflictMelds(){
		// Cards in melds that belong to multiple melds
		GRCardSet overlapCards = findConflictCards();
		// All conflict melds, grouped by overlapCards they contain
		HashMap<GRCard, ArrayList<Meld>> allOverlapMelds = findConflictMelds();
		// Melds associated with a particular overlapCard (which is not stored in overlapMelds)
		ArrayList<Meld> overlapMelds;
		// overlapMelds that have the highest score out of all the melds associated with their overlapCard
		ArrayList<Meld> highestScoreMelds = new ArrayList<Meld>();
		
		// For each overlapCard, add the meld with the highest score to highestScoreMelds
		for(GRCard overlapCard : overlapCards.get()){
			overlapMelds = new ArrayList<Meld>(allOverlapMelds.get(overlapCard));
			
			int highestScore = 0;
			Meld highestScoreMeld = new Meld();
			
			// Compares melds in overlapMelds and stores the one with the higher score into highestScoreMeld
			for(Meld meld : overlapMelds){
				if(meld.getScore() > highestScore){
					highestScore = meld.getScore();
					highestScoreMeld = meld;
				}
			}
			if(!highestScoreMelds.contains(highestScoreMeld)){
				highestScoreMelds.add(highestScoreMeld);
			}
		}
		
		return highestScoreMelds;
	}
	// Returns a HashMap of (conflict card, melds in which conflict card appears) for melds
	private HashMap<GRCard, ArrayList<Meld>> findConflictMelds(){
		// Cards in melds that belong to multiple melds
		GRCardSet overlapCards = findConflictCards();
		// All conflict melds, grouped by overlapCards they contain
		HashMap<GRCard, ArrayList<Meld>> allOverlapMelds = new HashMap<GRCard, ArrayList<Meld>>();
		// Melds associated with a particular overlapCard (which is not stored in overlapMelds)
		ArrayList<Meld> overlapMelds = new ArrayList<Meld>();
		
		// For each overlapCard, put (overlapCard, overlapMelds) into allOverlapMelds
		for(GRCard overlapCard : overlapCards.get()){
			for(Meld meld : melds){
				if(meld.contains(overlapCard)){
					overlapMelds.add(meld);
				}
			}
			allOverlapMelds.put(overlapCard, overlapMelds);
			
			// Reset overlapMelds
			overlapMelds = new ArrayList<Meld>();
		}
		
		return allOverlapMelds;
	}
	// Returns a GRCardSet of cards that appear in multiple melds in melds (i.e. conflict cards)
	private GRCardSet findConflictCards(){
		// All cards in melds
		GRCardSet meldCards = new GRCardSet();
		// Cards in melds that belong to multiple melds
		GRCardSet overlapCards = new GRCardSet();
		
		// Add each card in each meld in melds to meldCards and add conflict cards to overlapCards
		for(Meld meld : melds){
			for(GRCard card : meld.get()){
				// If card cannot be added, it means that it is a duplicate (since we are working with a set)
				// and therefore a conflict card 
				if(!meldCards.add(card)){
					overlapCards.add(card);
				}
			}
		}
		
		// Sort meldCards
		meldCards.mergeSort();
		
		return overlapCards;
	}
	/** Sorts the cards in the <code>GRCardSet</code>. **/
	public void mergeSort(){
		cards = mergeSort(cards);
	}
	/*
	 * This method written by Drausin Wulsin, modified for use with GRCard objects
	 */
	private ArrayList<GRCard> mergeSort(ArrayList<GRCard> ar){
		
		// sorts in descending order
		
		/* Added */
		// If there are no elements, return
		if(ar.size() == 0){
			return ar;
		}
		/* End Added */
		
		// if there is only one element, return 
		else if(ar.size() == 1){
			return ar;
		}
		
		// if the first element is less than the second, switch them and return
		else if(ar.size() == 2){
			if(ar.get(0).compareTo(ar.get(1)) > 0){
				GRCard temp = ar.remove(0);
				ar.add(temp);
			}
			
			return ar;
		}
		
		// if there are more than two elements, split list in half and make recursive call
		else{
			
			ArrayList<GRCard> firstHalf = new ArrayList<GRCard>();
			ArrayList<GRCard> secondHalf = new ArrayList<GRCard>();
			
			for(int i = 0; i < ar.size(); i++){
				// if element is in first half of list, add to firstHalf, otherwise add to secondHalf
				if(i < ar.size()/2){
					firstHalf.add(ar.get(i));
				}
				else{
					secondHalf.add(ar.get(i));
				}
			}
			
			// recursively sort the two halves
			firstHalf = mergeSort(firstHalf);
			secondHalf = mergeSort(secondHalf);
			
			// merge the two sorted halves back into one array
			ar = new ArrayList<GRCard>(); // reset ar ArrayList
			
			while(firstHalf.size() > 0 && secondHalf.size() > 0){
				// if the element in the first half is larger, remove it and add to ar list
				if(firstHalf.get(0).compareTo(secondHalf.get(0)) < 0){
					ar.add(firstHalf.remove(0));
				}
				// if the element in the second half is larger, remove it and add to the data list
				else{
					ar.add(secondHalf.remove(0));
				}
				//mergeSortCompCount++;
			}
			
			// if the first half still has elements, then the second half must be empty
			if(firstHalf.size() > 0){
				for(GRCard d: firstHalf){
					ar.add(d);
				}
			}
			// vice versa here
			else{
				for(GRCard d: secondHalf){
					ar.add(d);
				}
			}
			
			return ar;
		}
	}
}
