
public class Card {
	public int getRank(){
		return myRank;
	}
	
	public String getSuit(){
		return mySuit;
	}
	
	public String getRankSuit(){
		String rankTemp = "";
		switch(myRank){
			case 2: rankTemp = "Two";
					break;
			case 3: rankTemp = "Three";
					break;
			case 4: rankTemp = "Four";
					break;
			case 5: rankTemp = "Five";
					break;
			case 6: rankTemp = "Six";
					break;
			case 7: rankTemp = "Seven";
					break;
			case 8: rankTemp = "Eight";
					break;
			case 9: rankTemp = "Nine";
					break;
			case 10: rankTemp = "Ten";
					break;
			case 11: rankTemp = "Jack";
					break;
			case 12: rankTemp = "Queen";
					break;
			case 13: rankTemp = "King";
					break;
			case 14: rankTemp = "Ace";
					break;
		}
		return rankTemp+mySuit; //Example: ThreeHearts
	}
	
	public Card(){ // default case for testing
		myRank = 2;
		mySuit = "Spades";
	}
	
	public Card(int r, String s){
		myRank = r;
		mySuit = s;
	}
	
	private int myRank;
	private String mySuit;
}
