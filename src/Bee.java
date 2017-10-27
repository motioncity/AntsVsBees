import java.util.Random;


public class Bee extends Insect {

	String name;
	
	Bee (int armor, int xCoordinate, int yCoordinate){
		super(armor,xCoordinate,yCoordinate);
		this.name = "bee";
	}
	
	//This method takes in an ant and reduces it's armor by 1
	void sting(Ant x){
		x.reduceArmor(1);
	}
	
	//This method moves the bee, the x coordinate is random (-1,0,1) as specified by instructions
	//The y-coordinate will be subtracted 1 because Dr. Yarrington told me that the bee will be starting
	//at the right side of the board and througout the game will try to get to the right side of the board
	void moveTo(){
		Random randvar = new Random();
		//By using randvar.nextInt(3) I can only get 0,1,2 so by subtracting 1 from that, I can only get 
		//either -1,0,1
		int x = randvar.nextInt(3) - 1;
		xCoordinate = xCoordinate + x;
		yCoordinate = yCoordinate - 1;
			}
	
	//This method represents an instance of a bee
	public String toString(){
		return name + ", " + armor; 
	}
}
