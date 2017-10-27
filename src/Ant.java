
public class Ant extends Insect {
	
	int damage;
	int foodUsage;
	String name;
	
	Ant(int armor, int xCoordinate, int yCoordinate, int damage, int foodUsage, String name){
		super(armor,xCoordinate, yCoordinate);
		this.damage = damage;
		this.foodUsage = foodUsage;
		this.name = name;
	}
	
	//This method is used to check whether the object is an Ant. 
	boolean isAnt(){
		return true;
	}
	
	//This method is used to show the instance of an Ant. 
	public String toString(){
		return super.toString() +", " + damage + ", " + foodUsage + ", " + name;
	}

}
