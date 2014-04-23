import java.util.ArrayList;



public class Scacchiera {

	private Caselle[][] casella= new Caselle[8][8];
	private Giocatore p1,p2;
	private int color1,color2,gap,gap2;
	private ArrayList<Movimento> listMoves = new ArrayList<Movimento>();
	private static boolean lock = false;
	private boolean vittoria = false;

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

	private void calculateEat(int x, int y, int color1, int color2, int gap, int gap2){

		if(y>1 && y<6){		
			if (casella[x-gap][y+gap2].getPedina()!=null && casella[x][y].getPedina().getColor()==color1 && casella[x-(gap*2)][y+(gap2*2)].getPedina() == null
					&& casella[x-gap][y+gap2].getPedina().getColor()==color2 && 
					((casella[x][y].getPedina().getType()==true && casella[x-gap][y+gap2].getPedina().getType()==false) ||
							(casella[x][y].getPedina().getType()==true && casella[x-gap][y+gap2].getPedina().getType()==true) ||
							(casella[x][y].getPedina().getType()==false && casella[x-gap][y+gap2].getPedina().getType()==false) )){
				Movimento m1 = new Movimento(x,y,x-(gap*2),y+(gap2*2),casella[x][y].getPedina(),2);
				this.listMoves.add(m1);
				System.out.println("DEVO MANGIARE "+x+","+y+"-->"+(x-(gap*2))+","+(y+(gap2*2)));
			}
			if (casella[x-gap][y-gap2].getPedina()!=null && casella[x][y].getPedina().getColor()==color1 && casella[x-(gap*2)][y-(gap2*2)].getPedina() == null 
					&& casella[x-gap][y-gap2].getPedina().getColor()==color2 && 
					((casella[x][y].getPedina().getType()==true && casella[x-gap][y-gap2].getPedina().getType()==false) ||
							(casella[x][y].getPedina().getType()==true && casella[x-gap][y-gap2].getPedina().getType()==true) ||
							(casella[x][y].getPedina().getType()==false && casella[x-gap][y-gap2].getPedina().getType()==false) )){
				Movimento m2 = new Movimento(x,y,x-(gap*2),y-(gap2*2),casella[x][y].getPedina(),2);
				this.listMoves.add(m2);
				System.out.println("DEVO MANGIARE "+x+","+y+"-->"+(x-(gap*2))+","+(y-(gap2*2)));
			}
		}	    		
		else if (y<=1){
			if (casella[x-gap][y+gap2].getPedina()!=null && casella[x][y].getPedina().getColor()==color1 && casella[x-(gap*2)][y+(gap2*2)].getPedina() == null //destra
					&& casella[x-gap][y+gap2].getPedina().getColor()==color2 && ((casella[x][y].getPedina().getType()==true && casella[x-gap][y+gap2].getPedina().getType()==false) || (casella[x][y].getPedina().getType()==true && casella[x-gap][y+gap2].getPedina().getType()==true) || (casella[x][y].getPedina().getType()==false && casella[x-gap][y+gap2].getPedina().getType()==false) )){
				Movimento m1 = new Movimento(x,y,x-(gap*2),y+(gap2*2),casella[x][y].getPedina(),2);
				this.listMoves.add(m1);
				System.out.println("DEVO MANGIARE "+x+","+y+"-->"+(x-(gap*2))+","+(y+(gap2*2)));
			}
		}
		else{
			if (casella[x-gap][y-gap2].getPedina()!=null && casella[x][y].getPedina().getColor()==color1 && casella[x-(gap*2)][y-(gap2*2)].getPedina() == null //sinistra
					&& casella[x-gap][y-gap2].getPedina().getColor()==color2 && ((casella[x][y].getPedina().getType()==true && casella[x-gap][y-gap2].getPedina().getType()==false) ||
							(casella[x][y].getPedina().getType()==true && casella[x-gap][y-gap2].getPedina().getType()==true) ||
							(casella[x][y].getPedina().getType()==false && casella[x-gap][y-gap2].getPedina().getType()==false) )){
				Movimento m2 = new Movimento(x,y,x-(gap*2),y-(gap2*2),casella[x][y].getPedina(),2);
				this.listMoves.add(m2);
				System.out.println("DEVO MANGIARE "+x+","+y+"-->"+(x-(gap*2))+","+(y-(gap2*2)));
			}
		}
		

	}
	
	
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

	private void calculateCode(int x, int y,int gap){

		if (x-gap >= 0 && x-gap<=7){
			if(y>0 && y<7){	
				if (casella[(x-gap)][(y-1)].getPedina()==null){
					Movimento m1 = new Movimento(x,y,x-gap,y-1,casella[x][y].getPedina(),1);
					this.listMoves.add(m1);
					System.out.println("MOSSA VALIDA "+(x-gap)+","+(y-1));
				}
				if (casella[(x-gap)][(y+1)].getPedina()==null){
					Movimento m2 = new Movimento(x,y,x-gap,y+1,casella[x][y].getPedina(),1);
					this.listMoves.add(m2);
					System.out.println("MOSSA VALIDA "+(x-gap)+","+(y+1));
				}				    
			}
			else if (y==0){
				if (casella[x-gap][y+1].getPedina()==null) {
					Movimento m = new Movimento(x,y,x-gap,y+1,casella[x][y].getPedina(),1);
					this.listMoves.add(m);
					System.out.println("MOSSA VALIDA "+(x-gap)+","+(y+1));
				}
			}   
			else {
				if (casella[x-gap][y-1].getPedina()==null) {
					Movimento m = new Movimento(x,y,x-gap,y-1,casella[x][y].getPedina(),1);
					this.listMoves.add(m);
					System.out.println("MOSSA VALIDA "+(x-gap)+","+(y-1));
				}
			}
		}

	}

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
					System.out.println("\nMANGIATA EFFETTUATA\n");
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
					System.out.println("\nSPOSTAMENTO VALIDO\n");
					changeTurn();
					listMoves.clear();
					return;
				}
			}
		}
		
	}

	private void doppiaETripla(int x, int y){
		setLock(true);
		canEatWithParameters(x,y);
		if (listMoves.size()==0){
			changeTurn();
			setLock(false);
			return;
		}
		
		
		
	}

	private void changeTurn(){
		
		this.p1.setTurn(!this.p1.getTurn());
		this.p2.setTurn(!this.p2.getTurn());
		
	}

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


