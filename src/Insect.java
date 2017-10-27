
public class Insect {
	
	int armor;
	int xCoordinate;
	int yCoordinate;
	
	Insect(int armor, int xCoordinate, int yCoordinate){
		this.armor = armor;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	
	//This method reduces the insect armor by the inputed number and if the armor is less than 1
	//it then changes the coordinates of the insect to (-1,-1)
	void reduceArmor (int damage){
		armor = armor - damage;
		if ( armor < 0){
			xCoordinate = -1;
			yCoordinate = -1;
		}
	}
	
	//This method checks whether or not the object is an ant
	boolean isAnt(){
		return false;
	}
	
	//This toString method is used to show the instance of an Insect
	public String toString(){
		return "" + armor + ", " + xCoordinate + ", " + yCoordinate;
	}

}
