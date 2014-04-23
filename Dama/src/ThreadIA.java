import javax.swing.ImageIcon;

public class ThreadIA implements Runnable {
	Grafica f;
	Scacchiera s;
	
	public ThreadIA(Grafica f,Scacchiera s) {
		
		this.f = f;
		this.s = s;
		
	}

	public void run(){
		try {
			
				Thread.sleep(1000);
				if (checkESetWinner() == false){
					f.setLabel();
					refreshCaselle();
				}
				
				
				
		} 
		catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		
	}
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

	
	private void resetScacchiera(){
		int i,j;
		for (i=0; i<8; i++){
			for( j = 0; j<8; j++){

				f.getButton(i, j).setIcon(null);

			}
		}
	}
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
	
}