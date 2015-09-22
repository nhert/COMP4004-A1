import java.util.ArrayList;

public class Game {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test");
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
