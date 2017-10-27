
public class HarvesterAnt extends Ant {

	
	//This constructer uses the super class's constructer but sets the damage to 1, foodUsage to 1 and the 
	//name to "Harvester" 
	HarvesterAnt(int armor, int xCoordinate,int yCoordinate){
	super(armor,xCoordinate, yCoordinate,1,1, "harvester");
	
		}
	
	//This method represents an instance of a Harvester Ant
	public String toString(){
		return name + ", " + armor;	
}
}
