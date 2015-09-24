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
	
	public void setHand(Hand h){
		cards.clear();
		for(int x = 0; x<h.cardCount(); x++){
			cards.add(h.getCard(x));
		}
	}
	
	public int cardCount(){
		return cards.size();
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
