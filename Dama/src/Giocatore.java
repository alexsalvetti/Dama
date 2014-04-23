
public class Giocatore {
	
	private int color;
	private boolean turn;                               // se true tocca a quel giocatore
	private int pawn = 12;
	private boolean type;

	public Giocatore(int color,boolean turn, boolean type){
		this.color = color;
		this.turn = turn;
		this.type = type;
	}

	public boolean getTurn(){
		return turn;
	}
	
	public void setTurn(boolean turn){
		this.turn = turn;
	}
	
	public int getColor(){
		return color;
	}
	public void setColor(int color){
		this.color = color;
	}
	
	public int getPawn(){
		return pawn;
	}
	
	public void decPawn(){
		pawn = pawn -1;
	}
	public boolean getType(){
		return type;
	}
	public void resetPawn(){
		pawn = 12;
	}
	
}
