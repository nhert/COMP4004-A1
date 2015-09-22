import junit.framework.*;

public class TestHand extends TestCase{
	public TestHand(String sname){
		super(sname);
	}
	
	public void testHandOneCardLowest(){
		Hand h = new Hand(); // default case where there are no parms
		h.add(new Card());
		assertEquals("TwoSpades", h.getCard(0).getRankSuit());
		
		Hand h2 = new Hand();
		h2.add(new Card(2, "Hearts"));
		assertEquals("TwoHearts", h2.getCard(0).getRankSuit());
	}
}
