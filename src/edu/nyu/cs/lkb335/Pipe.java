package edu.nyu.cs.lkb335;

import processing.core.*;

/**
 * 
 * generation of pipe obstacles. This is called in an arrayList in App.java, to make multiple pipes with ease.
 * @author Nicole Ng and Lev Bernstein
 * @version 5.5
 * @source source of pipe image: https://pngimage.net/flappy-bird-pipe-png-3/
 */
		
public class Pipe {
	
	// hold a reference to the App object, inherited from PApplet
	private App app;
	
	private PImage img_up; // holds the image for the upward facing pipe
	private PImage img_down; // holds the image for the downward facing pipe
	
	private int x, y_up ,y_down; //pipes will be in same x position
	private int speedX = -10; // pipes will move towards the left at this speed
	private int adjustedY_down, adjustedY_up; // for checking collision, y_up and y_down aren't enough. We need something more precise.

	/**
	 * Constructor for pipe
	 * @param app reference to the PApplet object that controls the flow of the game.
	 */
	public Pipe(App app) {
		// set up initial properties for pipes
		this.app = app; 
		
		// position on right side of screen
		this.x = this.app.width; 
		this.y_up = this.app.height - (int)(Math.random() * 100) - 100;
		int pipeYDiff = 650 - (int)(Math.random() * 20) * 6;
		this.y_down = this.y_up - pipeYDiff;
		
		//add this Pipe object to the PApplet's list of pipes
		this.app.getPipes().add(this);
		
		//load in images to store in PImage
		//we had some problems with loading images, as one of us (Lev) is on windows and the other (Nicole) is on mac.
		//on windows, it seems you need the src\\, while on mac, it seems you don't need that.
		//so, we made this. It's the only way we were able to work on the same project on multiple computers with different operating systems..
		String OStype = System.getProperty("os.name").toLowerCase();
		//System.out.println(OStype);
		if (OStype.indexOf("windows") >= 0) {
			this.img_up = app.loadImage("src\\pipe up.png");
			this.img_down = app.loadImage("src\\pipe.png");
		}
		else {
			this.img_up = app.loadImage("pipe up.png");
			this.img_down = app.loadImage("pipe.png");
		}
		this.setAdjustedY_down(y_down + this.getPipeDownHeight()); //the adjusted y position for the down-facing pipe is just the height of the pipe + the y coordinate of the pipe, which is the top left corner of the pipe.
		this.setAdjustedY_up(this.getAdjustedY_down() + pipeYDiff - this.getPipeUpHeight()); //the adjusted y position for the up facing pipe is partially based on the distance between the two pipes, which is what the Diff value is.
	}
	
	/**
	 * Draw the pipe to the PApplet screen
	 */
	public void draw() {
		// draw upward facing pipe
		this.app.image(this.img_up, this.x, this.y_up);
		// draw downward facing pipe
		this.app.image(this.img_down, this.x, this.y_down);

	}
	
	/**
	 * Move the pipe to the left
	 */
	public void moveLeft() {
		// move left
		int newX = this.x + this.speedX;
		
		// make update to position
		this.x = newX;
	}
	
	//getters and setters
	
	/**
	 * @return the x position of this pipe
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * set the x position of the pipe
	 * @param the x position of the pipe
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets by how much the pipes moves each frame.
	 * @param the x speed of the pipe
	 */
	public void setSpeed(int speedX) {
		this.speedX = speedX;
	}
	
	/**
	 * Get by how much the pipes moves each frame.
	 * @return the x speed of the pope
	 */
	public int getSpeed() {
		return this.speedX;
	}	
	
	/**
	 * @param the adjusted y value for the down-facing pipe
	 */
	public void setAdjustedY_down(int adjustedY_down) {
		this.adjustedY_down = adjustedY_down;
	}

	/**
	 * @param the adjusted y value for the up-facing pipe
	 */
	public void setAdjustedY_up(int adjustedY_up) {
		this.adjustedY_up = adjustedY_up;
	}

	/**
	 * @return the adjusted y value for the down-facing pipe
	 */
	public int getAdjustedY_down() {
		return adjustedY_down;
	}

	/**
	 * @return the adjusted y value for the up-facing pipe
	 */
	public int getAdjustedY_up() {
		return adjustedY_up;
	}

	/**
	 * @return Get the width of the upward facing pipe
	 */
	public int getPipeUpWidth() {
		// The width of upward facing pipe
		return this.img_up.width;
		}
	
	/**
	 * @return Get the height of the upward facing pipe
	 */
	public int getPipeUpHeight() {
		// The height of the upward facing pipe
		return this.img_up.height;
		}
	
	/**
	 * @return Get the width of the downward facing pipe
	 */
	public int getPipeDownWidth() {
		// The width of downward facing pipe
		return this.img_down.width;
		}
	
	/**
	 * @return Get the height of the upward facing pipe
	 */
	public int getPipeDownHeight() {
		// The height of the downward facing pipe
		return this.img_down.height;
		}

}
