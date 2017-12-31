import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *  The Entity class represents an object the is used in the Ship class to 
 *  make a ship, the Missile class to make a missile and the Alien class to
 *  make an array of enemies.
 * @author Kevin
 *
 */
public abstract class Entity {

	
	/**
	 * The x coordinate of an entity. 
	 */
	protected int x;
	
	/**
	 * The y coordinate of an entity.
	 */
	protected int y;
	
	/**
	 * The hit box width.
	 */
	protected int width;
	
	/**
	 * The hit box height.
	 */
	protected int height;
		
	/**
	 * The velocity at which an entity moves either left or right.
	 */
	protected int velocity;
	
	/**
	 * The color of an entity.
	 */
	protected Color color;
	
	/**
	 * A rectangle that will represent the hitbox of an entity.
	 */
	protected Rectangle collisionBox;
	
	/**
	 * Boolean that is either true or false depending on whether or not the 
	 * entity is hit.
	 */
	protected boolean isHit;
	
	/**
	 * Default constructor that sets the initial x and y position, the width
	 * and height, the color, and the velocity of the entity.
	 */
	public Entity(){
		setup(0,0,20,20,Color.black,1);
	}
	
	/**
	 * 
	 * @param x = the x coordinate of the entity.
	 * @param y = the y coordinate of the entity.
	 * @param w = the width of the entity.
	 * @param h = the height of the entity.
	 * @param c = the color of the entity.
	 * @param v = the velocity of the entity.
	 */
	public Entity(int x, int y, int w, int h, Color c, int v){
		setup(x,y,w,h,c,v);
	}
	
	
	/**
	 * Sets up the x and y coordinate of an entity as well as the color for
	 * that entity.
	 */
	public void setup(int theX, int theY, int w, int h, Color theColor, int vel){
		
		x = theX;
		y = theY;
		width = w;
		height = h;
		color = theColor;
		velocity = vel;
		isHit = false;							//isHit is set to false to prevent any
		collisionBox = new Rectangle(theX,theY, width, height);		//unwanted actions.
	}
	
	/**
	 * The method that paints the entity. It is set as an abstract method 
	 * and is implemented in the child classes.
	 */
	public abstract void drawEntity(Graphics g);
	
	/**
	 * Method to set the x coordinate of the entity.
	 */
	public void setCoordinate(int theX, int theY){
		x = theX;
		y = theY;
		collisionBox.setLocation(theX, theY);		//The collision box of the entity is updated each time
	}							//the method is invoked.
	
	/**
	 * A setter method that can set the velocity of the entity.
	 * @param v
	 */
	public void setVelocity(int v){
		velocity = v;
	}
	
	
	/**
	 * Method that gets the x coordinate of an entity.
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Method that gets the y coordinate of an entity.
	 */
	public int getY()
	{
		return y;
	}
	
	
	/**
	 * A setter method which can set the x coordinate of the entity.
	 * @param theX
	 */
	public void setX(int theX){
		this.x = theX;
	}
	
	/**
	 * A setter method that can set the y coordinate of the entity.
	 * @param theY
	 */
	public void setY(int theY){
		this.y = theY;
	}
	
	/**
	 * An getter method that returns the current width of the entity.
	 * @return = the width.
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * A getter method that returns the current height of the entity.
	 * @return = the height.
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * Abstract method to detect a collision among entities.
	 * @return = isHit.
	 */
	public boolean collision(Entity entity){
		isHit = collisionBox.intersects(entity.collisionBox);		//method of the Rectangle object
		return isHit;							//that checks to see if it intersects 
	}									//with another rectangle.
	
	/**
	 * Method that returns the current value of the isHit boolean data member.
	 * @return
	 */
	public boolean isHit(){
		return isHit;
	}
	
	/**
	 * A method that moves the entity to the left.
	 */
	public void moveLeft(){
		x -= velocity;
		collisionBox.setLocation(x, y);						//updates the location of the entity
	}										//hit box.
	
	/**
	 * A method that moves the entity to the right.
	 */
	public void moveRight(){
		x +=velocity;
		collisionBox.setLocation(x, y);						//updates the location of the entity
	}										//hit box.
	
	/**
	 * A method that moves the entity upward.
	 */
	public void moveUp(){
		y -= velocity;
		collisionBox.setLocation(x,y);						//updates the location of the entity
	}										//hit box.
	
	/**
	 * A method that moves the entity downward.
	 */
	public void moveDown(){
		y += velocity;
		collisionBox.setLocation(x, y);						//updates the location of the entity
	}										//hit box.

	
}
