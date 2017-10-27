//Eluamuno Victor Enenmo
//Nicholas Barnoski

public class Main {
	//EXTRA CREDIT

	//As said in the assignment I have included extra credit in this project, there is a graphical interface
	//which shows the user the game, there are two new ants; strong (just ants with large armor) and zombie 
	//(ants that when stung destroy the bee that stung them and then creates a new zombie ant in their place)
	// and there is a new type of bee called killerbee which is just a stronger version of bee and moves forward of hives
	//unlike the bees which move either up or down of the hive and there can only bee one killer bee at each place

	public static void main(String[] args) {
		//This is the play game method which allows you to play the game, console will ask you the boardsize (then creates 
		//a board that is boardsize - 1 by boardsize, then it asks for an x and y (make sure these are in range of the 
		//board) and finally it will ask for name of ant (which is either; harvester, thrower, zombie or strong).

		Colony.playGame();

	}

}
