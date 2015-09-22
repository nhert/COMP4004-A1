import java.util.ArrayList;

public class Hand {
	
	public Hand(){
		cards = new ArrayList<Card>();
	}
	
	public Card getCard(int i){
		if(cards.size() >= i){
			return cards.get(i);
		}else{
			return null;
		}	
	}
	
	public void add(Card newCard){
		if(cards.size() == 5){
			return;
		}else{
			cards.add(newCard);
		}
	}
	
	private ArrayList<Card> cards;
}
