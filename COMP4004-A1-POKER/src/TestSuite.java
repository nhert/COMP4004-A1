import junit.framework.*;

public class TestSuite extends TestCase{
	TestCard cardSuite;
	TestDeck deckSuite;
	TestGame gameSuite;
	TestHand handSuite;
	TestPoker pokerSuite;
	
	public TestSuite(String sname){
		super(sname);
		cardSuite = new TestCard(this.getName());
		deckSuite = new TestDeck(this.getName());
		gameSuite = new TestGame(this.getName());
		handSuite = new TestHand(this.getName());
		pokerSuite = new TestPoker(this.getName());
	}
	
	public void testSuite() {
		cardSuite.runAll();
		deckSuite.runAll();
		gameSuite.runAll();
		handSuite.runAll();
		pokerSuite.runAll();
	}

}
