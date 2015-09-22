import java.util.ArrayList;
import java.util.Random;

public class Deck {
	public Deck(){
		deck = new ArrayList<Card>();
		deckCreate();
	}
	
	public void deckCreate(){
		deck.clear();
		for(int suit = 0; suit < 4; suit++){
			String suitLabel = "";
			switch(suit){
				case 0: suitLabel = "Spades";
						break;
				case 1: suitLabel = "Clubs";
						break;
				case 2: suitLabel = "Hearts";
						break;
				case 3: suitLabel = "Diamonds";
						break;
			}
			
			for(int cardNum = 2; cardNum<15; cardNum++){
				deck.add(new Card(cardNum, suitLabel));
			}
		}
	}
	
	public Card getACard(){
		Random pickCard = new Random();
		int randomIndex = pickCard.nextInt(deck.size());
		Card toReturn = deck.get(randomIndex);
		deck.remove(randomIndex);
		return toReturn;
	}
	
	private ArrayList<Card> deck;
}
