import java.util.ArrayList;



public class Scacchiera {

	private Caselle[][] casella= new Caselle[8][8];                                  // Definisco un array bidimensionale composto da 64 caselle
	private Giocatore p1,p2;                                                         // Passo i riferimenti dei giocatori alla scacchiera
	private int color1,color2,gap,gap2;                                              // Variabili che vengono utilizzate per invertire i controlli sulla scacchiera per i movimenti o per le mangiate, per cambiare il verso dall'alto verso il basso o viceversa
	private ArrayList<Movimento> listMoves = new ArrayList<Movimento>();             // Lista che conterrà alcune delle mosse ammesse a quel giocatore in quel turno
	private static boolean lock = false;                                             // variabile statica che viene settata a true in caso di una mangiata multipla, in modo da non eseguire altre operazioni fino alla conclusione della stessa

	/* Nel costruttore costruisco la scacchiera con un array bidimensionale contenente le caselle,
	 * quindi un array di tipo Caselle
	 */
	
	public Scacchiera(Giocatore p1, Giocatore p2){

		int x,y;
		this.p1 = p1;
		this.p2 = p2;

		for (x=0; x<8; x++){
			for (y=0; y<8; y++){
				casella[x][y] = new Caselle(x,y);	
			}
		}

	}

	/* Funzione che inserisce nell'array listMoves possibili mangiate che può fare il giocatore: 
	 * La funzione è divisa in due parti, ovvero canEat e calculateEat; la prima, visto che il
	 * controllo viene fatto su tutta la scacchiera in quanto le mangiate nella dama italiana
	 * sono obbligatorie e vanno controllate tutte subito, serve per fare in modo di non 
	 * effettuare un controllo che vada fuori dai bordi della scacchiera, generando degli errori.
	 */
	
	public void canEat(){
		if (listMoves.size()==0){
		int x,y;
		for(x=0; x<8; x++){
			for(y=0; y<8; y++){
				if (casella[x][y].getBoxColor()==1){
					if (casella[x][y].getPedina()!=null && p1.getTurn()==true && casella[x][y].getPedina().getType()==false && (x>=2 && x<8)){  // PEDINA BIANCA
						color1=0;
						color2=1;
						gap=1;
						gap2=1;
						calculateEat(x,y,color1,color2,gap,gap2);
					}
					if (casella[x][y].getPedina()!=null && p1.getTurn()==false && casella[x][y].getPedina().getType()==false && (x>=0 && x<6)){  // PEDINA NERA
						color1=1;
						color2=0;
						gap=-1;
						gap2=1;
						calculateEat(x,y,color1,color2,gap,gap2);
					}
					if (casella[x][y].getPedina()!=null && p1.getTurn()==true && casella[x][y].getPedina().getType()==true){    // DAMONE BIANCO
						color1=0;
						color2=1;
						if (x<2){
							gap=-1;
							gap2=1;
							calculateEat(x,y,color1,color2,gap,gap2);	
						}
						else if (x>=2 && x<=5){
							gap=1;
							gap2=1;
							calculateEat(x,y,color1,color2,gap,gap2);
							gap=-1;
							calculateEat(x,y,color1,color2,gap,gap2);
						}
						else{
							gap=1;
							gap2=1;
							calculateEat(x,y,color1,color2,gap,gap2);
						}
					}
					if (casella[x][y].getPedina()!=null && p1.getTurn()==false && casella[x][y].getPedina().getType()==true){    // DAMONE NERO
						color1=1;
						color2=0;
						if (x<2){
							gap=-1;
							gap2=1;
							calculateEat(x,y,color1,color2,gap,gap2);
						}
						else if (x>=2 && x<=5){
							gap=1;
							gap2=1;
							calculateEat(x,y,color1,color2,gap,gap2);
							gap=-1;
							calculateEat(x,y,color1,color2,gap,gap2);
						}
						else{
							gap=1;
							gap2=1;
							calculateEat(x,y,color1,color2,gap,gap2);
						}
					}
				}
			}
		}
		}
	}

	/* Questa è la seconda parte della funzione della mangiata. Viene chiamata direttamente all'interno
	 * della canEat ed inserisce il movimento completo della mangiata all'interno dell'array listMoves.
	 */
	
	private void calculateEat(int x, int y, int color1, int color2, int gap, int gap2){

		if(y>1 && y<6){		
			if (casella[x-gap][y+gap2].getPedina()!=null && casella[x][y].getPedina().getColor()==color1 && casella[x-(gap*2)][y+(gap2*2)].getPedina() == null
					&& casella[x-gap][y+gap2].getPedina().getColor()==color2 && 
					((casella[x][y].getPedina().getType()==true && casella[x-gap][y+gap2].getPedina().getType()==false) ||
							(casella[x][y].getPedina().getType()==true && casella[x-gap][y+gap2].getPedina().getType()==true) ||
							(casella[x][y].getPedina().getType()==false && casella[x-gap][y+gap2].getPedina().getType()==false) )){
				Movimento m1 = new Movimento(x,y,x-(gap*2),y+(gap2*2),casella[x][y].getPedina(),2);
				this.listMoves.add(m1);
			}
			if (casella[x-gap][y-gap2].getPedina()!=null && casella[x][y].getPedina().getColor()==color1 && casella[x-(gap*2)][y-(gap2*2)].getPedina() == null 
					&& casella[x-gap][y-gap2].getPedina().getColor()==color2 && 
					((casella[x][y].getPedina().getType()==true && casella[x-gap][y-gap2].getPedina().getType()==false) ||
							(casella[x][y].getPedina().getType()==true && casella[x-gap][y-gap2].getPedina().getType()==true) ||
							(casella[x][y].getPedina().getType()==false && casella[x-gap][y-gap2].getPedina().getType()==false) )){
				Movimento m2 = new Movimento(x,y,x-(gap*2),y-(gap2*2),casella[x][y].getPedina(),2);
				this.listMoves.add(m2);
			}
		}	    		
		else if (y<=1){
			if (casella[x-gap][y+gap2].getPedina()!=null && casella[x][y].getPedina().getColor()==color1 && casella[x-(gap*2)][y+(gap2*2)].getPedina() == null //destra
					&& casella[x-gap][y+gap2].getPedina().getColor()==color2 && ((casella[x][y].getPedina().getType()==true && casella[x-gap][y+gap2].getPedina().getType()==false) || (casella[x][y].getPedina().getType()==true && casella[x-gap][y+gap2].getPedina().getType()==true) || (casella[x][y].getPedina().getType()==false && casella[x-gap][y+gap2].getPedina().getType()==false) )){
				Movimento m1 = new Movimento(x,y,x-(gap*2),y+(gap2*2),casella[x][y].getPedina(),2);
				this.listMoves.add(m1);
			}
		}
		else{
			if (casella[x-gap][y-gap2].getPedina()!=null && casella[x][y].getPedina().getColor()==color1 && casella[x-(gap*2)][y-(gap2*2)].getPedina() == null //sinistra
					&& casella[x-gap][y-gap2].getPedina().getColor()==color2 && ((casella[x][y].getPedina().getType()==true && casella[x-gap][y-gap2].getPedina().getType()==false) ||
							(casella[x][y].getPedina().getType()==true && casella[x-gap][y-gap2].getPedina().getType()==true) ||
							(casella[x][y].getPedina().getType()==false && casella[x-gap][y-gap2].getPedina().getType()==false) )){
				Movimento m2 = new Movimento(x,y,x-(gap*2),y-(gap2*2),casella[x][y].getPedina(),2);
				this.listMoves.add(m2);
			}
		}
		

	}
	
	/* Equivalente alla funzione canEat ma il controllo della mangiata viene effettuato solo su una determinata
	 * casella. Questa funzione viene chiamata in caso di mangiata multipla, in quanto se dopo la prima mangiata sono
	 * presenti altre mangiate da parte della stessa pedina, devo salvare solo quelle di quella pedina e non altre possibili
	 * mangiate in altre posizioni della scacchiera.
	 * 
	 */
	
	private void canEatWithParameters(int x, int y){
		if (listMoves.size()==0){
		
				if (casella[x][y].getBoxColor()==1){
					if (casella[x][y].getPedina()!=null && p1.getTurn()==true && casella[x][y].getPedina().getType()==false && (x>=2 && x<8)){  // PEDINA BIANCA
						color1=0;
						color2=1;
						gap=1;
						gap2=1;
						calculateEat(x,y,color1,color2,gap,gap2);
					}
					if (casella[x][y].getPedina()!=null && p1.getTurn()==false && casella[x][y].getPedina().getType()==false && (x>=0 && x<6)){  // PEDINA NERA
						color1=1;
						color2=0;
						gap=-1;
						gap2=1;
						calculateEat(x,y,color1,color2,gap,gap2);
					}
					if (casella[x][y].getPedina()!=null && p1.getTurn()==true && casella[x][y].getPedina().getType()==true){    // DAMONE BIANCO
						color1=0;
						color2=1;
						if (x<2){
							gap=-1;
							gap2=1;
							calculateEat(x,y,color1,color2,gap,gap2);	
						}
						else if (x>=2 && x<=5){
							gap=1;
							gap2=1;
							calculateEat(x,y,color1,color2,gap,gap2);
							gap=-1;
							calculateEat(x,y,color1,color2,gap,gap2);
						}
						else{
							gap=1;
							gap2=1;
							calculateEat(x,y,color1,color2,gap,gap2);
						}
					}
					if (casella[x][y].getPedina()!=null && p1.getTurn()==false && casella[x][y].getPedina().getType()==true){    // DAMONE NERO
						color1=1;
						color2=0;
						if (x<2){
							gap=-1;
							gap2=1;
							calculateEat(x,y,color1,color2,gap,gap2);
						}
						else if (x>=2 && x<=5){
							gap=1;
							gap2=1;
							calculateEat(x,y,color1,color2,gap,gap2);
							gap=-1;
							calculateEat(x,y,color1,color2,gap,gap2);
						}
						else{
							gap=1;
							gap2=1;
							calculateEat(x,y,color1,color2,gap,gap2);
						}
					}
				}
			}
		}
	
	/* Questa funzione calcola tutti i movimenti (non mangiate) che può fare un giocatore sulla scacchiera. Viene utilizzata
	 * per verificare se quel giocatore finisce i movimenti possibili, anche se non è ancora arrivato a zero pedine perchè 
	 * magari è stato bloccato dall'avversario.
	 * 
	 */
	
	public void calculateMovementsAll(){
		int x,y;
		if (listMoves.size() == 0){    
			for(x=0; x<8; x++){
				for(y=0; y<8; y++){
					if (casella[x][y].getBoxColor()==1){
						if(casella[x][y].getPedina()!=null && casella[x][y].getPedina().getType()==false && casella[x][y].getPedina().getColor()==0 && this.p1.getTurn()==true){
							gap=1;
							
							calculateCode(x,y,gap);
						}
						if(casella[x][y].getPedina()!=null && casella[x][y].getPedina().getType()==false && casella[x][y].getPedina().getColor()==1 && this.p1.getTurn()==false){
							gap=-1;
							calculateCode(x,y,gap);
						}
						if(casella[x][y].getPedina()!=null && casella[x][y].getPedina().getType()==true && casella[x][y].getPedina().getColor()==0 && this.p1.getTurn()==true){
							gap=1;
							
							calculateCode(x,y,gap);
							gap=-1;
							calculateCode(x,y,gap);
						}
						if(casella[x][y].getPedina()!=null && casella[x][y].getPedina().getType()==true && casella[x][y].getPedina().getColor()==1 && this.p1.getTurn()==false){
							gap=-1;
							
							calculateCode(x,y,gap);
							gap=1;
							calculateCode(x,y,gap);
						}
					}
				}
			}
		}
	}
	
	/* Questa funzione calcola tutti i movimenti (non mangiate) che può fare un giocatore su una determinata casella. E' divisa in due parti:
	 * calculateMovements e calculateCode. Una volta verificata il tipo di pedina cambia il valore di gap a seconda di che giocatore siamo e in che
	 * direzione stiamo giocando, in modo da poter effettuare i controlli correttamente. Una volta faccio ciò chiama la funzione calculateCode.
	 */

	public void calculateMovements(int x, int y){

		if (listMoves.size() == 0 && casella[x][y].getBoxColor()==1){    	
			if(casella[x][y].getPedina()!=null && casella[x][y].getPedina().getType()==false && casella[x][y].getPedina().getColor()==0 && this.p1.getTurn()==true){
				gap=1;
				calculateCode(x,y,gap);
			}
			if(casella[x][y].getPedina()!=null && casella[x][y].getPedina().getType()==false && casella[x][y].getPedina().getColor()==1 && this.p1.getTurn()==false){
				gap=-1;
				calculateCode(x,y,gap);
			}
			if(casella[x][y].getPedina()!=null && casella[x][y].getPedina().getType()==true && casella[x][y].getPedina().getColor()==0 && this.p1.getTurn()==true){
				gap=1;
				calculateCode(x,y,gap);
				gap=-1;
				calculateCode(x,y,gap);
			}
			if(casella[x][y].getPedina()!=null && casella[x][y].getPedina().getType()==true && casella[x][y].getPedina().getColor()==1 && this.p1.getTurn()==false){
				gap=-1;
				calculateCode(x,y,gap);
				gap=1;
				calculateCode(x,y,gap);
			}
		}

	}

	/* E' la funzione che effettua i controlli veri e propri dei possibili spostamenti che può fare quella pedina e li salva
	 * nell'array listMoves.
	 * 
	 */
	
	private void calculateCode(int x, int y,int gap){

		if (x-gap >= 0 && x-gap<=7){
			if(y>0 && y<7){	
				if (casella[(x-gap)][(y-1)].getPedina()==null){
					Movimento m1 = new Movimento(x,y,x-gap,y-1,casella[x][y].getPedina(),1);
					this.listMoves.add(m1);
				}
				if (casella[(x-gap)][(y+1)].getPedina()==null){
					Movimento m2 = new Movimento(x,y,x-gap,y+1,casella[x][y].getPedina(),1);
					this.listMoves.add(m2);
				}				    
			}
			else if (y==0){
				if (casella[x-gap][y+1].getPedina()==null) {
					Movimento m = new Movimento(x,y,x-gap,y+1,casella[x][y].getPedina(),1);
					this.listMoves.add(m);
				}
			}   
			else {
				if (casella[x-gap][y-1].getPedina()==null) {
					Movimento m = new Movimento(x,y,x-gap,y-1,casella[x][y].getPedina(),1);
					this.listMoves.add(m);
				}
			}
		}

	}
	
	/* Funzione che effettua lo spostamento della pedina da una posizione ad un'altra (se corretta) e cambia il turno alla fine. Se il movimento 
	 * effettuato è una mangiata, chiama la funzione DoppiaETripla e controlla che non ci siano doppie mangiate da effettuare. Se ci sono setta
	 * la variabile lock a true e non cambia il turno del giocatore fino a quando il giocatore non termina tutte le mangiate che ha a disposizione.
	 */

	public void move(int sx, int sy, int x, int y){
		
		int i,c;
		c=this.listMoves.size();
		for(i=0;i<c;i++){			
			if (x == this.listMoves.get(i).getEndX() && y == this.listMoves.get(i).getEndY() && sx == this.listMoves.get(i).getStartX() && sy == this.listMoves.get(i).getStartY()){
				if(this.listMoves.get(i).getPriority()==2){
					casella[(this.listMoves.get(i).getStartX()+x)/2][(this.listMoves.get(i).getStartY()+y)/2].setPedina(null);
					if ((x == 0 || x == 7) && this.listMoves.get(i).getPedina().getType() == false)
						this.listMoves.get(i).getPedina().setType(true);
					casella[x][y].setPedina(this.listMoves.get(i).getPedina());
					casella[this.listMoves.get(i).getStartX()][this.listMoves.get(i).getStartY()].setPedina(null);
					if (casella[x][y].getPedina().getColor()==1)
						p1.decPawn();
					else
						p2.decPawn();
					listMoves.clear();
					doppiaETripla(x,y);
					return;
				}
				else{
					if ((x == 0 || x == 7) && this.listMoves.get(i).getPedina().getType() == false)
						this.listMoves.get(i).getPedina().setType(true);
					casella[x][y].setPedina(this.listMoves.get(i).getPedina());
					casella[this.listMoves.get(i).getStartX()][this.listMoves.get(i).getStartY()].setPedina(null);
					changeTurn();
					listMoves.clear();
					return;
				}
			}
		}
		
	}

	// Funzione che controlla se sono presenti doppie mangiate o meno.
	
	private void doppiaETripla(int x, int y){
		setLock(true);
		canEatWithParameters(x,y);
		if (listMoves.size()==0){
			changeTurn();
			setLock(false);
			return;
		}
				
	}

	// Funzione che cambia il turno del giocatore
	
	private void changeTurn(){
		
		this.p1.setTurn(!this.p1.getTurn());
		this.p2.setTurn(!this.p2.getTurn());
		
	}

	
	// get e set degli attributi
	
	public Caselle getCasella(int x,int y){
		
		return casella[x][y];
	
	}
	
	public Giocatore getGiocatore1(){
		return p1;
	}
	
	public Giocatore getGiocatore2(){
		return p2;
	}
	
	public ArrayList<Movimento> getList(){
		return listMoves;
	}

	public static boolean isLock() {
		return lock;
	}

	public static void setLock(boolean lock) {
		Scacchiera.lock = lock;
	}
	
	
	
	
}


