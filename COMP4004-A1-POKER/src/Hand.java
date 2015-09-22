import java.util.ArrayList;

public class Hand {
	
	public Hand(){
		cards = new ArrayList<Card>();
	}
	
	public Card getCard(int i){
		return cards.get(i);
	}
	
	public void add(Card newCard){
		cards.add(newCard);
	}
	
	private ArrayList<Card> cards;
}
