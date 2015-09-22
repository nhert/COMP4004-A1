import java.util.ArrayList;
import java.util.Collections;

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
	
	public boolean HasPair(Hand h, int numPairs){
		ArrayList<Integer> cards = new ArrayList<Integer>();
		ArrayList<Integer> alreadyChecked = new ArrayList<Integer>();
		int numberFound = 0;
		
		for(int x = 0; x<5; x++){
			cards.add(h.getCard(x).getRank());
		}
		
		for(int i = 0; i<5; i++){
			//if there is a pair for this number, and we haven't already found a pair for it then...
			if(Collections.frequency(cards, cards.get(i)) == 2   &&  !alreadyChecked.contains(cards.get(i))){
				//add the number to the list of numbers that were recorded as having a pair
				alreadyChecked.add(cards.get(i));
				numberFound++;
			}
		}
		
		if(numberFound == numPairs)
			return true;
		else
			return false;
	}
	
	public boolean HasNOfAKind(Hand h, int NumOfKind){
		ArrayList<Integer> cards = new ArrayList<Integer>();
		ArrayList<Integer> alreadyChecked = new ArrayList<Integer>();
		int numberFound = 0;
		
		for(int x = 0; x<5; x++){
			cards.add(h.getCard(x).getRank());
		}
		
		for(int i = 0; i<5; i++){
			//if there is a pair for this number, and we haven't already found a pair for it then...
			if(Collections.frequency(cards, cards.get(i)) == NumOfKind   &&  !alreadyChecked.contains(cards.get(i))){
				//add the number to the list of numbers that were recorded as having a pair
				alreadyChecked.add(cards.get(i));
				numberFound++;
			}
		}
		
		if(numberFound != 0)
			return true;
		else
			return false;
	}
	
	public boolean HasStraight(Hand h){
		ArrayList<Integer> cards = new ArrayList<Integer>();
		for(int x = 0; x<5; x++){
			cards.add(h.getCard(x).getRank());
		}
		
		int countIter = 0;
		int pivot = 0;
		
		for(int x = 0; x<5; x++){
			pivot = cards.get(x);
			countIter = 1;
			for(int i = 0; i<4; i++){
				if(cards.contains(pivot+1)){
					pivot++;
					countIter++;
				}
			}
			if(countIter == 5)
				break;
		}
		
		
		if(countIter == 5)
			return true;
		else
			return false;
	}
	
	public boolean HasFlush(Hand h){
		String theSuit = h.getCard(0).getSuit();
		int countSameSuit = 0;
		
		for(int x = 0; x< 5; x++){
			if(h.getCard(x).getSuit().contains(theSuit)){
				countSameSuit++;
			}
		}
		
		if(countSameSuit == 5)
			return true;
		else
			return false;
	}
	
	public boolean HasFullHouse(Hand h){
		ArrayList<Integer> cards = new ArrayList<Integer>();
		ArrayList<Integer> alreadyChecked = new ArrayList<Integer>();
		boolean foundThreeKind = false;
		boolean foundPair = false;
		for(int x = 0; x<5; x++){
			cards.add(h.getCard(x).getRank());
		}
		
		//first try to find three of a kind
		for(int i = 0; i<5; i++){
			if(Collections.frequency(cards, cards.get(i)) == 3   &&  !alreadyChecked.contains(cards.get(i))){
				alreadyChecked.add(cards.get(i));
				foundThreeKind = true;
			}
		}
		
		//then try to find a pair out of the non three of a kind numbers
		for(int i = 0; i<5; i++){
			if(Collections.frequency(cards, cards.get(i)) == 2   &&  !alreadyChecked.contains(cards.get(i))){
				alreadyChecked.add(cards.get(i));
				foundPair = true;
			}
		}
		
		if(foundThreeKind && foundPair)
			return true;
		else
			return false;
	}
	
	public boolean HasStraightFlush(Hand h){
		if(HasStraight(h) && HasFlush(h))
			return true;
		else
			return false;
	}
	
	public boolean HasStraightFromN(Hand h, int pivot){
		ArrayList<Integer> cards = new ArrayList<Integer>();
		for(int x = 0; x<5; x++){
			cards.add(h.getCard(x).getRank());
		}
		
		int countIter = 1;
		
		for(int i = 0; i<4; i++){
			if(cards.contains(pivot-1)){
				pivot--;
				countIter++;
			}
		}

		if(countIter == 5)
			return true;
		else
			return false;
	}
	
	public boolean HasRoyalFlush(Hand h){
		if(GetHighCard(h) == 14){
			if(HasStraightFromN(h,14) && HasFlush(h))
				return true;
			else
				return false;
		}else{
			return false;
		}
	}
}







