
public class circle {
	
	private int x = 0;
	private int y = 0;
	private int speedx = 5;
	private int speedy = 10;
	
	public static final int MAX_X = 1500;
	public static final int MAX_Y = 900;
	public static final int MAX_SPEED = 10;
	
	public circle(int x, int y, int speedx, int speedy) {
		this.setX(x);
		this.setY(y);
		this.setSpeedx(speedx);
		this.setSpeedy(speedy);
		
	}
	
	public circle() {
		this.setX((int) (Math.random() * circle.MAX_X));
		this.setY((int) (Math.random() * circle.MAX_Y));
		this.setSpeedx((int) (Math.random() * circle.MAX_SPEED) +1);
		this.setSpeedy((int) (Math.random() * circle.MAX_SPEED) +1);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the speedx
	 */
	public int getSpeedx() {
		return speedx;
	}

	/**
	 * @param speedx the speedx to set
	 */
	public void setSpeedx(int speedx) {
		this.speedx = speedx;
	}

	/**
	 * @return the speedy
	 */
	public int getSpeedy() {
		return speedy;
	}

	/**
	 * @param speedy the speedy to set
	 */
	public void setSpeedy(int speedy) {
		this.speedy = speedy;
	}
	
}
