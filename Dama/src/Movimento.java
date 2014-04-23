
public class Movimento {
	private int startx,starty,endx,endy;
	private Pedina pedina;
	private int priorita;
	
	public Movimento(int startx,int starty,int endx,int endy,Pedina pedina,int priorita){
		this.startx = startx;
		this.starty = starty;
		this.endx = endx;
		this.endy = endy;
		this.pedina = pedina;
		this.priorita = priorita;
		
	}
	
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
