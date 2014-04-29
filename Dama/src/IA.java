import java.util.ArrayList;
import java.util.Random;


public class IA {

	private Scacchiera s;
	private ArrayList<Movimento> listTemp = new ArrayList<Movimento>();
	private Random ran = new Random();
	private int r;

	public IA(Scacchiera s){
		this.s = s;
		
	}

		
	/* Funzione che calcola le mosse per il computer. La funzione è divisa in 3 parti:
	 * nella prima fase calcola, tra tutte le mosse possibili, se ci sono mosse intelligenti che darebbero vantaggio;
	 * se non trova mosse intelligenti, passa alla seconda fase che scarta tutte le mossi peggiori dalla lista completa
	 * di mosse che può fare; se non trova mosse da scartare oppure le scarta tutte, sceglie una mossa a caso e la esegue.
	 */

	public void bestMoves(){
		
		
		listTemp.clear();				
		s.calculateMovementsAll();		
		if (s.getList().size()!=0){

			for (Movimento move : s.getList()) { 	

				/* -- MOSSE PER PROTEGGERE LE PEDINE --:
				 * 1) NERA, NERA, BIANCA
				 * 2) NERA, NERA, BIANCA
				 * 3) BIANCA, NERA, NERA
				 * 4) BIANCA, NERA, NERA
				 */


				if (
					(((((move.getStartX())+3)>=0) && (((move.getStartX())+3)<=7)) &&				// 1)
					((((move.getStartY())+3)>=0) && (((move.getStartY())+3)<=7)) &&
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+2)>=0) && (((move.getEndX())+2)<=7)) &&
					((((move.getEndY())+2)>=0) && (((move.getEndY())+2)<=7)) &&									
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getStartX())+3), ((move.getStartY())+3))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+3), ((move.getStartY())+3))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+2), ((move.getEndY())+2))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+2), ((move.getEndY())+2))).getPedina().getColor()==0)) ||
					
					
					(((((move.getStartX())+3)>=0) && (((move.getStartX())+3)<=7)) &&				// 2)
					((((move.getStartY())-3)>=0) && (((move.getStartY())-3)<=7)) &&
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndX())+2)>=0) && (((move.getEndX())+2)<=7)) &&
					((((move.getEndY())-2)>=0) && (((move.getEndY())-2)<=7)) &&											
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getStartX())+3), ((move.getStartY())-3))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+3), ((move.getStartY())-3))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+2), ((move.getEndY())-2))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+2), ((move.getEndY())-2))).getPedina().getColor()==0)) ||
					
					
					(((((move.getStartX())-3)>=0) && (((move.getStartX())-3)<=7)) &&				// 3)
					((((move.getStartY())+3)>=0) && (((move.getStartY())+3)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())-2)>=0) && (((move.getEndX())-2)<=7)) &&
					((((move.getEndY())+2)>=0) && (((move.getEndY())+2)<=7)) &&						
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getStartX())-3), ((move.getStartY())+3))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-3), ((move.getStartY())+3))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true) &&				
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&				
					((s.getCasella(((move.getEndX())-2), ((move.getEndY())+2))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-2), ((move.getEndY())+2))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-2), ((move.getEndY())+2))).getPedina().getType()==true)) ||
					
					
					(((((move.getStartX())-3)>=0) && (((move.getStartX())-3)<=7)) &&				// 4)
					((((move.getStartY())-3)>=0) && (((move.getStartY())-3)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndX())-2)>=0) && (((move.getEndX())-2)<=7)) &&
					((((move.getEndY())-2)>=0) && (((move.getEndY())-2)<=7)) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getStartX())-3), ((move.getStartY())-3))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-3), ((move.getStartY())-3))).getPedina().getColor()==0) &&	
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())-2), ((move.getEndY())-2))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-2), ((move.getEndY())-2))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-2), ((move.getEndY())-2))).getPedina().getType()==true)) ||
					
										
					/* COONTROLLI PER SPINGERE A DIVENTARE DAMONE:
					 * 1) DA SINISTRA A DESTRA
					 * 2) DA DESTRA A SINISTRA
					 * 3) DA SINISTRA A DESTRA
					 * 4) DA DESTRA A SINISTRA
					 * 5) DA SINISTRA A DESTRA
					 * 6) DA DESTRA A SINISTRA
					 * 7) DA SINISTRA A DESTRA
					 * 8) DA DESTRA A SINISTRA
					 *  
					*/
					
									
					((move.getStartX()==5) &&
					((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 1)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() == null) &&					
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() == null) &&					
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==false) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() == null) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||
										
					((move.getStartX()==5) &&
					((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 2)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() == null) &&							
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() == null) &&							
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() == null) &&									
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==false) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||					
					
					((move.getStartX()==5) &&
					((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 3)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&		
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() == null) &&							
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() == null) &&								
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==false) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==true) &&								
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||					
					
					((move.getStartX()==5) &&
					((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 4)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&		
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==1) &&									
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() == null) &&							
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() == null) &&										
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==true) &&									
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==false) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||					
					
					((move.getStartX()==5) &&
					((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 5)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&		
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() == null) &&							
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() == null) &&								
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==false) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==false) &&								
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||					
					
					((move.getStartX()==5) &&
					((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 6)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&		
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==0) &&									
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() == null) &&							
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() == null) &&										
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==false) &&									
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==false) &&										
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||
					
																			
					/* ALTRI CONTROLLI PER DIVENTARE DAMONE DA RIGA 6
					 * 1) DA DESTRA A SINISTRA
					 * 2) DA SINISTRA A DESTRA
					 */
					
					
					((move.getStartX()==6) &&
					((((move.getStartX())+1)>=0) && (((move.getStartX())+1)<=7)) &&				// 1)
					((((move.getStartY())-1)>=0) && (((move.getStartY())-1)<=7)) &&
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&
					((s.getCasella(((move.getStartX())+1), ((move.getStartY())-1))).getPedina() == null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==false)) ||
					
					((move.getStartX()==6) &&
					((((move.getStartX())+1)>=0) && (((move.getStartX())+1)<=7)) &&				// 2)
					((((move.getStartY())+1)>=0) && (((move.getStartY())+1)<=7)) &&
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&
					((s.getCasella(((move.getStartX())+1), ((move.getStartY())+1))).getPedina() == null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==false))){

				listTemp.add(move);					
				
			}
			
			
			/* -- MOSSE PER TAPPARE I BUCHI --
			 * 
			 * DUE NERE E UNA BIANCA :    
			 * 
			 * 1) NERO, NERO, VUOTA, BIANCA
			 * 2) NERO, NERO, BIANCO, VUOTA
			 * 3) BIANCA, NERA, VUOTA, NERA
			 * 4) NERA, BIANCA, NERA, VUOTA
			 * 5) BIANCA, VUOTA, NERA, NERA
			 * 6) NERA, VUOTA, NERA, BIANCA
			 * 7) VUOTA, BIANCA, NERA, NERA
			 * 8) VUOTA, NERA, VUOTA, NERA
			 * 
			 */
			
			else if ((move.getStartX()!=0)&&( 
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 1)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&						
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&					
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() == null) &&				
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==false) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||
									
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 2)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() == null) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==false) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&	
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 3)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&						
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() == null) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==false) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1)) ||
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 4)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&								
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() == null) &&								
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==false) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 5)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&										
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==1) &&									
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==0) &&									
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina() == null) &&										
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&								
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() == null) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1)) ||
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 6)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&												
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==0) &&											
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==1) &&											
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina() == null) &&												
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&											
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() == null) &&											
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true) &&											
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 7)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&														
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==1) &&													
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==0) &&													
		     		((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina() == null) &&														
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() == null) &&													
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&												
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&													
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true)) ||
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 8)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&																
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==0) &&														
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==1) &&															
				   	((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina() == null) &&																
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() == null) &&															
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&														
		     		((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&															
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true)) ||
					
											
			        /* DUE BIANCHE E UNA NERA:
			         * 1) BIANCA, NERA, VUOTA, BIANCA
			         * 2) NERA, BIANCA, BIANCA, VUOTA
			         * 3) VUOTA, BIANCA, BIANCA, NERA
		             * 4) BIANCA, VUOTA, NERA, BIANCA 
			         */
							
					
				    (((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 1)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	                   
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&								
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==0) &&						
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||
									
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 2)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	                    
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&							
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==0) &&						
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&						
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||									
							
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 3)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&				
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&								
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==0) &&					
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina() == null) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() == null) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true)) ||										    
							
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 4)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&						
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&							
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==0) &&					
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina() == null) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() == null) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||
							
							
			         /* TRE NERE E UNA BIANCA 
			          *   
			          * 1) NERA, NERA, NERA, BIANCA
			          * 2) NERA, NERA, BIANCA, NERA
			          * 3) NERA, BIANCA, NERA, NERA
		              * 4) BIANCA, NERA, NERA, NERA  
		              *    		
	                  */
							
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				  // 1)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&					
			        ((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&					
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||							
							
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				  // 2)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&					
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina().getColor()==1) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1)) ||						
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				  // 3)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&					
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
				    ((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&					
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==0) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina().getColor()==1) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
				    ((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&	
			    	((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true)) ||					
							
			     	(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				  // 4)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&					
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==0) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
				    ((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==1) &&
				    ((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina().getColor()==1) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1)) ||							
							
								     	
			         /* DUE BIANCHE E DUE NERE
			          * 1) BIANCA, NERA, NERA, BIANCA
			          * 2) NERA, BIANCA, BIANCA, NERA
			          */				
							
			     	
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				  // 1)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&						
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&					
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina().getColor()==1) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||
											
							
							
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				  // 2)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&						
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&	
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&					
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina().getColor()==1) &&				
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&						
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1)))){

				listTemp.add(move);				

			}
		}

		}
		
		// FINE MOSSE VINCENTI ED INIZIO MOSSE DA SCARTARE
	
		
		if (listTemp.size()==0){

			for (Movimento move : s.getList()) {

				/* -- MOSSE DA SCARTARE --:
				 * 
				 * DUE NERE E DUE BIANCHE
				 * 1) NERA, NERA, BIANCA, BIANCA
				 * 2) BIANCA, NERA, BIANCA, NERA
				 * 3) BIANCA, BIANCA, NERA, NERA
				 * 4) NERA, BIANCA, NERA, BIANCA
				 * 5) NERA, NERA, BIANCA, BIANCA
				 * 6) NERA, BIANCA, NERA, BIANCA
				 * 7) BIANCA, NERA, BIANCA, NERA
				 * 8) BIANCA, BIANCA, NERA, NERA
				 * 
				 */

				if (!(
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 1)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina().getColor()==0) &&					
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==1) &&												
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||						
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 2)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&							
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina().getColor()==0) &&							
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==0) &&														
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1)) ||					
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 3)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&							
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina().getColor()==0) &&								
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==1) &&													
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==true) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true)) ||					
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 4)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&									
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&										
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==1) &&									
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina().getColor()==0) &&									
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&									
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==true) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||					
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 5)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&						
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina().getColor()==0) &&					
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||								
							
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 6)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&							
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina().getColor()==0) &&							
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&									
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&								
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||							
							
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 7)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&							
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&							
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina().getColor()==0) &&								
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==true) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true)) ||							
							
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 8)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&									
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&										
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==0) &&									
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina() != null) &&	
			    	((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina().getColor()==0) &&									
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==1) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==true) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1)) ||
					
					
					/* UNA NERA E DUE BIANCHE:
					 * 1) NERA, VUOTA, BIANCA, BIANCA
					 * 2) BIANCA, NERA, BIANCA, VUOTA
					 * 3) BIANCA, BIANCA, VUOTA, NERA
					 * 4) VUOTA, BIANCA, NERA, BIANCA
					 * 5) VUOTA, NERA, BIANCA, BIANCA
					 * 6) NERA, BIANCA, VUOTA, BIANCA
					 * 7) BIANCA, VUOTA, BIANCA, NERA
					 * 8) BIANCA, BIANCA, NERA, VUOTA
					 */
										
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 1)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina().getColor()==0) &&					
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() == null) &&				
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||	
												
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 2)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&							
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&									
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() == null) &&							
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina().getColor()==0) &&							
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==0) &&								
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||
							
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 3)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&							
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina().getColor()==0) &&								
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() == null) &&							
					((((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==true) &&								
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||							
					(((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==true))) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&						
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true)) ||
													
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 4)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&									
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&										
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() == null) &&										
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina().getColor()==0) &&										
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==0) &&										
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() == null) &&										
					((((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==true) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) || 
					(((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&											
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true))) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true)) ||
														
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 5)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&						
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina().getColor()==0) &&					
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() == null) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() == null) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||	
																
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 6)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&							
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() == null) &&						
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina().getColor()==0) &&							
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&									
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&								
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||
																
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 7)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&							
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&							
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() == null) &&						
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina().getColor()==0) &&								
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==0) &&								
					((((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==true) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0)) ||							
					(((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true))) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() == null) &&														
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true)) ||
																
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 8)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&									
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&										
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==0) &&									
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina() != null) &&	
				   	((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina().getColor()==0) &&									
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() == null) &&								
					((((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
		     		((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==true)) ||							
					(((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==true) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0))) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||
										
					
					/* UNA NERA E UNA BIANCA:
					 * 
					 * 1) VUOTA, NERA, VUOTA, BIANCA
					 * 2) NERA, VUOTA, BIANCA, VUOTA
					 * 3) VUOTA, BIANCA, NERA, VUOTA
					 * 4) BIANCA, VUOTA, VUOTA, NERA
				     */
					
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 1)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() == null) &&										
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() == null) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() == null) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||					
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 2)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() == null) &&												
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() == null) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||					
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 3)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
		     		((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&							
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() == null) &&							
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina().getColor()==0) &&												
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() == null) &&								
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==true) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||					
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 4)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&									
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() == null) &&							
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina().getColor()==0) &&												
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==true) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true)) ||
				
								
					/* UNA NERA E UNA BIANCA E DUE VUOTE
					 * 
					 * 1) NERA, BIANCA,
					 * 2) NERA, BIANCA,
					 * 3) BIANCA, NERA
					 * 4) BIANCA, NERA
					 * 	 
					 */
					
					
				    (((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 1)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() == null) &&				
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina().getColor()==0) &&					
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() == null) &&											
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() == null) &&				
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&				
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||						
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 2)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&							
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() == null) &&							
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina().getColor()==0) &&							
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() == null) &&													
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() == null) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||					
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 3)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&							
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() == null) &&						
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina().getColor()==0) &&								
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() == null) &&													
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==true) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true)) ||				
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 4)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&									
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&										
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() == null) &&									
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina().getColor()==0) &&									
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() == null) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() == null) &&									
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==true) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||
					
					
					/* TRE BIANCHE E UNA NERA:
					 * 1) NERA, BIANCA, BIANCA, BIANCA,
					 * 2) BIANCA, NERA, BIANCA, BIANCA,
					 * 3) BIANCA, BIANCA, NERA, BIANCA,
					 * 4) BIANCA, BIANCA, BIANCA, NERA.
					 */
								
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 1)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&						
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina().getColor()==0) &&						
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==0) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&						
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||					
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 2)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&						
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&								
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina().getColor()==0) &&								
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==0) &&								
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&									
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||					
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 3)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==0) &&										
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina().getColor()==0) &&										
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==0) &&										
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&													
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==true) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||					
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 4)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==0) &&										
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina().getColor()==0) &&										
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==0) &&										
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==true) &&											
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&										
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true)) ||				
					
					
					/* TRE NERE E UNA BIANCA:
					 * 1) NERA, NERA, NERA, BIANCA
					 * 2) NERA, NERA, BIANCA, NERA
					 * 3) NERA, BIANCA, NERA, NERA
					 * 4) BIANCA, NERA, NERA, NERA.
					 */
					
										
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 1)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==1) &&						
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina().getColor()==0) &&						
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==1) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&						
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||					
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 2)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&						
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==1) &&								
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina().getColor()==0) &&								
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==1) &&								
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&									
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1)) ||					
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 3)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==1) &&										
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina().getColor()==0) &&										
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==1) &&										
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&													
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==true) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1)) ||					
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 4)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==1) &&										
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina().getColor()==0) &&										
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==1) &&										
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==true) &&											
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&										
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true)) ||
					
					
					/* DUE NERE E UNA BIANCA (nere in diagonale)
					 * 1) NERA, VUOTA, BIANCA, NERA
					 * 2) VUOTA, NERA, NERA, BIANCA,
					 * 3) BIANCA, NERA, NERA, VUOTA
					 * 4) NERA, BIANCA, VUOTA, NERA
					 */
										
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 1)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&						
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina().getColor()==1) &&						
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() == null) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&								
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() == null) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1)) ||					
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 2)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&						
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==0) &&								
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina().getColor()==1) &&								
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() == null) &&								
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() == null) &&											
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&								
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||					
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 3)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==0) &&										
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina().getColor()==1) &&										
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() == null) &&										
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==true) &&											
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||					
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 4)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina().getColor()==0) &&										
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina().getColor()==1) &&										
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() == null) &&									
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&											
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==true) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true)) ||					
					
					
					/* DUE NERE E UNA BIANCA (nere in orizzontale)
					 * 1) NERA, NERA, VUOTA, BIANCA
					 * 2) NERA, NERA, BIANCA, VUOTA,
					 * 3) VUOTA, BIANCA, NERA, NERA
					 * 4) BIANCA, VUOTA, NERA, NERA
					 */
										
										
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 1)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&						
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() == null) &&						
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina().getColor()==0) &&						
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&								
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() == null) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||					
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 2)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&						
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() == null) &&									
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina().getColor()==0) &&								
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==1) &&								
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&										
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&								
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&							
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() == null)) ||					
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 3)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() == null) &&									
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())+2))).getPedina().getColor()==0) &&									
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina().getColor()==1) &&										
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() == null) &&											
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&	
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getType()==true) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getType()==true) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1)) ||					
					
					(((((move.getStartX())-2)>=0) && (((move.getStartX())-2)<=7)) &&				// 4)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())-2), (move.getStartY()))).getPedina() == null) &&										
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())-2), ((move.getStartY())-2))).getPedina().getColor()==0) &&										
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() != null) &&
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina().getColor()==1) &&									
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==0) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getType()==true) &&											
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() == null) &&										
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&									
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getType()==true)) ||
					
					
					/* DUE NERE E UNA BIANCA (nere in verticale)
					 * 1) NERA, VUOTA, NERA, BIANCA
					 * 2) VUOTA, NERA, BIANCA, NERA
					 */
					
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 1)
					((((move.getStartY())+2)>=0) && (((move.getStartY())+2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==1) &&						
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())+2))).getPedina().getColor()==0) &&						
					((s.getCasella((move.getStartX()), ((move.getStartY())+2))).getPedina() == null) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina().getColor()==1) &&								
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() == null) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==1) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==0)) ||
					
					(((((move.getStartX())+2)>=0) && (((move.getStartX())+2)<=7)) &&				// 2)
					((((move.getStartY())-2)>=0) && (((move.getStartY())-2)<=7)) &&					
					((((move.getEndX())-1)>=0) && (((move.getEndX())-1)<=7)) &&	
					((((move.getEndY())-1)>=0) && (((move.getEndY())-1)<=7)) &&	
					((((move.getEndX())+1)>=0) && (((move.getEndX())+1)<=7)) &&
					((((move.getEndY())+1)>=0) && (((move.getEndY())+1)<=7)) &&								
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), (move.getStartY()))).getPedina().getColor()==1) &&						
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina() != null) &&	
					((s.getCasella(((move.getStartX())+2), ((move.getStartY())-2))).getPedina().getColor()==0) &&						
					((s.getCasella((move.getStartX()), ((move.getStartY())-2))).getPedina() == null) &&						
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())-1))).getPedina() == null) &&							
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina() != null) &&		
					((s.getCasella(((move.getEndX())-1), ((move.getEndY())+1))).getPedina().getColor()==1) &&	
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())-1))).getPedina().getColor()==0) &&					
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina() != null) &&
					((s.getCasella(((move.getEndX())+1), ((move.getEndY())+1))).getPedina().getColor()==1)) ||
										
					(move.getStartX()==5) || (move.getStartX()==0)				

				    ))

				{

					listTemp.add(move);

				}	

			}
		}
		
			
		if (listTemp.size() != 0)

		{		
			s.getList().clear();
			s.getList().addAll(listTemp);
			listTemp.clear();

		}


		if (s.getList().size() != 0){

			listTemp.add(s.getList().get(ran.nextInt(s.getList().size())));
			s.getList().clear();
			s.getList().addAll(listTemp);

		}
	}

	// Funzione che fa eseguire la mossa o la mangiare al computer

	public void moveIA(){

		if (s.getList().size()!=0){
			r =	ran.nextInt(s.getList().size());
			s.move(s.getList().get(r).getStartX(), s.getList().get(r).getStartY(), s.getList().get(r).getEndX(), s.getList().get(r).getEndY());

		}
	}

}


