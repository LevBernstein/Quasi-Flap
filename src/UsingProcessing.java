import processing.core.PApplet;
import java.util.ArrayList;

public class UsingProcessing extends PApplet{
	
	
	private ArrayList<circle> circles = new ArrayList <circle>();
	
	public static void main(String[] args) {
		PApplet.main("UsingProcessing");

	}
	
	public void settings() {
		size(1500,900);
	}
	
	public void setup() {
		fill(120,50,240);
		
		for (int i =0; i < 250; i++) {
			circle myCircle = new circle();
			this.circles.add(myCircle);
		}
	}
	
	public void draw() {
		this.background(0,50,240);
		//this.ellipse(width/2,height/2,width,height);
		for (int i =0; i < circles.size(); i++) {
			circle thisCircle = circles.get(i);
			this.ellipse(thisCircle.getX(), thisCircle.getY(),50,50);
			thisCircle.setX(thisCircle.getX()+thisCircle.getSpeedx());
			thisCircle.setY(thisCircle.getY()+thisCircle.getSpeedy());
			if (thisCircle.getX() > width || thisCircle.getX() <=0) thisCircle.setSpeedx(thisCircle.getSpeedx() * -1);
			if (thisCircle.getY() > height || thisCircle.getY() <=0) thisCircle.setSpeedy(thisCircle.getSpeedy() * -1);
		}

		
		
		
	}

}
