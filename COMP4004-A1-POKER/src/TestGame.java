import junit.framework.*;


public class TestGame extends TestCase{
	public TestGame(String sname){
		super(sname);
	}
	
	public void runAll(){
		testOnePlayer();
		testTwoPlayers();
		testPlayerCapacity();
	}
	
	//test a game that only has one player functions properly
	public void testOnePlayer(){
		Game g = new Game();
		g.addPlayer();
		assertEquals(1, g.playerCount());
		g.getPlayer(0).add(new Card(10, "Hearts"));
		assertEquals("TenHearts", g.getPlayer(0).getCard(0).getRankSuit());
	}
	
	//test a game that has more than one player works correctly
	public void testTwoPlayers(){
		Game g = new Game();
		g.addPlayer();
		g.addPlayer();
		assertEquals(2,g.playerCount());
	}
	
	//test that game will only hold 4 players
	public void testPlayerCapacity(){
		Game g = new Game();
		g.addPlayer();
		g.addPlayer();
		g.addPlayer();
		g.addPlayer();
		assertEquals(4, g.playerCount());
		g.addPlayer();
		assertEquals(4, g.playerCount());
	}
	
	
	
	
}
