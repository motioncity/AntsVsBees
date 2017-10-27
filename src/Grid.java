import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;


//This class is responsible for creating the grid
public class Grid extends JFrame {
	JPanel p = new JPanel();
	Colony gameImages;
	InsectImage buttons[][] ;

	public Grid(Colony gameImages){
			super("Food: " + gameImages.food + "             " + "Number of bees: " + gameImages.Numbees );
		this.gameImages = gameImages;
	updateGrid(gameImages);
	}

	void updateGrid(Colony x){
		//This method updates grid, showing the user what has happened as a result of the turns
		this.setTitle("Food: " + gameImages.food + "             " + "Number of bees: " + gameImages.Numbees);
		p.removeAll();
		buttons = new InsectImage[gameImages.boardsize-1][gameImages.boardsize];
		setSize(650,650);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		p.setLayout(new GridLayout(gameImages.boardsize-1,gameImages.boardsize ));
		for(int i = 0; i< gameImages.boardsize - 1;i++){
			//This for loop goes through each index in the buttons matrix and makes a new insectImage for that
			//index and then adds it to the Jpanel
			for (int j = 0; j < gameImages.boardsize; j++){
				buttons[i][j] = new InsectImage(gameImages.colony[i][j]);
				p.add(buttons[i][j]);
			}

		}
		add(p);
		setVisible(true);
	}

}




