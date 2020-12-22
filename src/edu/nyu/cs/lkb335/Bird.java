package edu.nyu.cs.lkb335;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Flappy Bird bird. 
 * The bird has a few properties, some much more important than the others. Its x value (birdX), for instance, never changes, while its y value (birdY) is changing constantly.
 * @author Nicole Ng, Lev Bernstein
 * @version 2.1
 * @source image source: https://www.cleanpng.com/png-flappy-bird-app-store-sprite-scratch-851850/
 */

/**
 * @author LevBernstein
 *
 */
public class Bird {
	
	private App app;
	
	
	//properties
	private PImage img;
	private int birdX;
	private int birdY;
	private int birdV; //the bird is constantly falling, yet birdV (its velocity) is positive. This is because the y coordinate starts being counted from the top, so a positive value is farther down the screen.
	private int risingBirdV; //risingBirdV is -2*birdV, as that is the value used for "jumping."
	private boolean alive; //is the bird alive? If so, the player can press a key and make it "jump." If not, the player can't do anything anymore.
	
	//constructors
	/**
	 * the bird object itself.
	 * @param birdX
	 * @param birdY
	 * @param birdV
	 * @param app
	 */
	public Bird(int birdX, int birdY, int birdV, PApplet app) {
		this.app = (App) app; //we need to use PApplet, so why not take the one from App.java?
		this.setBirdX(birdX);
		this.setBirdY(birdY);
		this.setBirdV(birdV);
		this.risingBirdV = (this.getBirdV()*-2);
		this.setAlive(true);
		
		//we had some problems with loading images, as one of us (Lev) is on windows and the other (Nicole) is on mac.
		//on windows, it seems you need the src\\, while on mac, it seems you don't need that.
		//so, we made this. It's the only way we were able to work on the same project on multiple computers with different operating systems..
		String OStype = System.getProperty("os.name").toLowerCase();
		//System.out.println(OStype);
		if (OStype.indexOf("windows") >= 0) {
			this.img = app.loadImage("src\\bird.png");
		}
		else {
			this.img = app.loadImage("bird.png");
		}
	}
	
	//we need to have an overloaded constructor, but the professor said in class that we don't actually need to use it. We just have to show that we know how to make it. So:
	/**
	 * This is a more random bird; this constructor creates a bird with random x, y, and velocity.
	 * @param app
	 */
	public Bird(PApplet app) {
		this.app = (App) app;
		this.setBirdX((int) (Math.random() * 100));
		this.setBirdY((int) (Math.random() * 100));
		this.setBirdV((int)((Math.random() * 5) + 1));
		this.risingBirdV = (this.getBirdV()*-2);
		this.setAlive(true);
	}
	
	
	//behaviors
	
	/**
	 * draw the bird!
	 */
	public void draw() {
		this.app.image(this.img, this.getBirdX(), this.getBirdY());
	}
	
	
	/**
	 * make the bird fall. this is done very simply by adding the velocity of the bird to its y position.
	 */
	public void falling() {
		if (this.isAlive()) {
			int birdY2 = this.getBirdY() + this.getBirdV();
			this.setBirdY(birdY2);
		}
	}
	
	/**
	 * when jumping, add the bird's rising velocity to its y position. falling() is also firing, but jumping increases the y by twice as much.
	 */
	public void jumping() {
		if (this.isAlive()) {
			this.setBirdY(birdY+this.risingBirdV);
		}
	}
	
	/**
	 * collision detection is always annoying and wonky. This is no exception, but it gets the job done.
	 * @param a single pipe object
	 * @return whether the bird has collided with that specific pipe or not
	 */
	public boolean collision(Pipe pipe) { //because processing runs at 60 FPS, this only checks every 1/60th of a second. That means collision isn't as reliable as we'd like.
		boolean collision = false; //by default, collision is false.
		if (this.getBirdX() >= pipe.getX() && this.getBirdX() + 42 <= pipe.getPipeUpWidth() + pipe.getX()) { //the bird is an oval, which isn't accounted for here; honestly, it's not too important.
			if (this.getBirdY() <= pipe.getAdjustedY_down() || this.getBirdY() + 30 >= pipe.getAdjustedY_up()) { //if any part of the bird is inside the boundaries of the pipe, the bird has collided with the pipe.
				collision = true;
			}
		}
		return collision;
	}
	
	/**
	 * you died! This is basically just the isAlive method with an additional println.
	 */
	public void death() {
		System.out.println("Game over!");
		this.setAlive(false);
	}
	
	
	//getters
	
	/**
	 * @return the bird's x position
	 */
	public int getBirdX() {
		return birdX;
	}

	/**
	 * @return the bird's y position
	 */
	public int getBirdY() {
		return birdY;
	}

	/**
	 * @return the bird's falling velocity
	 */
	public int getBirdV() {
		return birdV;
	}
	
	/**
	 * @return is the bird alive?
	 */
	public boolean isAlive() {
		return alive;
	}

	//setters
	
	/**
	 * set the bird's x position
	 * @param the bird's x position
	 */
	public void setBirdX(int birdX) {
		this.birdX = birdX;
	}

	/**
	 * set the bird's y position
	 * @param the bird's y position
	 */
	public void setBirdY(int birdY) {
		this.birdY = birdY;
	}

	/**
	 * set the bird's falling velocity
	 * @param the bird's falling velocity
	 */
	public void setBirdV(int birdV) {
		this.birdV = birdV;
	}

	/**
	 * set the bird to alive or dead
	 * @param is the bird alive
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
