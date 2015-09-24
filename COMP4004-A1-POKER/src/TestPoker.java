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
	
	public void runAll(){
		testCreatePokerGame();
		testHighCard();
		testPairs();
		testNumKind();
		testRegularStraight();
		testRegularFlush();
		testFullHouse();
		testStraightFlush();
		testRoyalFlush();
		testGameResolution();
	}
	
	public void testCreatePokerGame(){
		Poker p = new Poker();
		assertNotNull(p);
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
		//one pair
		h.add(new Card(2, "Spades"));
		h.add(new Card(2, "Hearts"));
		h.add(new Card(3, "Clubs"));
		h.add(new Card(4, "Clubs"));
		h.add(new Card(5, "Hearts"));
		assertTrue(p.HasPair(h, 1));
		assertFalse(p.HasPair(h, 2));
		
		//two pair
		h = new Hand();
		h.add(new Card(2, "Spades"));
		h.add(new Card(2, "Hearts"));
		h.add(new Card(3, "Clubs"));
		h.add(new Card(3, "Spades"));
		h.add(new Card(5, "Hearts"));
		assertTrue(p.HasPair(h, 2));
		assertFalse(p.HasPair(h, 1));
		
		//no pairs
		h = new Hand();
		h.add(new Card(2, "Spades"));
		h.add(new Card(3, "Hearts"));
		h.add(new Card(4, "Clubs"));
		h.add(new Card(5, "Spades"));
		h.add(new Card(6, "Hearts"));
		assertFalse(p.HasPair(h, 2));
		assertFalse(p.HasPair(h, 1));
	}
	
	//test a hand for having a certain number of a "kind"
	public void testNumKind(){
		Poker p = new Poker();
		Hand h = new Hand();
		//three of a kind
		h.add(new Card(2, "Spades"));
		h.add(new Card(2, "Hearts"));
		h.add(new Card(2, "Clubs"));
		h.add(new Card(3, "Clubs"));
		h.add(new Card(11, "Hearts"));
		assertTrue(p.HasNOfAKind(h, 3));
		assertFalse(p.HasNOfAKind(h, 4));
		
		//four of a kind
		h = new Hand();
		h.add(new Card(2, "Spades"));
		h.add(new Card(2, "Hearts"));
		h.add(new Card(2, "Clubs"));
		h.add(new Card(2, "Clubs"));
		h.add(new Card(11, "Hearts"));
		assertTrue(p.HasNOfAKind(h, 4));
		assertFalse(p.HasNOfAKind(h, 3));
		
		//only a pair
		h = new Hand();
		h.add(new Card(2, "Spades"));
		h.add(new Card(2, "Hearts"));
		h.add(new Card(3, "Clubs"));
		h.add(new Card(4, "Clubs"));
		h.add(new Card(11, "Hearts"));
		assertFalse(p.HasNOfAKind(h, 4));
		assertFalse(p.HasNOfAKind(h, 3));
	}
	
	public void testRegularStraight(){
		Poker p = new Poker();
		Hand h = new Hand();
		//normal straight
		h.add(new Card(2, "Spades"));
		h.add(new Card(3, "Hearts"));
		h.add(new Card(4, "Clubs"));
		h.add(new Card(5, "Clubs"));
		h.add(new Card(6, "Hearts"));
		assertTrue(p.HasStraight(h));
		
		//almost a straight but off by 1
		h = new Hand();
		h.add(new Card(2, "Spades"));
		h.add(new Card(3, "Hearts"));
		h.add(new Card(4, "Clubs"));
		h.add(new Card(5, "Clubs"));
		h.add(new Card(10, "Hearts"));
		assertFalse(p.HasStraight(h));
		
		//descending order
		h = new Hand();
		h.add(new Card(10, "Spades"));
		h.add(new Card(9, "Hearts"));
		h.add(new Card(8, "Clubs"));
		h.add(new Card(7, "Clubs"));
		h.add(new Card(6, "Hearts"));
		assertTrue(p.HasStraight(h));
		
		//face cards
		h = new Hand();
		h.add(new Card(14, "Spades"));
		h.add(new Card(13, "Hearts"));
		h.add(new Card(12, "Clubs"));
		h.add(new Card(11, "Clubs"));
		h.add(new Card(10, "Hearts"));
		assertTrue(p.HasStraight(h));
	}
	
	public void testRegularFlush(){
		Poker p = new Poker();
		Hand h = new Hand();
		//regular flush
		h.add(new Card(2, "Spades"));
		h.add(new Card(3, "Spades"));
		h.add(new Card(2, "Spades"));
		h.add(new Card(5, "Spades"));
		h.add(new Card(10, "Spades"));
		assertTrue(p.HasFlush(h));
		
		//almost flush, off by one
		h = new Hand();
		h.add(new Card(2, "Spades"));
		h.add(new Card(3, "Clubs"));
		h.add(new Card(2, "Spades"));
		h.add(new Card(5, "Spades"));
		h.add(new Card(10, "Hearts"));
		assertFalse(p.HasFlush(h));
	}
	
	public void testFullHouse(){
		Poker p = new Poker();
		Hand h = new Hand();
		//a full house
		h.add(new Card(2, "Spades"));
		h.add(new Card(2, "Hearts"));
		h.add(new Card(2, "Clubs"));
		h.add(new Card(3, "Spades"));
		h.add(new Card(3, "Spades"));
		assertTrue(p.HasFullHouse(h));
		
		//just three of a kind with no pair
		h = new Hand();
		h.add(new Card(2, "Spades"));
		h.add(new Card(2, "Hearts"));
		h.add(new Card(2, "Clubs"));
		h.add(new Card(5, "Spades"));
		h.add(new Card(6, "Spades"));
		assertFalse(p.HasFullHouse(h));
		
		//just pair with no three of a kind
		h = new Hand();
		h.add(new Card(2, "Spades"));
		h.add(new Card(2, "Hearts"));
		h.add(new Card(3, "Clubs"));
		h.add(new Card(4, "Spades"));
		h.add(new Card(5, "Spades"));
		assertFalse(p.HasFullHouse(h));		
	}
	
	public void testStraightFlush(){
		Poker p = new Poker();
		Hand h = new Hand();
		//a straight flush
		h.add(new Card(2, "Spades"));
		h.add(new Card(3, "Spades"));
		h.add(new Card(4, "Spades"));
		h.add(new Card(5, "Spades"));
		h.add(new Card(6, "Spades"));
		assertTrue(p.HasStraightFlush(h));
		
		h = new Hand();
		//a straight with no flush
		h.add(new Card(2, "Spades"));
		h.add(new Card(3, "Hearts"));
		h.add(new Card(4, "Spades"));
		h.add(new Card(5, "Clubs"));
		h.add(new Card(6, "Spades"));
		assertFalse(p.HasStraightFlush(h));
		
		h = new Hand();
		//a flush with no straight
		h.add(new Card(2, "Hearts"));
		h.add(new Card(10, "Hearts"));
		h.add(new Card(4, "Hearts"));
		h.add(new Card(9, "Hearts"));
		h.add(new Card(6, "Hearts"));
		assertFalse(p.HasStraightFlush(h));
	}
	
	public void testRoyalFlush(){
		Poker p = new Poker();
		Hand h = new Hand();
		//a Royal flush!
		h.add(new Card(14, "Spades"));
		h.add(new Card(13, "Spades"));
		h.add(new Card(12, "Spades"));
		h.add(new Card(11, "Spades"));
		h.add(new Card(10, "Spades"));
		assertTrue(p.HasRoyalFlush(h));
		
		h = new Hand();
		//a straight and flush with no ace
		h.add(new Card(9, "Spades"));
		h.add(new Card(13, "Spades"));
		h.add(new Card(12, "Spades"));
		h.add(new Card(11, "Spades"));
		h.add(new Card(10, "Spades"));
		assertFalse(p.HasRoyalFlush(h));
		
		h = new Hand();
		//a straight with ace with no flush
		h.add(new Card(14, "Hearts"));
		h.add(new Card(13, "Spades"));
		h.add(new Card(12, "Spades"));
		h.add(new Card(11, "Clubs"));
		h.add(new Card(10, "Spades"));
		assertFalse(p.HasRoyalFlush(h));
		
		h = new Hand();
		//a flush with ace but no straight
		h.add(new Card(14, "Spades"));
		h.add(new Card(13, "Spades"));
		h.add(new Card(12, "Spades"));
		h.add(new Card(11, "Spades"));
		h.add(new Card(9, "Spades"));
		assertFalse(p.HasRoyalFlush(h));
	}
	
	public void testHandRankings(){
		Poker p = new Poker();
		Hand h = new Hand();
		//royal flush = 10
		h.add(new Card(14, "Spades"));
		h.add(new Card(13, "Spades"));
		h.add(new Card(12, "Spades"));
		h.add(new Card(11, "Spades"));
		h.add(new Card(10, "Spades"));
		//assertEquals(10,p.getPokerRanking(h));
		
		h = new Hand();
		//straight flush = 9
		h.add(new Card(9, "Spades"));
		h.add(new Card(13, "Spades"));
		h.add(new Card(12, "Spades"));
		h.add(new Card(11, "Spades"));
		h.add(new Card(10, "Spades"));
		//assertEquals(9,p.getPokerRanking(h));
		
		h = new Hand();
		//four of kind = 8
		h.add(new Card(9, "Spades"));
		h.add(new Card(9, "Diamonds"));
		h.add(new Card(9, "Hearts"));
		h.add(new Card(9, "Spades"));
		h.add(new Card(10, "Clubs"));
		//assertEquals(8,p.getPokerRanking(h));
		
		h = new Hand();
		//full house = 7
		h.add(new Card(9, "Spades"));
		h.add(new Card(9, "Hearts"));
		h.add(new Card(9, "Spades"));
		h.add(new Card(10, "Diamonds"));
		h.add(new Card(10, "Spades"));
		//assertEquals(7,p.getPokerRanking(h));
		
		h = new Hand();
		//Flush = 6
		h.add(new Card(9, "Spades"));
		h.add(new Card(5, "Spades"));
		h.add(new Card(12, "Spades"));
		h.add(new Card(2, "Spades"));
		h.add(new Card(10, "Spades"));
		//assertEquals(6,p.getPokerRanking(h));
		
		h = new Hand();
		//straight = 5
		h.add(new Card(2, "Spades"));
		h.add(new Card(3, "Hearts"));
		h.add(new Card(4, "Spades"));
		h.add(new Card(5, "Diamonds"));
		h.add(new Card(6, "Spades"));
		//assertEquals(5,p.getPokerRanking(h));
		
		h = new Hand();
		//three of kind = 4
		h.add(new Card(9, "Spades"));
		h.add(new Card(9, "Hearts"));
		h.add(new Card(9, "Spades"));
		h.add(new Card(12, "Diamonds"));
		h.add(new Card(10, "Spades"));
		//assertEquals(4,p.getPokerRanking(h));
		
		h = new Hand();
		//two pair = 3
		h.add(new Card(9, "Spades"));
		h.add(new Card(9, "Hearts"));
		h.add(new Card(10, "Spades"));
		h.add(new Card(10, "Diamonds"));
		h.add(new Card(5, "Spades"));
		//assertEquals(3,p.getPokerRanking(h));
		
		h = new Hand();
		//pair = 2
		h.add(new Card(9, "Spades"));
		h.add(new Card(9, "Hearts"));
		h.add(new Card(5, "Spades"));
		h.add(new Card(12, "Diamonds"));
		h.add(new Card(10, "Spades"));
		//assertEquals(2,p.getPokerRanking(h));
		
		h = new Hand();
		//high card default = 1
		h.add(new Card(9, "Spades"));
		h.add(new Card(2, "Hearts"));
		h.add(new Card(5, "Spades"));
		h.add(new Card(12, "Diamonds"));
		h.add(new Card(10, "Spades"));
		//assertEquals(1,p.getPokerRanking(h));
	}
	
	public void testGameResolution(){
		Game g = new Game();
		Poker p = new Poker();
		//TESTING WHERE PLAYERS EACH HAVE A PAIR
		g.addPlayer();
		g.addPlayer();
		//player 1 has a pair of 5s
		g.getPlayer(0).add(new Card(5, "Hearts"));
		g.getPlayer(0).add(new Card(5, "Diamonds"));
		g.getPlayer(0).add(new Card(10, "Clubs"));
		g.getPlayer(0).add(new Card(9, "Hearts"));
		g.getPlayer(0).add(new Card(8, "Hearts"));
		//player 2 has a pair of 5s as well
		g.getPlayer(1).add(new Card(5, "Hearts"));
		g.getPlayer(1).add(new Card(5, "Diamonds"));
		g.getPlayer(1).add(new Card(10, "Hearts"));
		g.getPlayer(1).add(new Card(9, "Hearts"));
		g.getPlayer(1).add(new Card(7, "Hearts"));
		p.FindWinner(g);
		
		//TESTING WHERE PLAYERS EACH HAVE JUST A HIGH CARD
		g = new Game();
		g.addPlayer();
		g.addPlayer();
		//player 1 has a pair of 5s
		g.getPlayer(0).add(new Card(2, "Hearts"));
		g.getPlayer(0).add(new Card(5, "Diamonds"));
		g.getPlayer(0).add(new Card(10, "Clubs"));
		g.getPlayer(0).add(new Card(9, "Hearts"));
		g.getPlayer(0).add(new Card(8, "Hearts"));
		//player 2 has a pair of 5s as well
		g.getPlayer(1).add(new Card(2, "Hearts"));
		g.getPlayer(1).add(new Card(5, "Diamonds"));
		g.getPlayer(1).add(new Card(10, "Hearts"));
		g.getPlayer(1).add(new Card(9, "Hearts"));
		g.getPlayer(1).add(new Card(7, "Hearts"));
		p.FindWinner(g);
		
		//TESTING WHERE PLAYERS EACH HAVE TWO PAIR
				g = new Game();
				g.addPlayer();
				g.addPlayer();
				//player 1 has a pair of 5s
				g.getPlayer(0).add(new Card(2, "Hearts"));
				g.getPlayer(0).add(new Card(2, "Diamonds"));
				g.getPlayer(0).add(new Card(5, "Clubs"));
				g.getPlayer(0).add(new Card(5, "Hearts"));
				g.getPlayer(0).add(new Card(8, "Hearts"));
				//player 2 has a pair of 5s as well
				g.getPlayer(1).add(new Card(2, "Hearts"));
				g.getPlayer(1).add(new Card(2, "Diamonds"));
				g.getPlayer(1).add(new Card(5, "Hearts"));
				g.getPlayer(1).add(new Card(5, "Hearts"));
				g.getPlayer(1).add(new Card(7, "Hearts"));
				p.FindWinner(g);
				
				//TESTING WHERE PLAYERS EACH HAVE THREE OF A KIND SAME VALUE (shouldnt be possible by current rules)
				g = new Game();
				g.addPlayer();
				g.addPlayer();
				//player 1 has a pair of 5s
				g.getPlayer(0).add(new Card(2, "Hearts"));
				g.getPlayer(0).add(new Card(2, "Diamonds"));
				g.getPlayer(0).add(new Card(2, "Clubs"));
				g.getPlayer(0).add(new Card(5, "Hearts"));
				g.getPlayer(0).add(new Card(4, "Hearts"));
				//player 2 has a pair of 5s as well
				g.getPlayer(1).add(new Card(2, "Hearts"));
				g.getPlayer(1).add(new Card(2, "Diamonds"));
				g.getPlayer(1).add(new Card(2, "Hearts"));
				g.getPlayer(1).add(new Card(5, "Hearts"));
				g.getPlayer(1).add(new Card(3, "Hearts"));
				p.FindWinner(g);
				
				//TESTING WHERE PLAYERS EACH HAVE STRAIGHT (this should always resolve to a tie if they have the same high card)
				g = new Game();
				g.addPlayer();
				g.addPlayer();
				//player 1 has a pair of 5s
				g.getPlayer(0).add(new Card(2, "Hearts"));
				g.getPlayer(0).add(new Card(3, "Diamonds"));
				g.getPlayer(0).add(new Card(4, "Clubs"));
				g.getPlayer(0).add(new Card(5, "Hearts"));
				g.getPlayer(0).add(new Card(6, "Hearts"));
				//player 2 has a pair of 5s as well
				g.getPlayer(1).add(new Card(2, "Hearts"));
				g.getPlayer(1).add(new Card(3, "Diamonds"));
				g.getPlayer(1).add(new Card(4, "Hearts"));
				g.getPlayer(1).add(new Card(5, "Hearts"));
				g.getPlayer(1).add(new Card(6, "Hearts"));
				p.FindWinner(g);
				
				//TESTING WHERE PLAYERS EACH HAVE FLUSH
				g = new Game();
				g.addPlayer();
				g.addPlayer();
				//player 1 has a pair of 5s
				g.getPlayer(0).add(new Card(10, "Diamonds"));
				g.getPlayer(0).add(new Card(9, "Diamonds"));
				g.getPlayer(0).add(new Card(8, "Diamonds"));
				g.getPlayer(0).add(new Card(7, "Diamonds"));
				g.getPlayer(0).add(new Card(4, "Diamonds"));
				//player 2 has a pair of 5s as well
				g.getPlayer(1).add(new Card(10, "Hearts"));
				g.getPlayer(1).add(new Card(9, "Hearts"));
				g.getPlayer(1).add(new Card(8, "Hearts"));
				g.getPlayer(1).add(new Card(7, "Hearts"));
				g.getPlayer(1).add(new Card(3, "Hearts"));
				p.FindWinner(g);
				
				//TESTING WHERE PLAYERS EACH HAVE FULL HOUSE
				g = new Game();
				g.addPlayer();
				g.addPlayer();
				//player 1 has a pair of 5s
				g.getPlayer(0).add(new Card(9, "Hearts"));
				g.getPlayer(0).add(new Card(9, "Diamonds"));
				g.getPlayer(0).add(new Card(9, "Clubs"));
				g.getPlayer(0).add(new Card(8, "Hearts"));
				g.getPlayer(0).add(new Card(8, "Hearts"));
				//player 2 has a pair of 5s as well
				g.getPlayer(1).add(new Card(9, "Hearts"));
				g.getPlayer(1).add(new Card(9, "Diamonds"));
				g.getPlayer(1).add(new Card(9, "Hearts"));
				g.getPlayer(1).add(new Card(7, "Hearts"));
				g.getPlayer(1).add(new Card(7, "Hearts"));
				p.FindWinner(g);
				
				//TESTING WHERE PLAYERS EACH HAVE FOUR OF KIND (this shouldnt realistically happen, one player will always have a higher hand)
				g = new Game();
				g.addPlayer();
				g.addPlayer();
				//player 1 has a pair of 5s
				g.getPlayer(0).add(new Card(2, "Hearts"));
				g.getPlayer(0).add(new Card(2, "Diamonds"));
				g.getPlayer(0).add(new Card(2, "Clubs"));
				g.getPlayer(0).add(new Card(2, "Hearts"));
				g.getPlayer(0).add(new Card(8, "Hearts"));
				//player 2 has a pair of 5s as well
				g.getPlayer(1).add(new Card(2, "Hearts"));
				g.getPlayer(1).add(new Card(2, "Diamonds"));
				g.getPlayer(1).add(new Card(2, "Hearts"));
				g.getPlayer(1).add(new Card(2, "Hearts"));
				g.getPlayer(1).add(new Card(7, "Hearts"));
				p.FindWinner(g);
				
				//TESTING WHERE PLAYERS EACH HAVE STRAIGHT FLUSH (again, should always result in a tie!)
				g = new Game();
				g.addPlayer();
				g.addPlayer();
				//player 1 has a pair of 5s
				g.getPlayer(0).add(new Card(10, "Hearts"));
				g.getPlayer(0).add(new Card(9, "Hearts"));
				g.getPlayer(0).add(new Card(8, "Hearts"));
				g.getPlayer(0).add(new Card(7, "Hearts"));
				g.getPlayer(0).add(new Card(6, "Hearts"));
				//player 2 has a pair of 5s as well
				g.getPlayer(1).add(new Card(10, "Diamonds"));
				g.getPlayer(1).add(new Card(9, "Diamonds"));
				g.getPlayer(1).add(new Card(8, "Diamonds"));
				g.getPlayer(1).add(new Card(7, "Diamonds"));
				g.getPlayer(1).add(new Card(6, "Diamonds"));
				p.FindWinner(g);
				
				//TESTING WHERE PLAYERS EACH HAVE ROYAL FLUSH (SHOULD RESULT IN TIE)
				g = new Game();
				g.addPlayer();
				g.addPlayer();
				//player 1 has a pair of 5s
				g.getPlayer(0).add(new Card(14, "Diamonds"));
				g.getPlayer(0).add(new Card(13, "Diamonds"));
				g.getPlayer(0).add(new Card(12, "Diamonds"));
				g.getPlayer(0).add(new Card(11, "Diamonds"));
				g.getPlayer(0).add(new Card(10, "Diamonds"));
				//player 2 has a pair of 5s as well
				g.getPlayer(1).add(new Card(14, "Hearts"));
				g.getPlayer(1).add(new Card(13, "Hearts"));
				g.getPlayer(1).add(new Card(12, "Hearts"));
				g.getPlayer(1).add(new Card(11, "Hearts"));
				g.getPlayer(1).add(new Card(10, "Hearts"));
				p.FindWinner(g);
				
				
				
				//TESTING WHERE THERE IS A TIE AMONG 4 PLAYERS
				g = new Game();
				g.addPlayer();
				g.addPlayer();
				g.addPlayer();
				g.addPlayer();
				//player 1 has a pair of 5s
				g.getPlayer(0).add(new Card(14, "Diamonds"));
				g.getPlayer(0).add(new Card(13, "Diamonds"));
				g.getPlayer(0).add(new Card(12, "Diamonds"));
				g.getPlayer(0).add(new Card(11, "Diamonds"));
				g.getPlayer(0).add(new Card(10, "Diamonds"));
				//player 1 has a pair of 5s
				g.getPlayer(1).add(new Card(10, "Diamonds"));
				g.getPlayer(1).add(new Card(10, "Diamonds"));
				g.getPlayer(1).add(new Card(10, "Diamonds"));
				g.getPlayer(1).add(new Card(9, "Diamonds"));
				g.getPlayer(1).add(new Card(8, "Diamonds"));
				//player 1 has a pair of 5s
				g.getPlayer(2).add(new Card(10, "Diamonds"));
				g.getPlayer(2).add(new Card(10, "Diamonds"));
				g.getPlayer(2).add(new Card(10, "Diamonds"));
				g.getPlayer(2).add(new Card(9, "Diamonds"));
				g.getPlayer(2).add(new Card(8, "Diamonds"));
				//player 1 has a pair of 5s
				g.getPlayer(3).add(new Card(6, "Diamonds"));
				g.getPlayer(3).add(new Card(7, "Hearts"));
				g.getPlayer(3).add(new Card(2, "Diamonds"));
				g.getPlayer(3).add(new Card(9, "Diamonds"));
				g.getPlayer(3).add(new Card(10, "Diamonds"));
				p.FindWinner(g);
				
				
				//TESTING WHERE THERE IS A TIE AMONG 4 PLAYERS with 2 ties!
				g = new Game();
				g.addPlayer();
				g.addPlayer();
				g.addPlayer();
				g.addPlayer();
				//player 1 has a pair of 5s
				g.getPlayer(0).add(new Card(14, "Diamonds"));
				g.getPlayer(0).add(new Card(13, "Diamonds"));
				g.getPlayer(0).add(new Card(12, "Diamonds"));
				g.getPlayer(0).add(new Card(11, "Diamonds"));
				g.getPlayer(0).add(new Card(10, "Diamonds"));
				//player 1 has a pair of 5s
				g.getPlayer(1).add(new Card(10, "Diamonds"));
				g.getPlayer(1).add(new Card(10, "Diamonds"));
				g.getPlayer(1).add(new Card(10, "Diamonds"));
				g.getPlayer(1).add(new Card(9, "Diamonds"));
				g.getPlayer(1).add(new Card(8, "Diamonds"));
				//player 1 has a pair of 5s
				g.getPlayer(2).add(new Card(10, "Diamonds"));
				g.getPlayer(2).add(new Card(10, "Diamonds"));
				g.getPlayer(2).add(new Card(10, "Diamonds"));
				g.getPlayer(2).add(new Card(9, "Diamonds"));
				g.getPlayer(2).add(new Card(8, "Diamonds"));
				//player 1 has a pair of 5s
				g.getPlayer(3).add(new Card(14, "Diamonds"));
				g.getPlayer(3).add(new Card(13, "Diamonds"));
				g.getPlayer(3).add(new Card(12, "Diamonds"));
				g.getPlayer(3).add(new Card(11, "Diamonds"));
				g.getPlayer(3).add(new Card(10, "Diamonds"));
				p.FindWinner(g);
	}
}


















