import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

/*Note of Rankings in Poker:
 * High Card: 2 to 14
 * One Pair: 15 to 27
 * Two Pair: 28 to 40
 * Three of a Kind: 41 to 53
 * Straight: 54 to 66
 * Flush: 67 to 79
 * Full House: 80 to 92
 * Four of a Kind: 93 to 105
 * Straight Flush: 106 to 118
 * Royal Flush: 119
 */

public class Poker {
	private int rank = 4;
	private int tieCount = 0;
	private Map<Integer, Integer> finalsMap;
	
	public Poker(){
		rank = 4;
		finalsMap = new HashMap<Integer, Integer>();
	}
	//logic: check for highest rank first then gradually move down until a match is found
	public int getPokerRanking(Hand h){
		//check rankings for all hands
		//if there is a tie find high card
		//if tied still then two winners, if not then output ranks of all the hands (by checking poker ranking)
		if(HasRoyalFlush(h))
			return 119;
		else if(HasStraightFlush(h))
			return 104+GetHighCard(h);
		else if(HasNOfAKind(h,4))
			return 91+FindHighestNOfAKind(h,4);
		else if(HasFullHouse(h))
			return 78+FindHighestNOfAKind(h,3);
		else if(HasFlush(h))
			return 65+GetHighCard(h);
		else if(HasStraight(h))
			return 52+GetHighCard(h);
		else if(HasNOfAKind(h,3))
			return 39+FindHighestNOfAKind(h,3);
		else if(HasPair(h,2))
			return 26+FindHighestPair(h,2);
		else if(HasPair(h,1))
			return 13+FindHighestPair(h,1);
		else
			return 0+GetHighCard(h);
	}
	
	public void FindWinner(Game currentGame){
		rank = currentGame.playerCount();
		tieCount = 0;
		ArrayList<Integer> rankingList = new ArrayList<Integer>();
		
		for(int x = 0; x<currentGame.playerCount(); x++){
			rankingList.add(getPokerRanking(currentGame.getPlayer(x)));
		}
		
		for(int y = 0; y<currentGame.playerCount(); y++){
			String rankingText = RankToText(rankingList.get(y));
			System.out.print("PLAYER (" + (y+1) + ") has POINTS (" +rankingList.get(y)+rankingText+ ")  Cards: ");
			for(int i = 0; i<5; i++){
				System.out.print(currentGame.getPlayer(y).getCard(i).getRankSuit() + " ");
			}
			System.out.println("\n\n");
		}
		
		System.out.println("Game Rankings: ");
		Map<Integer, Integer> newMap = new HashMap<Integer,Integer>();
		for(int x = 0; x<rankingList.size(); x++){
			newMap.put(x, rankingList.get(x));
		}
		ResolveRanking(currentGame,newMap);
		for(int x = 0; x<currentGame.playerCount(); x++){
			System.out.println("Player " + (x+1) + " Rank " + (finalsMap.get(x)-tieCount));
		}
		System.out.println("\n\n\n");
	}
	
	public String RankToText(int r){
		if(r == 119)
			return " | !ROYAL FLUSH!";
		else if(r >= 106)
			return " | STRAIGHT FLUSH";
		else if(r >=93)
			return " | FOUR OF A KIND";
		else if(r >=80)
			return " | FULL HOUSE";
		else if(r >=67)
			return " | FLUSH";
		else if(r >=54)
			return " | STRAIGHT";
		else if(r >=41)
			return " | THREE OF A KIND";
		else if(r >= 28)
			return " | TWO PAIRS";
		else if(r >= 15)
			return " | ONE PAIR";
		else
			return " | NO HAND, HIGH CARD";
	}
	
	public void ResolveRanking(Game g, Map<Integer,Integer> newMap){
		//Map format: PNUM, Ranking ex: <1,20> , <2, 50>
		//Tie resolution: New Map Format: <Ranking, <PNUM, Ranking>>
		//reresolve new map with getpokerrank 
		//
		newMap = sortByValue(newMap);
		Map.Entry<Integer, Integer> current = null;
		HashMap<Integer, HashMap<Integer,Integer>> resMap = new HashMap<Integer, HashMap<Integer,Integer>>();
		//group them by same values if size > 1 then reresolve
		for(Iterator<Map.Entry<Integer, Integer>> it = newMap.entrySet().iterator(); it.hasNext(); ) {
			current = it.next();
			//System.out.println("Player (" + (current.getKey()+1) + ") Has Game Rank: " + current.getValue());
			addToResolutionMap(resMap, current.getKey(), current.getValue());
		}
		//System.out.println(resMap);
		SortedSet<Integer> keys = new TreeSet<Integer>(resMap.keySet());
		
		for(Integer curK : keys){
			//System.out.println(curK);
			if(resMap.get(curK).size() > 1){//there was a tied hand in the resolution map
				//System.out.println("There was a tied hand!");
				
				if(curK == 119){ // there was a tie on royal flush
					
					for(Iterator<Map.Entry<Integer, Integer>> it = resMap.get(curK).entrySet().iterator(); it.hasNext(); ) {
						current = it.next();
						//System.out.println("Player " + (current.getKey()+1) + " Rank: " + rank);
						finalsMap.put(current.getKey(), rank);
					}
					tieCount++;
					rank--;
				}else if((curK <= 92 && curK >= 80) || (curK <=53 && curK >=41 )){ // full house or three kind, take away the three kind
					SortedSet<Integer> keys2 = new TreeSet<Integer>(resMap.get(curK).keySet());
					for(Integer key : keys2){
						//System.out.println("Inside First Takeaway Loop 92" + key);
						g.getPlayer(key).setHand(takeAwayHighestNKind(g.getPlayer(key), 3));
					}
					
					HashMap<Integer,Integer> newSubList = new HashMap<Integer,Integer>();
					
					for(Integer key : keys2){
						//System.out.println("Inside Second RList Loop 92" + key);
						newSubList.put(key,getPokerRanking(g.getPlayer(key)));
					}
					ResolveRanking(g,newSubList);
					
				}else if((curK <= 40 && curK >= 28) || (curK <= 27 && curK >= 15)){ // two pair or one pair, take away the highest pair
					SortedSet<Integer> keys2 = new TreeSet<Integer>(resMap.get(curK).keySet());
					for(Integer key : keys2){
						//System.out.println("Inside First Takeaway Loop 40" + key);
						g.getPlayer(key).setHand(takeAwayHighestPair(g.getPlayer(key)));
					}
					
					HashMap<Integer,Integer> newSubList = new HashMap<Integer,Integer>();
					
					for(Integer key : keys2){
						//System.out.println("Inside Second RList Loop 40" + key);
						newSubList.put(key,getPokerRanking(g.getPlayer(key)));
					}
					ResolveRanking(g,newSubList);
		
				}else if(curK <= 105 && curK >= 93){
					SortedSet<Integer> keys2 = new TreeSet<Integer>(resMap.get(curK).keySet());
					for(Integer key : keys2){
						//System.out.println("Inside First Takeaway Loop 92" + key);
						g.getPlayer(key).setHand(takeAwayHighestNKind(g.getPlayer(key), 4));
					}
					
					HashMap<Integer,Integer> newSubList = new HashMap<Integer,Integer>();
					
					for(Integer key : keys2){
						//System.out.println("Inside Second RList Loop 92" + key);
						newSubList.put(key,getPokerRanking(g.getPlayer(key)));
					}
					ResolveRanking(g,newSubList);
					
				}
				
				
				else{ // everything else, look at the high card to decide IF THERE IS NO OTHER CARDS AFTER THE PAIR, THEN IT IS A TIE!
					SortedSet<Integer> keys2 = new TreeSet<Integer>(resMap.get(curK).keySet());
					boolean flag = true;
					for(Integer key : keys2){
						//System.out.println("Inside First Takeaway Loop other " + (key+1));
						g.getPlayer(key).setHand(takeAwayHighestCard(g.getPlayer(key)));
						if(g.getPlayer(key).cardCount() == 0){//players out of cards to check, its a tie
							for(int x = 0; x<4; x++){//there was no tie for this rank, print out the player rank for score
								if(resMap.get(curK).containsKey(x)){
									//System.out.println("Player " + (x+1) + " Rank: " + rank + " (tie)");
									finalsMap.put(x, rank);
								}
							}
							rank--;
							flag = false;
							break;
						}
					}
					
					if(flag){//if we didnt run them out of cards to get a tie
						HashMap<Integer,Integer> newSubList = new HashMap<Integer,Integer>();
						
						for(Integer key : keys2){
							//System.out.println("Inside Second RList Loop " + key);
							newSubList.put(key,getPokerRanking(g.getPlayer(key)));
						}
						ResolveRanking(g,newSubList);
					}else{
						tieCount++;
					}
				}
			}else{//this is the only person with this rank (points)
				for(int x = 0; x<4; x++){//there was no tie for this rank, print out the player rank for score
					if(resMap.get(curK).containsKey(x)){
						//System.out.println("Player " + (x+1) + " Rank: " + rank);
						finalsMap.put(x, rank);
						rank--;
					}
				}
			}
		}
	}
	
	//Map Format: <14,<1,14>>
	public void addToResolutionMap(Map <Integer, HashMap <Integer,Integer>> map, Integer key, Integer value){
		if(!map.containsKey(value)){
		   map.put(value, new HashMap<Integer,Integer>());
		}
		map.get(value).put(key,value);
	}
	
	//ReResolve: if two players are equal in their first highest hand, need to re resolve with a kicker
	//Special Cases: 	Two pair, need to look at the second pair for higher
	//					full house, need to look at the pair, if same then tied hand
	//					
	
	
	//function for getting the high card in a hand
	//note: this is not checked in the ranking func, if player doesn't at least have a pair they automatically have high card hand
	public int GetHighCard(Hand h){
		int currentHigh = 0;
		for(int x = 0; x < h.cardCount(); x++){
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
		
		for(int x = 0; x<h.cardCount(); x++){
			cards.add(h.getCard(x).getRank());
		}
		
		for(int i = 0; i<h.cardCount(); i++){
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
	
	public int FindHighestPair(Hand h, int numPairs){
		ArrayList<Integer> cards = new ArrayList<Integer>();
		ArrayList<Integer> alreadyChecked = new ArrayList<Integer>();
		int numberFound = 0;
		
		for(int x = 0; x<h.cardCount(); x++){
			cards.add(h.getCard(x).getRank());
		}
		
		for(int i = 0; i<h.cardCount(); i++){
			//if there is a pair for this number, and we haven't already found a pair for it then...
			if(Collections.frequency(cards, cards.get(i)) == 2   &&  !alreadyChecked.contains(cards.get(i))){
				//add the number to the list of numbers that were recorded as having a pair
				alreadyChecked.add(cards.get(i));
				numberFound++;
			}
		}
		
		Collections.sort(alreadyChecked);
		Collections.reverse(alreadyChecked);
		//System.out.println(alreadyChecked.get(0));
		return alreadyChecked.get(0);
	}
	
	public int FindHighestNOfAKind(Hand h, int NumOfKind){
		ArrayList<Integer> cards = new ArrayList<Integer>();
		ArrayList<Integer> alreadyChecked = new ArrayList<Integer>();
		int numberFound = 0;
		
		for(int x = 0; x<h.cardCount(); x++){
			cards.add(h.getCard(x).getRank());
		}
		
		for(int i = 0; i<h.cardCount(); i++){
			//if there is a pair for this number, and we haven't already found a pair for it then...
			if(Collections.frequency(cards, cards.get(i)) == NumOfKind   &&  !alreadyChecked.contains(cards.get(i))){
				//add the number to the list of numbers that were recorded as having a pair
				alreadyChecked.add(cards.get(i));
				numberFound++;
			}
		}
		
		Collections.sort(alreadyChecked);
		Collections.reverse(alreadyChecked);
		//System.out.println(alreadyChecked.get(0));
		return alreadyChecked.get(0);
	}
	
	public Hand takeAwayHighestNKind(Hand h, int n){
		ArrayList<Card> temp = new ArrayList<Card>();
		for(int x = 0; x<h.cardCount(); x++){
			temp.add(h.getCard(x));
		}
		int rank = FindHighestNOfAKind(h, n);
		Hand hnew = new Hand();
		for(int y = 0; y<temp.size(); y++){
			if(temp.get(y).getRank() != rank){
				hnew.add(temp.get(y));
				//System.out.println(temp.get(y).getRankSuit());
			}
		}
		return hnew;
	}
	
	//take away all occurances of the highest card
	public Hand takeAwayHighestCard(Hand h){
		ArrayList<Card> temp = new ArrayList<Card>();
		for(int x = 0; x<h.cardCount(); x++){
			temp.add(h.getCard(x));
		}
		int high = GetHighCard(h);
		Hand hnew = new Hand();
		for(int y = 0; y<temp.size(); y++){
			if(temp.get(y).getRank() != high){
				hnew.add(temp.get(y));
				//System.out.println(temp.get(y).getRankSuit());
			}
		}
		return hnew;
	}
	
	public Hand takeAwayHighestPair(Hand h){
		ArrayList<Card> temp = new ArrayList<Card>();
		for(int x = 0; x<h.cardCount(); x++){
			temp.add(h.getCard(x));
		}
		int high = FindHighestPair(h,1);
		Hand hnew = new Hand();
		for(int y = 0; y<temp.size(); y++){
			if(temp.get(y).getRank() != high){
				hnew.add(temp.get(y));
				//System.out.println(temp.get(y).getRankSuit());
			}
		}
		return hnew;
	}
	
	public boolean HasNOfAKind(Hand h, int NumOfKind){
		ArrayList<Integer> cards = new ArrayList<Integer>();
		ArrayList<Integer> alreadyChecked = new ArrayList<Integer>();
		int numberFound = 0;
		
		for(int x = 0; x<h.cardCount(); x++){
			cards.add(h.getCard(x).getRank());
		}
		
		for(int i = 0; i<h.cardCount(); i++){
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
		for(int x = 0; x<h.cardCount(); x++){
			cards.add(h.getCard(x).getRank());
		}
		
		int countIter = 0;
		int pivot = 0;
		
		for(int x = 0; x<h.cardCount(); x++){
			pivot = cards.get(x);
			countIter = 1;
			for(int i = 0; i<h.cardCount()-1; i++){
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
		
		for(int x = 0; x< h.cardCount(); x++){
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
		for(int x = 0; x<h.cardCount(); x++){
			cards.add(h.getCard(x).getRank());
		}
		
		//first try to find three of a kind
		for(int i = 0; i<h.cardCount(); i++){
			if(Collections.frequency(cards, cards.get(i)) == 3   &&  !alreadyChecked.contains(cards.get(i))){
				alreadyChecked.add(cards.get(i));
				foundThreeKind = true;
			}
		}
		
		//then try to find a pair out of the non three of a kind numbers
		for(int i = 0; i<h.cardCount(); i++){
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
		for(int x = 0; x<h.cardCount(); x++){
			cards.add(h.getCard(x).getRank());
		}
		
		int countIter = 1;
		
		for(int i = 0; i<h.cardCount()-1; i++){
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
	
	public static <K, V extends Comparable<? super V>> Map<K, V> 
    sortByValue( Map<K, V> map )
{
    List<Map.Entry<K, V>> list =
        new LinkedList<>( map.entrySet() );
    Collections.sort( list, new Comparator<Map.Entry<K, V>>()
    {
        @Override
        public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
        {
            return (o2.getValue()).compareTo( o1.getValue() );
        }
    } );

    Map<K, V> result = new LinkedHashMap<>();
    for (Map.Entry<K, V> entry : list)
    {
        result.put( entry.getKey(), entry.getValue() );
    }
    return result;
}
}







