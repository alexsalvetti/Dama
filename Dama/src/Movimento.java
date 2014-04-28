
public class Movimento {
	private int startx,starty,endx,endy;                       // startx e starty = coordinate x e y della casella di partenza; endx e endy coordinate di quella di arrivo
	private Pedina pedina;                                     // pedina contenuta nella posizione di partenza, che devo spostare nella posizione finale
	private int priorita;                                      // priorità del movimento: 1 se è uno spostamento normale, 2 se è una mangiata.
	
	public Movimento(int startx,int starty,int endx,int endy,Pedina pedina,int priorita){
		this.startx = startx;
		this.starty = starty;
		this.endx = endx;
		this.endy = endy;
		this.pedina = pedina;
		this.priorita = priorita;
		
	}
	
	// get degli attributi
	
	public int getStartX(){
		return startx;
	}
	public int getStartY(){
		return starty;
	}
	public int getEndX(){
		return endx;
	}
	public int getEndY(){
		return endy;
	}
	public Pedina getPedina(){
		return pedina;
	}
	
	public int getPriority(){
		return priorita;
	}

}
