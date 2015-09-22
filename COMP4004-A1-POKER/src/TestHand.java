import junit.framework.*;

public class TestHand extends TestCase{
	public TestHand(String sname){
		super(sname);
	}
	
	public void testHandOneCardLowest(){
		Hand h = new Hand();
		assertEquals(2, h.getRank());
	}
}
