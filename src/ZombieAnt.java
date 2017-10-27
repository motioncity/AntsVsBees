
//A zombie ant is an ant that when stinged, destroys either the killer bee or entire bee list and 
//creates a new zombie ant in the bee's place
public class ZombieAnt extends Ant{

	ZombieAnt(int armor, int xCoordinate, int yCoordinate){
		super(armor,xCoordinate,yCoordinate,1,1,"zombie");
		
	}
	
	public String toString(){
		return name + ", " + armor;
	}
}
