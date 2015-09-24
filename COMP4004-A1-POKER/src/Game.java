import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	public static void main(String[] args) {
		
		int players = 0;
		int round = 1;
		Scanner reader = new Scanner(System.in);
		
		while(true){
			System.out.println("------------------------------------\nNEW ROUND (" + round + ")");
			System.out.println("How many Players this Round? (Min 2, Max 4) or 'Q' to quit: ");
			String userin = reader.nextLine();
			if(userin.contains("q") || userin.contains("Q")){
				break;
			}
			
			int pCount = Integer.parseInt(userin);
			if(pCount > 4)
				pCount = 4;
			if(pCount < 2)
				pCount = 2;
			System.out.println("\nStarting Round with (" + pCount + ") Players.\n");
			
			Deck d = new Deck();
			Game g = new Game();
			Poker p = new Poker();
			
			for(int x = 0; x<pCount; x++){
				g.addPlayer();
				for(int i = 0; i<5; i++)
					g.getPlayer(x).add(d.getACard());
			}
			
			p.FindWinner(g);
			round ++;
		}
	}
	
	public Game(){
		players = new ArrayList<Hand>();
	}
	
	public int playerCount(){
		return players.size();
	}
	
	public void addPlayer(){
		if(players.size() < 4){
			players.add(new Hand());
		}else{
			return;
		}
	}
	
	public Hand getPlayer(int i){
		return players.get(i);
	}
	
	private ArrayList<Hand> players;
}
