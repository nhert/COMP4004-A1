import junit.framework.*;


/*Important NOTE for Testing:
 * in the logic for checking a hand, hands are checked for the highest hands down to the lowest, so special cases in pair functions etc. dont need to be checked
 * for example: in checking for a pair we dont need to check for 3 of a kind because we already know there isn't one if we are checking for a pair
 * these special cases are handled in checking for the better hands first
 */
public class TestPoker extends TestCase{
	public TestPoker(String sname){
		super(sname);
	}
	
	public void testCreatePokerGame(){
		Poker p = new Poker();
		assertNotNull(p);
		assertEquals(0, p.getPokerRanking());
	}
	
	//test the high card function in the poker class
	public void testHighCard(){
		Poker p = new Poker();
		Hand h = new Hand();
		//regular cards
		h.add(new Card(2, "Spades"));
		h.add(new Card(3, "Spades"));
		h.add(new Card(4, "Spades"));
		h.add(new Card(5, "Spades"));
		h.add(new Card(10, "Spades"));
		assertEquals(10,p.GetHighCard(h));
		
		//some face cards
		Hand h2 = new Hand();
		h2.add(new Card(10, "Spades"));
		h2.add(new Card(11, "Spades"));
		h2.add(new Card(12, "Spades"));
		h2.add(new Card(13, "Spades"));
		h2.add(new Card(14, "Spades"));
		assertEquals(14,p.GetHighCard(h2));
	}
	
	//test a hand for the number of pairs it has
	public void testPairs(){
		Poker p = new Poker();
		Hand h = new Hand();
		h.add(new Card(2, "Spades"));
		h.add(new Card(2, "Hearts"));
		h.add(new Card(3, "Clubs"));
		h.add(new Card(4, "Clubs"));
		h.add(new Card(5, "Hearts"));
		assertTrue(p.HasPair(h, 1));
		assertFalse(p.HasPair(h, 2));
		
		h = new Hand();
		h.add(new Card(2, "Spades"));
		h.add(new Card(2, "Hearts"));
		h.add(new Card(3, "Clubs"));
		h.add(new Card(3, "Spades"));
		h.add(new Card(5, "Hearts"));
		assertTrue(p.HasPair(h, 2));
		assertFalse(p.HasPair(h, 1));
		
		h = new Hand();
		h.add(new Card(2, "Spades"));
		h.add(new Card(3, "Hearts"));
		h.add(new Card(4, "Clubs"));
		h.add(new Card(5, "Spades"));
		h.add(new Card(6, "Hearts"));
		assertFalse(p.HasPair(h, 2));
		assertFalse(p.HasPair(h, 1));
	}
	
	
}
