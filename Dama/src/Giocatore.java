
public class Giocatore {
	
	private int color;                                  // indica il colore delle pedine di quel giocatore: 0 bianche, 1 nere
	private boolean turn;                               // se true tocca a quel giocatore, se false è il turno dell'avversario
	private int pawn = 12;                              // numero di pedine che possiede quel giocatore
	private boolean type;                               // se true indica che si tratta del computer (IA), altrimenti è un player normale

	public Giocatore(int color,boolean turn, boolean type){
		this.color = color;
		this.turn = turn;
		this.type = type;
	}
	
	// Funzioni get e set per gli attributi

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
