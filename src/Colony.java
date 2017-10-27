import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;


public class Colony {

	int Numbees = 50;
	Place hive;
	int food = 4;
	Place [][] colony;
	boolean[] queen;
	int boardsize;

	Colony(int boardsize){
		//this is the constructer which takes in a boardsize and creates a board that is size
		//this.boardsize-1 by this.boardsize  as specified by instructions, which stated creating 
		//an extra column for the queens
		this.boardsize = boardsize + 1;
		colony = new Place [boardsize][this.boardsize];
		//This for loop goes through each place in the colony matrix and initializes it as a new place
		for(int i = 0;i< boardsize;i++){
			for (int j = 0;j< this.boardsize;j++){
				colony[i][j] = new Place(i,j,false,false);
			}
		}
		Random randvar = new Random();
		int randSquare = randvar.nextInt(boardsize);
		//This sets the hive to a random place in the last column of the colony matrix and then sets 
		// the isHive field as true
		hive = colony[randSquare][this.boardsize - 1];
		colony[randSquare][this.boardsize - 1].isHive = true;

		queen = new boolean [boardsize];
		Arrays.fill(queen, true);
		//This for loop goes through each place in the colony matrix and sets all of isQueen fields of the 
		//places in the first column as true
		for(int i = 0; i < boardsize;i++){
			for(int j = 0; j< this.boardsize;j++){
				if(j ==0 ){
					colony[i][j].isQueen = true;
				}
			}
		}

	}

	LinkedList<Bee> createBees(int x, int y){
		//This method creates a linked list of bees (between 1 to 5 bees)
		Random randvar = new Random();
		int count = randvar.nextInt(5) + 1;
		//I set count as randvar.nextInt(5) + 1 so that it includes 5 and so that
		//it will always be greater than zero
		LinkedList<Bee> beeList = new LinkedList<Bee>();
		for (int i = 0; i < count; i ++){
			//This for loop creates as many bees in the linked list as the value of count
			beeList.add(new Bee(1,x,y));
		}
		Numbees = Numbees - count;
		return beeList;
	}

	void launchfromHive(){
		//This method is responsible for launching either a killer bee or bees from the hive
		Random randnum = new Random();
		int killerChoice = randnum.nextInt(14);
		Random randvar = new Random ();
		int choice = randvar.nextInt(2);
		//int choice = 0;

		if(killerChoice == 3){
			//If killerChoice is 3 then the killerBee of the place to the left of the hive is set to a new killer bee 
			//with an armor of 3, the chances of drawing a killerBee each turn are 1/14
			colony[hive.xCoordinate][hive.yCoordinate - 1].killerBee = new KillerBee(3,hive.xCoordinate, hive.yCoordinate - 1);
			Numbees = Numbees - 1;
		}
		//If the hive is placed at the top of the column then it creates new bees in the place directly below it
		else if (hive.xCoordinate == 0 ){
			colony[hive.xCoordinate + 1][hive.yCoordinate].beeList.addAll(createBees(hive.xCoordinate + 1, hive.yCoordinate));
		}
		//If the hive is placed at the bottom of the column then it creates new bees in the place directly above it
		else if (hive.xCoordinate == boardsize - 2){
			colony[hive.xCoordinate - 1][hive.yCoordinate].beeList.addAll(createBees(hive.xCoordinate - 1,hive.yCoordinate));
		}
		//If neither of the above conditions are true then it looks at choice( which has a 1/2 chance of being 0) and if 
		//choice is equal to 0 it creats bees in the place directly above the hive and if choice is equal to 
		//1 it then creates bees in the place directly below the hive
		else if (choice == 0){
			colony[hive.xCoordinate - 1][hive.yCoordinate].beeList.addAll(createBees(hive.xCoordinate - 1, hive.yCoordinate));
		}
		else if (choice == 1){
			colony[hive.xCoordinate + 1][hive.yCoordinate].beeList.addAll(createBees(hive.xCoordinate + 1,hive.yCoordinate ));
		}
	}


	void moveBee(Bee abee){
		//This method takes in a bee and keeps moving it until it's x and y coordinates are in range of the
		//boardsize
		int oldX = abee.xCoordinate;
		int oldY = abee.yCoordinate;
		abee.moveTo();
		//This while loops looks at the x and y coordinates of the bee and if it is out of range of the board
		// it resets the x and y coordinates to what it orignally was and keeps moving the bee until it's 
		//x and y coordinates are in range
		while ((abee.xCoordinate > (boardsize - 2) | (abee.yCoordinate > boardsize - 1) 
				| (abee.xCoordinate < 0) | (abee.yCoordinate < 0))){
			abee.xCoordinate = oldX;
			abee.yCoordinate = oldY;
			abee.moveTo();
		}

	}



	void moveallBee(){
//This method goes through each place and either moves all of the bees there or the killer bee there
		for(int j = 0; j < boardsize ;j++){
			for(int i = 0; i < boardsize - 1 ; i ++){
				if (colony[i][j].beeList.isEmpty()==false){
					//If colony[i][j] has bees there it then sets length equal to the size 
					//of the beelist at that place because the size of linkedlists change so 
					//having the for loop going until the original size was the best solution
					int length = colony[i][j].beeList.size();
					for (int k = 0; k < length ;k++){
						//This for loop goes through each bee in the beelist at colony[i][j].beeList
						//and moves the bee and does something different depending on what is at the place
						//the bee was going to move to
						int l = 0;
						//I used the counter l because linkedList items change index so the easier way to 
						//deal with that was to use another count, it will only increase when I tell it to
						int oldX= colony[i][j].beeList.get(l).xCoordinate;
						int oldY = colony[i][j].beeList.get(l).yCoordinate;
						//I move the bee
						Bee oldBee = colony[i][j].beeList.get(l);
						moveBee(colony[i][j].beeList.get(l));

						int newX = colony[i][j].beeList.get(l).xCoordinate;
						int newY = colony[i][j].beeList.get(l).yCoordinate;
						Ant potentialAnt = colony[newX][newY].ant;

						if((potentialAnt != null)&& (potentialAnt.name.equals("harvester"))){
							//If the spot where the bee was going to move to has a harvester ant
							//the x and y coordinates are reverted to what they originally were
							// and it stings the ant and then increases the l counter to go to the 
							//next ant
							oldBee.xCoordinate = oldX;
							oldBee.yCoordinate = oldY;
							oldBee.sting(potentialAnt);
							l++;
						}
						else if ((potentialAnt!= null) && (potentialAnt.name.equals("thrower"))){
							//If the spot where the bee was going to move to has a harvester ant
							//the x and y coordinates are reverted to what they originally were
							// and it stings the ant and then the ant attacks the bee. 
							oldBee.xCoordinate = oldX;
							oldBee.yCoordinate = oldY;
							oldBee.sting(potentialAnt);	
							((ThrowerAnt) potentialAnt).throwAt(oldBee);
							l++;
						}
						else if ((potentialAnt!= null) && (potentialAnt.name.equals("zombie"))){
							//If the spot where the bee was going to move to has a zombie ant
							//the bee stings the ant and then the entire beeList at colony[i][j]
							//is cleared and a new zombie ant with armor 1 is created in it's place
							oldBee.sting(potentialAnt);	
							colony[i][j].beeList.clear();
							colony[i][j].ant = new ZombieAnt(1,i,j);
							k = length;
						}
						else if ((potentialAnt!= null) && (potentialAnt.name.equals("strong"))){
							//If the spot where the bee was going to move to has a strong ant
							//the x and y coordinates are reverted to what they originally were
							// and it stings the ant
							oldBee.xCoordinate = oldX;
							oldBee.yCoordinate = oldY;
							oldBee.sting(potentialAnt);
							l++;
						}
						else if (colony[newX][newY].killerBee != null){
							//If the spot where the bee was going to move to has a killer bee it reverts
							//the bee's x and y coordinates to what they originally were
							oldBee.xCoordinate = oldX;
							oldBee.yCoordinate = oldY;
							l++;
						}
						else {
							//If there is nothing in the spot in where the ant was going to move to then
							//the bee moves there without hassle
							colony[newX][newY].beeList.add(oldBee);
							colony[i][j].beeList.remove(l);
						}
					}
				}
				else if (colony[i][j].killerBee != null){
					//If colony[i][j] has a killer bee the bee is moved and it 
					//behaves differently depending on what was at the spot it was going 
					//to move to
					int oldX= colony[i][j].xCoordinate;
					int oldY = colony[i][j].yCoordinate;
					KillerBee oldKiller = colony[i][j].killerBee;
					moveBee(colony[i][j].killerBee);

					int newX = colony[i][j].killerBee.xCoordinate;
					int newY = colony[i][j].killerBee.yCoordinate;
					Ant potentialAnt = colony[newX][newY].ant;

					if(colony[newX][newY].killerBee!= null){
						//If the spot where the killer bee was going to move to 
						//has another killer bee the x and y coordinates of the killer bee
						//we are at are reverted to what they originally were
						oldKiller.xCoordinate = oldX;
						oldKiller.yCoordinate = oldY;
					}
					else if(colony[newX][newY].beeList.isEmpty() == false){
						//If the spot where the killer bee was going to move to 
						//has another bunch of bees the x and y coordinates of the killer bee
						//we are at are reverted to what they originally were
						oldKiller.xCoordinate = oldX;
						oldKiller.yCoordinate = oldY;
					}
					else if ((potentialAnt != null) && (potentialAnt.name.equals("harvester"))){
						//If the spot where the killer bee was going to move to 
						//has a harvester ant the and y coordinates of the killer bee are reverted to 
						//what they originally were and the killer bee stings the ant 
						oldKiller.xCoordinate = oldX;
						oldKiller.yCoordinate = oldY;
						oldKiller.sting(potentialAnt);
					}
					else if ((potentialAnt != null) && (potentialAnt.name.equals("thrower"))){
						//If the spot where the killer bee was going to move to 
						//has a thrower ant the and y coordinates of the killer bee are reverted to 
						//what they originally were and the killer bee stings the ant and the ant 
						//also attacks the bee
						oldKiller.xCoordinate = oldX;
						oldKiller.yCoordinate = oldY;
						oldKiller.sting(potentialAnt);
						((ThrowerAnt) potentialAnt).throwAt(oldKiller);
					}
					else if ((potentialAnt != null) && (potentialAnt.name.equals("zombie"))){
						//If the spot where the killer bee was going to move to 
						//has a zombie ant, the killer bee stings the ant and then the killer bee is set 
						//to null, a new zombie ant is created in it's place
						oldKiller.sting(potentialAnt);
						oldKiller = null;
						colony[oldX][oldY].ant = new ZombieAnt(1,i,j);
					}
					else if ((potentialAnt != null) && (potentialAnt.name.equals("strong"))){
						//If the spot where the killer bee was going to move to 
						//has a strong ant the and y coordinates of the killer bee are reverted to 
						//what they originally were and the killer bee stings the ant
						oldKiller.xCoordinate = oldX;
						oldKiller.yCoordinate = oldY;
						oldKiller.sting(potentialAnt);
					}
					else {
						//If there is nothing where the killer bee was going to move to then the
						//killer bee moves to that spot without hassle
						colony[newX][newY].killerBee = oldKiller;
						oldKiller = null;
					}
				}
			}
		}

	}


	void addAnt(int x, int y,String name){
		//This method is responsible for adding a new ant to the board. It takes in a x, y and a name and if there is nothing
		//at the x and y specified, if the place at the x and y specificied  is not a hive or a queen and there is enough food then a 
		//new ant (depending on whatever name you gave in ) is created at that x and y
		if ((food >= 1) && (colony[x][y].ant == null)&&(colony[x][y].beeList.isEmpty()) && (colony[x][y].killerBee == null)
				&& (x <= boardsize - 2) && 
				(colony[x][y] != hive)&&(y <= boardsize - 1)&&(y!= 0) && (name.equals("harvester"))){
			colony[x][y].ant = new HarvesterAnt(1,x,y);
			food = food - 1;
		}
		else if ((food >= 2) && (colony[x][y].ant == null)&&(colony[x][y].beeList.isEmpty()) &&(colony[x][y].killerBee == null)
				&&(x <= boardsize - 2) &&
				(colony[x][y] != hive)&&(y <= boardsize - 1)&&(y!= 0) && (name.equals("thrower"))){
			colony[x][y].ant = new ThrowerAnt(2,x,y);
			food = food - 2;
		}
		else if ((food >= 1) && (colony[x][y].ant == null)&&(colony[x][y].beeList.isEmpty()) && (colony[x][y].killerBee == null)
				&& (x <= boardsize -2) &&
				(colony[x][y] != hive)&&(y <= boardsize - 1)&&(y!= 0) && (name.equals("zombie"))){
			colony[x][y].ant = new ZombieAnt(1,x,y);
			food = food - 1;
		}
		else if ((food >= 4) && (colony[x][y].ant == null)&&(colony[x][y].beeList.isEmpty()) &&  (colony[x][y].killerBee == null)
				&& (x <= boardsize - 2) &&
				(colony[x][y] != hive)&&(y <= boardsize - 1)&&(y!= 0) && (name.equals("strong"))){
			colony[x][y].ant = new StrongAnt(5,x,y);
			food = food - 4;
		}
	}

	void checkQueens(){
		//This method checks all of the queens on the board and if there is either a bee or 
		//a killer bee there then that location is no longer considered a queen, the bee there is removed
		//and the sets the queen array for that row equal to false, so if row 0 no longer has a queen then
		//queen[0] = false
		int j = 0;
		for (int i = 0;i< boardsize - 1;i++){
			//This for loop goes through each queen and sees if there is a bee there
			//and if there is it does what I specified above
			if (colony[i][j].beeList.isEmpty()== false){
				colony[i][j].beeList.clear();
				queen[i] = false;
				colony[i][j].isQueen = false;
			}
			else if (colony[i][j].killerBee != null){
				colony[i][j].killerBee = null;
				queen[i] = false;
				colony[i][j].isQueen = false;
			}
		}
	}
	boolean nomoreBees(){
		//This method returns true if there are no more bees in the playing area (everywhere besides where queens are)
		//and false if otherwise
		int count = (boardsize - 1) * boardsize ;
		int noBee = 0;
		int noKillerBee = 0;
		for(int i = 0;i<boardsize - 1;i++){
			//This for loop checks the colony matrix to see how many places have no bees
			for (int j = 0;j<boardsize;j++){
				if(colony[i][j].beeList.isEmpty()){
					noBee++;
				}
			}
		}
		for(int i = 0;i<boardsize -1 ;i++){
			//This for loop checks the colony matrix to see how many places have no killer bees
			for (int j = 0;j<boardsize;j++){
				if(colony[i][j].killerBee == null){
					noKillerBee++;
				}
			}
		}
		if ((noBee == count) && (Numbees == 0) && (noKillerBee == count)){
			return true;
		}
		return false;
	}

	boolean noQueen(){
		//This method returns true if there are no more queens on the board and false if otherwise
		int count = 0;
		for(int i = 0;i< queen.length;i++){
			//This for loop checks to see how many rows don't have queens
			if (queen[i] == false){
				count++;
			}
		}
		if (count == boardsize - 1){
			return true;
		}
		return false;
	}

	boolean noAnt(){
		//This method returns true if there are no more ants on the board and false if otherwise
		int count = (boardsize - 1) * boardsize;
		int noAnt = 0;
		for(int i = 0;i<boardsize - 1;i++){
			for (int j = 0;j<boardsize;j++){
				//This for loop checks to see how many places do not have ants
				if(colony[i][j].ant== null){
					noAnt++;
				}
			}
		}
		if ((noAnt == count) && (food == 0)){
			return true;
		}
		return false;
	}

	boolean beesWin(){
		//This method returns true if the bees have won the game which is when there are either no more 
		//ants on the board or queens and returns false if otherwise
		if ((noAnt()) || (noQueen())){
			System.out.println("The bees have won!!!");
			return true;
		}
		return false;
	}

	boolean antsWin(){
		//This method returns true if the ants have won the game which is when there are no more bees on the board
		//and false if otherwise
		if (nomoreBees()){
			System.out.println("The ants have won!!!");
			return true;
		}
		return false;
	}

	boolean hasWon(){
		//This method returns true if either the bees have won or the ants have won and false if otherwise
		if ((beesWin()) | (antsWin())){
			return true;
		}
		return false;
	}

	void yourTurn(){
		//This method is responsible for the user's turn, it asks the user for a x,y and a name 
		//and if the conditions are met it then places a new ant (depending on name) at that x and y
		//then for every harvester ant on the board the food is increased by 1
		Scanner in = new Scanner (System.in);
		System.out.println("Enter x coordinate for new ant");
		int xCor = in.nextInt();
		System.out.println("Enter y coordinate for new ant");
		int yCor = in.nextInt();
		System.out.println("Enter name for new ant");
		String name = in.next();
		addAnt(xCor,yCor,name);

		for(int i = 0;i<boardsize - 1;i++){
			for (int j = 0;j<boardsize;j++){
				//This for loop goes through each place in the colony matrix and wherever there is a harvester
				//ant the food is increased by 1
				if((colony[i][j].ant != null) && (colony[i][j].ant.name.equals("harvester"))){
					food++;
				}
			}
		}
	}
	void computerTurn(){
		//This method is responsible for the computer's turn, as long as Numbees is greater than 0 then the computer will
		//launch bees from the hive
		if(Numbees > 0){
			launchfromHive();
		}
	}

	static void playGame(){
		//This method is what allows us to play the game, it adds a boardsize and creates a new colony using that value
		//and also creates a new grid which is responsible for the grid that shows the game
		Scanner in = new Scanner (System.in);
		System.out.println("Enter boardsize");
		int bz = in.nextInt();
		Colony game = new Colony (bz);
		Grid screen = new Grid (game);

		while (game.hasWon()==false){
			//As long as no one has won the game then game is played and the grid is refreshed
			game.checkQueens();
			game.moveallBee();
			game.computerTurn();
			game.yourTurn();
			screen.updateGrid(game);
			for (int i = 0; i < bz;i++){
				for (int j = 0; j< bz + 1;j++){
					game.colony[i][j].removeBees();
					game.colony[i][j].removeKillerBee();
					game.colony[i][j].removeAnt();
				}
			}
		}
	}
}
