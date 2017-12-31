import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Timer;

/**
 * The primary weapon used by both the player ship and the generated
 * aliens.
 * @author Kevin
 *
 */
public class Missile extends Entity{
	
	/**
	 * The right border that the missile is allowed to travel.
	 */
	private final int MrightBorder = 580;
	
	/**
	 * The left border that the missile is allowed to travel.
	 */
	private final int MleftBorder = 10;
	
	/**
	 * The top border that the ship is allowed to travel.
	 */
	private final int MtopBorder = 0;

	/**
	 * Default constructor the sets the x and y coordinates of the
	 * missile and also the color of the missile.
	 */
	public Missile(){

		super();
	}

	public Missile(int theX, int theY, int theWidth, int theHeight , Color c, int vel){

		super(theX, theY, theWidth, theHeight, c, vel);
	}

	/**
	 * Method that sets the isHit boolean to true and allows the missile to be fired 
	 * in the Game class.
	 */
	public void fireMissile(){
		isHit = true;
	}
	
	/**
	 * Method that draws the missile object.
	 */
	public void drawEntity(Graphics g) {
	
		g.setColor(color);
		g.fillRect(x, y, width, height);
		
		
	}
}
