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
	
	
}
