import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The class which holds all of the rules for the game and organizes all of the objects.
 * @author Kevin
 *
 */
public class Game extends JPanel implements ActionListener, KeyListener{
	
	/**
	 * The bottom border that the ship is allowed to travel.
	 */
	private final int MbottomBorder = 530;

	/**
	 * The array of aliens.
	 */
	private Ship[][] aliens;

	/**
	 * The new ship object that is controlled by a user.
	 */
	private Ship ship;

	/**
	 * The new missiles that are designated for the ship object.
	 */
	private Missile shipMissile;

	/**
	 * The new missiles that are designated for the alien object.
	 */
	private Missile alienMissile;

	/**
	 * The timer that is set to fire sixty times per second.
	 */
	private Timer timer;

	/**
	 * A boolean that switches between true and false in order to 
	 * move the alien array up and down.
	 */
	private boolean goingUp = false;
	
	/**
	 * Boolean that allows the player missile to be drawn and propelled upwards.
	 */
	private boolean fireMissile = false;

	/**
	 * The main method of the Game class.
	 * @param args
	 */
	public static void main(String[] args) {
		new Game();	
		
	}

	/**
	 * Default constructor of the game class that creates an instance 
	 * of the Game class.
	 */
	public Game(){

		ship = new Ship(250, 500, 30, 30, Color.blue, 10);
		shipMissile = new Missile(260, 480, 10, 20, Color.red, 8);
		timer = new Timer(33, this);									
		aliens = new Ship[5][10];
		timer.start();
		setupAliens();
		setupWindow();

	}

	/**
	 * Sets up the window where the graphics will be painted.
	 */
	private void setupWindow(){

		JFrame frame = new JFrame();

		frame.setTitle("Space Invaders");	                  		//Names the window "Space invaders".
		frame.setLocation(250, 100);			     			//Determines where it pops up on a screen.
		frame.setSize(616, 600);						//Panel set to 616X600 pixels.
		frame.add(this);							//Sets the size of the window.
		frame.addKeyListener(this);
		frame.setBackground(Color.black);					//Background color set to black.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//Ends the window functionality upon closing.
		frame.setVisible(true);							//Allows the user to see the window.

		this.setBackground(new Color (0, 0, 0));				
	}

	/**
	 * A setup method that sets up all of the elements in the alien array with a 
	 * specific set of coordinates, width, height, color, and velocity.
	 */
	private void setupAliens(){

		int previousX = 100;
		int previousY = 20;

		for(int row =0 ; row < aliens.length; row++){
			for(int col= 0; col < aliens.length*2; col++){
				
				aliens[row][col] = new Ship(previousX, previousY, 30, 30 , Color.orange, 3);
				previousX +=  30 + 10;	
				if(shipMissile.collision(aliens[row][col])){
//					aliens[row][col].setX(0);
//					aliens[row][col].setY(0);
					aliens[row][col]=null;
					
				}
			}
			previousY += 40;
			previousX = 100;
		}
	}

	/**
	 * Method that draws the alien array.
	 * @param g
	 */
	private void drawAliens(Graphics g){

		for(int row =0 ; row < aliens.length; row++){
			for(int col= 0; col < aliens.length*2; col++){

				if(!shipMissile.collision(aliens[row][col]))
				aliens[row][col].drawEntity(g);
				else if(shipMissile.collision(aliens[row][col]));
				
				
			}
		}
	}

	/**
	 * Method that moves the alien array up and down.
	 */
	private void moveAliens(){

		for(int row = 0 ; row < aliens.length; row++){
			for(int col= 0; col < aliens.length*2; col++){
				
				if(shipMissile.collision(aliens[row][col])){
					System.out.println("Target hit");
					shipMissile.setY(MbottomBorder);
					fireMissile = false;
					
					
				}
				if(aliens[4][9].getY() < 180){

					int previousX = 100;
					int previousY = 20;

					for(int i =0 ; i < aliens.length; i++){
						for(int j = 0; j < aliens.length*2; j++){

							if(shipMissile.collision(aliens[row][col])){
								aliens[row][col]=null;
							}
							aliens[i][j] = new Ship(previousX, previousY, 30, 30 , Color.orange, 3);
							previousX +=  40;
							
							
						}
						previousY += 40;
						previousX = 100;
					}


					goingUp = false;
				}
				else if(aliens[4][9].getY()> 300)
				{
					goingUp = true;
				}

				if( !goingUp){
					aliens[row][col].moveDown();
					
				}	
				else{
					aliens[row][col].moveUp();	
				}

			}
		}
	}

	/**
	 * Main painting method of the game class that draws the aliens, ship, and missile of the game.
	 */
	public void paint(Graphics g){
		super.paint(g);


		ship.drawEntity(g);
		if(fireMissile){
		shipMissile.drawEntity(g);
		}
		drawAliens(g);
		g.setColor(Color.green);
		g.drawLine(0, 531, 600, 531);
	}

	/**
	 * The method that will detect the keys pressed which will move
	 * the player ship either left or right.
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(fireMissile){
		shipMissile.moveUp();
		}
		if(shipMissile.getY()<=0){
			shipMissile.setY(MbottomBorder);
			fireMissile = false;
		}
		moveAliens();
		repaint();
	}


	/**
	 * KeyListener method that listens for key presses and responds only if
	 * a specific set of commands are attached to that key.
	 */
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_RIGHT){
			System.out.println("Right movement");
			ship.moveRight();
			ship.checkRightBoundary();
			
		}
		if (code == KeyEvent.VK_LEFT){
			System.out.println("Left movement");
			ship.moveLeft();
			ship.checkLeftBoundary();

		}
		if (code == KeyEvent.VK_SPACE){
			System.out.println("Fire Missile");
			fireMissile = !fireMissile;
			shipMissile.fireMissile();
			if(shipMissile.getY()<=MbottomBorder){
				shipMissile.setX(ship.getX()+10);
			}

		}

		repaint();  
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}

}
