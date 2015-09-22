import junit.framework.*;

public class TestPoker extends TestCase{
	public TestPoker(String sname){
		super(sname);
	}
	
	public void testCreatePokerGame(){
		Poker p = new Poker();
		assertNotNull(p);
		assertEquals(0, p.getPokerRanking());
	}
	
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
	
	
}
