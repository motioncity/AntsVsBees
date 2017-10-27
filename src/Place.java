import java.util.LinkedList;


public class Place {
	

	int xCoordinate;
	int yCoordinate;
	LinkedList<Bee> beeList = new LinkedList<Bee>();
	Ant ant = null;
	KillerBee killerBee = null;
	boolean isHive;
	boolean isQueen;
	
	
	
	
	Place (int xCoordinate, int yCoordinate,boolean isHive, boolean isQueen){
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.isHive = isHive;
		this.isQueen = isQueen;
	}
	
	
	
	//This method goes through the beeList and removes any bee with an armor less than or equal to 0
	void removeBees(){
		for (int i = 0;i<beeList.size();i++){
			if (beeList.get(i).armor <= 0){
				beeList.remove(i);
			}
		}
			
		}
	
	//This method evaluates the killer bee if it's armor is less than or equal to 0 it sets the killer bee to null
		void removeKillerBee(){
			if ((killerBee != null)&&(killerBee.armor <= 0) ){
				killerBee = null;
			}
		}
	
	
	//This method evaluates the ant if it's armor is less than or equal to 0 it sets the ant to null
	void removeAnt(){
		if ((ant != null)&&(ant.armor <= 0) ){
			ant = null;
		}
	}
}
