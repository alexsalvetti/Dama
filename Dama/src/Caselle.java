
public class Caselle {
    
	private int x;                              // Indico la riga della casella
	private int y;                              // Indico la colonna della casella
	private int boxColor;                       // Definisco il colore della casella; 0 bianca , 1 nera
	private Pedina pedina;                      // Definisco un oggetto pedina all'interno di una casella
	
	/* Definisco i colori di ogni casella ed assegno alle caselle nere nelle prime tre righe
	 * e ultime tre righe le pedine; per le prime tre le pedine saranno nere, mentre per le
	 * ultime tre saranno bianche. Se quella casella non ha pedine setto l'oggetto pedina a null.
	 */
	
	public Caselle(int x, int y){
		this.x = x;
		this.y = y;
		if ((x+y)%2==0){
			boxColor = 1;
		}
		else{
			boxColor = 0;
		}
		if (x>=0 && x<=2){
			if (boxColor == 1){
				this.pedina = new Pedina(1,false);
			}	
			else{
				this.pedina = null;
			}
		}
		
		else if (x>=5 && x<=7){
			if (boxColor == 1){
				this.pedina = new Pedina(0,false);
			}
			else{
				this.pedina = null;
			}
		}
		else
			this.pedina = null;
		
	}		
	
	// Funzioni get e set per gli attributi
	
	public int getRow(){
		return x;
	}
	
	public int getColumn(){
		return y;
	}
	 
	public int getBoxColor(){
		return boxColor;
	}
	public Pedina getPedina(){
		return pedina;
	}
	public void setPedina(Pedina pedina){
		this.pedina = pedina;
	}
	
}
