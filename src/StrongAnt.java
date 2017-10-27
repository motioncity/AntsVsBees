
//A strong ant is just an ant that has alot more armor than an usual ant 
public class StrongAnt extends Ant {
	
	StrongAnt(int armor, int xCoordinate, int yCoordinate){
		super(armor,xCoordinate,yCoordinate,1,1,"strong");
		
	}
	
	public String toString(){
		return name + ", " + armor;
	}

}
