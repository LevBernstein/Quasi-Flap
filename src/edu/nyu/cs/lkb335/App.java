package edu.nyu.cs.lkb335;

import processing.core.*;
import java.util.ArrayList;


/**
 * A flappy-bird-esque game in Java using Processing. A few major differences:
 * Instead of pressing a key to jump, you press a key to gain altitude. You are falling at all other times.
 * @author Nicole Ng, Lev Bernstein
 * @version 4.6
 * @source background image source: https://pngimage.net/flappy-bird-background-png-2/
 */
public class App extends PApplet {
	
	//we need the main method for PApplet, that's it.
	public static void main(String[] args) {
		PApplet.main("edu.nyu.cs.lkb335.App");
	}
	
	//we need PImage for loading in all the sprites. There are a few.
	PImage img;
	
	//window size:
	private static final int WIDTH = 900;
	private static final int HEIGHT = 504;
	
	//creating a Bird
	private Bird bird;
	
	// create a pipe
	private Pipe pipe;
	
	// set distance between the pipes
	public final static int PIPE_SPACING = 20;
	
	// array list to hold the pipes on the screen
	private ArrayList<Pipe> pipes = new ArrayList<Pipe>();
	
	
	/**
	 * Getter for the ArrayList of Pipe objects on the screen
	 * @return the ArrayList of pipe objects 
	 */
	public ArrayList<Pipe> getPipes() {
		return pipes;
	}

	/**
	 * Setter for the Arraylist of pipes objects on screen
	 * @param pipe ArrayList of pipe objects
	 */
	public void setPipes(ArrayList<Pipe> pipe) {
		this.pipes = pipe;
	}

	/**
	 * Set up window
	 */
	public void settings() {
		this.size(App.WIDTH,App.HEIGHT);
	}
	
	public void setup() {
		this.background(0,0,0);
		
		
		//we had some problems with loading images, as one of us (Lev) is on windows and the other (Nicole) is on mac.
		//on windows, it seems you need the src\\, while on mac, it seems you don't need that.
		//so, we made this. It's the only way we were able to work on the same project on multiple computers with different operating systems..
		String OStype = System.getProperty("os.name").toLowerCase();
		
		//System.out.println(OStype);
		if (OStype.indexOf("windows") >= 0) { //if the os name contains the word windows:
			img = loadImage("src\\background.png");
		}
		else { //assuming all other operating systems are unix-based:
			img = loadImage("background.png");
		}
		
		//generate the bird. It starts at x=100, y=height/3, it has a falling speed of 3, and it takes the PApplet app.
		this.bird = new Bird(100, (HEIGHT/3), 3, this);
		
		//generate a pipe.
		this.pipe = new Pipe(this);  
		}
	
	// generate pipes a certain distance apart by counting frames that have passed
	int framesElapsed = 0;
	
	public void draw() {
		boolean gameOver = false;
		this.background(img);
		double time = (int)(1.0*frameCount/60*100) / 100.0; //there are many ways to keep track of time. Because we are tying other things to frameCount, we use it for time here as well.
		
		this.bird.draw();
		this.bird.falling(); //the bird is constantly falling at a speed of birdV (3 in this case).
		if (keyPressed == true) { //instead of adding confusion as to what button to press, we just made is so that pressing any button makes the bird "jump."
			this.bird.jumping(); //the bird is constantly falling, but while you are jumping you are also changing the y by -2 * birdV.
		}
		
		// track how many frames have passed
		framesElapsed++;

		if(framesElapsed == App.PIPE_SPACING){ //after the requisite number of frames has passed:
			
			// generate a new pipe
			Pipe pipe = new Pipe(this);
			framesElapsed = 0;
		  }
		
		for (int i = 0; i<this.pipes.size(); i++) {
			// for every pipe, move and draw.
			Pipe pipe = this.pipes.get(i);
			pipe.moveLeft();
			pipe.draw();
	
			//checking collision. We have to check the collision for each pipe, so this is inside the for loop that moves and draws the pipes.
			//if the bird is colliding with the pipe or touching the floor/ceiling:
			if (this.bird.collision(pipe) || this.bird.getBirdY() <= 0 || this.bird.getBirdY() >= HEIGHT-30) { //we use HEIGHT-30 instead of HEIGHT because the bird sprite is 30 pixels tall, and its y position is held in the top left.
				gameOver = true;
			}
			
		}
		if (gameOver) { //all the below lines are outside the for loop because when they were inside, they would display under some pipes, which made reading difficult.
			this.bird.death(); //call the death function.
			noLoop(); //if you lose the game, stop redrawing stuff. The game is over.
			System.out.printf("\nYou survived for %.2f seconds.",time);
			textSize(32);
			fill(255,0,0);
			text("Game over!", WIDTH/3+50, HEIGHT/2-32);
			text("You survived for " + time + " seconds.", WIDTH/3-75, HEIGHT/2+50);
		}
		//this just keeps track of the time for the player.
		if (!gameOver) {
			textSize(32);
			fill(200,150,0);
			text("time: " + time, 10, 30);
		}
	}
}
