import junit.framework.*;

public class TestCard extends TestCase{
	
	public TestCard(String sname){
		super(sname);
	}
	
	public void testRankLowest(){
		Card c = new Card();
		assertEquals(2, c.getRank());
	}
}
