
//A killer bee extends the bee class and is just a strong bee that does more damage when it stings 
//and moves forward of hives unlike the bees which move either up or down of the hive

public class KillerBee extends Bee {
	
	KillerBee(int  armor, int xCoordinate, int yCoordinate ){
		super(armor,xCoordinate,yCoordinate);
		this.name = "killer bee";
	}
	
	void sting(Ant x){
		x.reduceArmor(3);
	}
	
	public String toString(){
		return name + ", " + armor; 
	}
	

}

