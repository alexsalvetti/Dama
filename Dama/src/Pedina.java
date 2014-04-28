
public class Pedina {
	private boolean type;      // indica se la è pedina o damone: false è pedina, true è damone
	private int color;         // indica il colore della pedina
	
	public Pedina(int color, boolean type){	
		
		this.color = color;
		this.type = type;
		
	}
	
	// get e set degli attributi
	
	public int getColor(){
		return color;
	}
	
	public boolean getType(){
		return type;
	}
	
	public void setType(boolean type){
		this.type = type;
	}
}
