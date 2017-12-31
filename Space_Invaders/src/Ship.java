import java.awt.Color;
import java.awt.Graphics;

/**
 * The player controlled object that is used to fight against 
 * the aliens. The ship has a Missile so it is capable of fighting
 * with the alien invaders.
 * @author Kevin
 *
 */
public class Ship extends Entity{

	/**
	 * The right border that the ship is allowed to travel.
	 */
	private final int SrightBorder = 570;
	
	/**
	 * The left border that the ship is allowed to travel.
	 */
	private final int SleftBorder = 0;
	
	

	/**
	 * Default constructor that defines where the x and y coordinates 
	 * of the ship are and also what color the ship is.
	 */
	public Ship(){

		super();
	}

	/**
	 * Constructor that defines the x and y coordinates of the ship but
	 * not the color.
	 */
	public Ship(int theX, int theY, int theWidth, int theHeight , Color c, int vel){

		super(theX, theY, theWidth, theHeight, c, vel);
	}

	/**
	 * Method that checks to see if the ship has moved past the left most allowed 
	 * boundary of the panel.
	 */
	public void checkLeftBoundary(){
		if(getX()<=SleftBorder){		//Sets the x coordinate if it has passed the left
			setX(SleftBorder);			//border limit of the panel.
		}
	}
	
	/**
	 * Method that checks to see if the ship has moved past the right most allowed 
	 * boundary of the panel.
	 */
	public void checkRightBoundary(){
		if(getX()>=SrightBorder){		//Sets the x coordinate if it has passed the right 
			setX(SrightBorder);			//border limit of the panel.
		}
	}
	
	/**
	 * Method that draws the ship only if the isHit boolean is flagged to true.
	 */
	public void drawEntity(Graphics g) {

		if(!isHit){
			g.setColor(color);
			g.fillRect(x, y, width, height);	
		}
	}

}
