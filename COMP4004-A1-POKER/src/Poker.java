import java.util.ArrayList;

/*Note of Rankings in Poker:
 * High Card: 1
 * One Pair: 2
 * Two Pair: 3
 * Three of a Kind: 4
 * Straight: 5
 * Flush: 6
 * Full House: 7
 * Four of a Kind: 8
 * Straight Flush: 9
 * Royal Flush: 10
 */

public class Poker {
	
	//logic: check for highest rank first then gradually move down until a match is found
	public int getPokerRanking(){
		//check rankings for all hands
		//if there is a tie find high card
		//if tied still then two winners, if not then output ranks of all the hands (by checking poker ranking)
		return 0;
	}
	
	//function for getting the high card in a hand
	//note: this is not checked in the ranking func, if player doesn't at least have a pair they automatically have high card hand
	public int GetHighCard(Hand h){
		int currentHigh = 0;
		for(int x = 0; x < 5; x++){
			if(h.getCard(x).getRank() > currentHigh){
				currentHigh = h.getCard(x).getRank();
			}
		}
		return currentHigh;
	}
}
