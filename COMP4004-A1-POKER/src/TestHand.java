import junit.framework.*;

public class TestHand extends TestCase{
	public TestHand(String sname){
		super(sname);
	}
	
	//assure that card values are stored properly for at least 1 card
	public void testHandOneCardLowest(){
		Hand h = new Hand(); // default case where there are no parms
		h.add(new Card());
		assertEquals("TwoSpades", h.getCard(0).getRankSuit());
		
		Hand h2 = new Hand();
		h2.add(new Card(2, "Hearts"));
		assertEquals("TwoHearts", h2.getCard(0).getRankSuit());
	}
	
	//Assure that a player can hold more than one card
	public void testHandTwoCards(){
		Hand h = new Hand();
		h.add(new Card(2, "Clubs"));
		h.add(new Card(5, "Clubs"));
		assertEquals("TwoClubs", h.getCard(0).getRankSuit());
		assertEquals("FiveClubs", h.getCard(1).getRankSuit());
	}
	
	//Assure that the suited cards are output properly and their values are retained
	public void testHandWithSuited(){
		Hand h = new Hand();
		h.add(new Card(11, "Clubs"));
		h.add(new Card(12, "Clubs"));
		h.add(new Card(13, "Clubs"));
		h.add(new Card(14, "Clubs"));
		assertEquals("JackClubs", h.getCard(0).getRankSuit());
		assertEquals("QueenClubs", h.getCard(1).getRankSuit());
		assertEquals("KingClubs", h.getCard(2).getRankSuit());
		assertEquals("AceClubs", h.getCard(3).getRankSuit());
		assertEquals(11, h.getCard(0).getRank());
		assertEquals(12, h.getCard(1).getRank());
		assertEquals(13, h.getCard(2).getRank());
		assertEquals(14, h.getCard(3).getRank());
		
	}
	
	//Assure that trying to get a card that doesn't exist in a players hand is null
	public void testNoHand(){
		Hand h = new Hand();
		assertNull(h.getCard(3));
	}
	
	public void testHandCapacity(){
		Hand h = new Hand();
		h.add(new Card());
		h.add(new Card());
		h.add(new Card());
		h.add(new Card());
		h.add(new Card());
		assertEquals(5, h.cardCount());
		h.add(new Card());
		assertEquals(5, h.cardCount());
	}
}





