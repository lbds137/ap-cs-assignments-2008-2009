/* Tester.java
 * Vladlena Costescu
 * AP Computer Science AB
 * GinRummy (due 04/13/09)
 * This is the tester class.
 */

public class Tester {

	public static void main(String[] args) throws Exception {
		
		Player human = new Player("Ivan the Terrible");
		
		/*
		Deck deck = new Deck(true);
		for(int i = 0; i < 10; i++){
			human.addToHand(new GRCard(deck.dealNextCard()));
		}
		*/
		
		///*
		human.addToHand(new GRCard(Suit.Diamonds, 6));
		human.addToHand(new GRCard(Suit.Diamonds, 3));
		human.addToHand(new GRCard(Suit.Diamonds, 4));
		human.addToHand(new GRCard(Suit.Hearts, 6));
		human.addToHand(new GRCard(Suit.Spades, 6));
		human.addToHand(new GRCard(Suit.Hearts, 8));
		human.addToHand(new GRCard(Suit.Spades, 8));
		human.addToHand(new GRCard(Suit.Clubs, 8));
		human.addToHand(new GRCard(Suit.Diamonds, 7));
		human.addToHand(new GRCard(Suit.Diamonds, 5));
		// Doesn't get added because it's a duplicate, even though it's a new instance of the Five of Diamonds
		human.addToHand(new GRCard(Suit.Diamonds, 5));
		human.addToHand(new GRCard(Suit.Diamonds, 10));
		// Doesn't get added because hand is full
		human.addToHand(new GRCard(Suit.Clubs, 13));
		
		// This works even though we are using two different card instances of the Ten of Diamonds
		human.discard(new GRCard(Suit.Diamonds, 10));
		
		human.printMelds();
		
		for(int i = 0; i < 10; i++){
			System.out.println(human.getHand().get(i).getName());
		}
		System.out.println("&&&&&&&&&&&&& Deadwood:");
		human.getDeadwood().print();
		System.out.println(human.getScore());
		//*/
		
		
		/** The following can be used to test the GRCardSet class (and of course can be modified to test
		 ** more aspects of the GRCardSet class
		 **/
		
		/*
		GRCardSet cards = new GRCardSet();
		cards.add(new GRCard(Suit.Diamonds, 6));
		cards.add(new GRCard(Suit.Diamonds, 3));
		cards.add(new GRCard(Suit.Diamonds, 4));
		cards.add(new GRCard(Suit.Hearts, 6));
		cards.add(new GRCard(Suit.Spades, 6));
		cards.add(new GRCard(Suit.Hearts, 8));
		cards.add(new GRCard(Suit.Spades, 8));
		cards.add(new GRCard(Suit.Clubs, 8));
		cards.add(new GRCard(Suit.Diamonds, 7));
		cards.add(new GRCard(Suit.Diamonds, 5));
		
		cards.print();
		System.out.println("++++++++++++++++");
		System.out.println("conflict cards: ");
		cards.findMelds();
		cards.findConflictCards().print();
		*/
		
		/** **/
		
		
		/** The following can be used to test the Meld class (and of course can be modified to test
		 ** more aspects of the Meld class
		 **/
		
		/*
		GRCardSet cards = new GRCardSet();
		cards.add(new GRCard(Suit.Hearts, 1));
		cards.add(new GRCard(Suit.Hearts, 2));
		cards.add(new GRCard(Suit.Hearts, 3));
		cards.add(new GRCard(Suit.Hearts, 4));
		cards.add(new GRCard(Suit.Hearts, 5));
		cards.add(new GRCard(Suit.Hearts, 6));
		cards.add(new GRCard(Suit.Hearts, 7));
		cards.add(new GRCard(Suit.Hearts, 8));
		cards.add(new GRCard(Suit.Hearts, 9));
		
		Meld meld = new Meld(cards);
		meld.print();
		System.out.println("--------------");
		meld.remove(new GRCard(Suit.Hearts, 4));
		meld.print();
		*/
		
		/** **/
	}

}
