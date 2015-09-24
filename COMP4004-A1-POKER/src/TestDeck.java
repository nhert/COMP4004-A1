import junit.framework.*;
import java.util.ArrayList;

public class TestDeck extends TestCase{
	public TestDeck(String sname){
		super(sname);
	}
	
	public void runAll(){
		testNewDeck();
		testEveryCardInDeck();
		testEveryCardInDeckUnique();
	}
	

	public void testNewDeck(){
		Deck d = new Deck();
	}
	
	//test that we can pull 52 cards from the deck
	public void testEveryCardInDeck(){
		Deck d = new Deck();
		for(int x = 0; x< 52; x++){
			assertNotNull(d.getACard());
		}
	}
	
	//test that every card in our deck is unique (no duplicates)
	public void testEveryCardInDeckUnique(){
		ArrayList<Card> cardsTest = new ArrayList<Card>();
		Deck d = new Deck();
		
		for(int x = 0; x< 52; x++){
			Card c = d.getACard();
			assertFalse(cardsTest.contains(c));
			cardsTest.add(c);
		}
	}
}
