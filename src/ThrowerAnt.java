
public class ThrowerAnt extends Ant {
	
	//This constructer uses the super class's constructer but sets the damage to 1, foodUsage to 1 and the 
   //name to "Thrower" 
	ThrowerAnt(int armor, int xCoordinate,int yCoordinate){
		super(armor,xCoordinate,yCoordinate,2,2, "thrower");
	}
	
	//This method takes in a bee and reduces the bee's armor by the damage of the thrower ant
	void throwAt(Bee x){
		x.reduceArmor(damage);

		}
	
	//This method represents an instance of a Thrower Ant
	public String toString(){
		return name + ", " + armor;
	}
	
}
