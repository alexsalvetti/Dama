import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;


public class ScacchieraGrafica implements ActionListener {
	private int x,y;
	private Scacchiera s;
	private Grafica f;
	private IA ia;
	private static int startx=-1,starty=-1;



	public ScacchieraGrafica(int x, int y,Scacchiera s, Grafica f, IA ia) {
		
		this.x = x;
		this.y = y;
		this.s = s;
		this.f = f;
		this.ia = ia;

	}
	
	/* Ogni casella è collegata alla seguente funzione dell'evento di click. La funzione gestisce tutte le funzioni create nella classe Scacchiera ed IA
	 * e le fa cooperare tra loro per il funzionamento complessivo del gioco.
	 */

	public void actionPerformed(ActionEvent e) {

		if (s.getGiocatore2().getType()==false ){
			playerVsPlayer();
			if (checkESetWinner()==true){
				return;
			}
		}

		else{
			if (s.getGiocatore1().getTurn()==true){
				playerVsPlayer();
				if (checkESetWinner()==true){
					return;
				}			
			}

			if (s.getGiocatore1().getTurn()!=true){				
				refreshSelezione();
				ThreadIA r = new ThreadIA(f,s,ia);
				Thread t = new Thread(r);
				t.start();
			}
		}
	}
	
	// Questa funzione resetta le immagini delle pedine sulla scacchiera nella posizione corrente

	private void refreshCaselle(){
		
		int i,j;
		for (i=0; i<8; i++){
			for( j = 0; j<8; j++){
				if (s.getCasella(i, j).getPedina()!= null && s.getCasella(i, j).getPedina().getColor()==0 && s.getCasella(i, j).getPedina().getType()==false){
					f.getButton(i, j).setIcon(new ImageIcon(f.getIm1()));
				}
				else if (s.getCasella(i, j).getPedina()!= null && s.getCasella(i, j).getPedina().getColor()==1  && s.getCasella(i, j).getPedina().getType()==false){
					f.getButton(i, j).setIcon(new ImageIcon(f.getIm2()));
				}
				else if (s.getCasella(i, j).getPedina()!= null && s.getCasella(i, j).getPedina().getColor()==0  && s.getCasella(i, j).getPedina().getType()==true){
					f.getButton(i, j).setIcon(new ImageIcon(f.getIm3()));
				}
				else if (s.getCasella(i, j).getPedina()!= null && s.getCasella(i, j).getPedina().getColor()==1  && s.getCasella(i, j).getPedina().getType()==true){
					f.getButton(i, j).setIcon(new ImageIcon(f.getIm4()));
				}
				else
					f.getButton(i, j).setIcon(null);

			}
		}
		
	}

	// Questa funzione resetta il colore di sfondo dei bottoni, quindi rimuove la selezione verde dalla casella selezionata
	
	private void refreshSelezione(){
		
		int i,j;
		for(i=0;i<8;i++){
			for(j=0;j<8;j++){
				if ((i+j)%2==0){
					f.getButton(i, j).setBackground(new Color(61,43,31));

				}
				else{
					f.getButton(i, j).setBackground(new Color(210,180,140));

				}
			}
		}
		
	}
	
	// Questa funzione elimina tutte le pedine dalla scacchiera; viene chiamata a fine partita

	private void resetScacchiera(){
		
		int i,j;
		for (i=0; i<8; i++){
			for( j = 0; j<8; j++){
				f.getButton(i, j).setIcon(null);
			}
		}
		
	}
	
	// Effettua la selezione verde su un preciso bottone

	private void selezioneRossa(){
		
		if (s.getCasella(x, y).getBoxColor()==1 && s.getGiocatore1().getTurn()==true && s.getCasella(x, y).getPedina()!=null && s.getCasella(x, y).getPedina().getColor()==0 ){
			f.getButton(x, y).setBackground(new Color(0,168,107));
		}

		if (s.getCasella(x, y).getBoxColor()==1 && s.getGiocatore1().getTurn()==false && s.getCasella(x, y).getPedina()!=null && s.getCasella(x, y).getPedina().getColor()==1 ){
			f.getButton(x, y).setBackground(new Color(0,168,107));
		}
		
	}

	// Funzione che gestisce tutto il gioco in modalità "Player vs Player"
	
	private void playerVsPlayer(){
		
		refreshSelezione();	
		selezioneRossa();		
		if (s.getList().size()==0){			
			startx = x;
			starty = y;			
			s.calculateMovements(x, y);				
			for (Movimento move : s.getList()) { 				
				if (x==move.getStartX() && y == move.getStartY())
					f.getButton(move.getEndX(), move.getEndY()).setBackground(new Color(255,255,102));
			}
		}
		else if ((startx==-1 && starty==-1) || (s.getGiocatore1().getTurn()== true && s.getCasella(x,y).getPedina()!= null && s.getCasella(x,y).getPedina().getColor()==0) || (s.getGiocatore1().getTurn()== false && s.getCasella(x,y).getPedina()!= null && s.getCasella(x,y).getPedina().getColor()==1)){
			if (Scacchiera.isLock() ==false)
				s.getList().clear();
			s.canEat();
			s.calculateMovements(x, y);
			startx=x;
			starty=y;
			for (Movimento move : s.getList()) { 
				if (move.getPriority()==2)
					f.getButton(move.getEndX(), move.getEndY()).setBackground(new Color(255,255,102));
				else if (x==move.getStartX() && y == move.getStartY()){						
					selezioneRossa();						
					f.getButton(move.getEndX(), move.getEndY()).setBackground(new Color(255,255,102));
				}
			}
		}
		else{

			s.move(startx,starty,x,y);
			startx=-1;
			starty=-1;

			if (Scacchiera.isLock() == false){
				s.getList().clear();
				s.canEat();	
			}
		}
		refreshCaselle();
		f.setLabel();
		
		if (s.getList().size()==0){
			s.calculateMovementsAll();
			if (s.getList().size()==0){
				f.setVincitore();
				resetScacchiera();
				return;
			}
			else{
				s.getList().clear();
			}
			for (Movimento move : s.getList()) { 
				if (move.getPriority()==2)
					f.getButton(move.getEndX(), move.getEndY()).setBackground(new Color(255,255,102));
			}
		}
		
	}
	
	// Funzione che controlla se uno dei due giocatori ha vinto; se sì chiama la funzione setVincitore per settare correttamente l'etichetta

	private boolean checkESetWinner(){
		
		if (s.getGiocatore1().getPawn()== 0 || s.getGiocatore2().getPawn()== 0){
			f.setVincitore();
			resetScacchiera();
			return true;	
		}
		else{
			return false;
		}
		
	}

}

