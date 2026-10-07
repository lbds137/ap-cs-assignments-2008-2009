/* Tester.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.4-cards (due 10/13/08)
 * This is the tester class.
 */

public class Tester {

	public static void main(String[] args) {
		Deck deck1 = new Deck(true);
		deck1.printDeck();
		
		Card firstCard = deck1.dealNextCard();
		Card secondCard = deck1.dealNextCard();
		
		System.out.println();
		deck1.printDeck();
		
		System.out.println();
		System.out.println(firstCard.getName());
		System.out.println(secondCard.getName());
	}

}
