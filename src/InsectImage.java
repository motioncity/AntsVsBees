import javax.swing.ImageIcon;
import javax.swing.JButton;


//This class is pretty much just for assigning images to objects
public class InsectImage extends JButton {
	ImageIcon Hive = new ImageIcon("vesqueen.png" );
	ImageIcon Queen = new ImageIcon("masqueran.png");
	ImageIcon deadQueen = new ImageIcon("xedOut.png");
	ImageIcon HarvesterAnt =   new ImageIcon("trapinch.png");
	ImageIcon ThrowerAnt = new ImageIcon("durant.png");
	ImageIcon ZombieAnt = new ImageIcon("nincada.png");
	ImageIcon StrongAnt = new ImageIcon("scolpede.png");
	ImageIcon Bee = new ImageIcon("combee.png");
	ImageIcon KillerBee = new ImageIcon("beedrill.png");
	Place gameObject;


	public InsectImage(Place gameObject){
		//This method takes in a place and this place then gets an image represeneting  what it contains
		this.gameObject = gameObject;
		if((gameObject.yCoordinate == 0) && (gameObject.isQueen== false)){
			setIcon(deadQueen);
		}
		else if(gameObject.isQueen){
			setIcon(Queen);
		}
		else if(gameObject.isHive ){
			setIcon(Hive);
		}
		else if((gameObject.ant != null) && (gameObject.ant.name.equals("harvester"))){
			setIcon(HarvesterAnt);
		}
		else if((gameObject.ant != null) && (gameObject.ant.name.equals("thrower"))){
			setIcon(ThrowerAnt);
		}
		else if ((gameObject.ant!= null) && (gameObject.ant.name.equals("zombie"))){
			setIcon(ZombieAnt);
		}
		else if ((gameObject.ant!= null) && (gameObject.ant.name.equals("strong"))){
			setIcon(StrongAnt);
		}
		else if (gameObject.beeList.isEmpty() == false){
		setIcon(Bee);
		}
		else if (gameObject.killerBee != null){
			setIcon(KillerBee);
		}
	}
	

	}
