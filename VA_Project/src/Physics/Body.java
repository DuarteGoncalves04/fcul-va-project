package Physics;

import processing.core.PApplet;
import processing.core.PVector;

public class Body extends Mover {

	protected int color;
	protected float radius;

	public Body(PVector pos, PVector vel, float mass, float radius, int color) {
		super(pos, vel, mass);
		this.color = color;
		this.radius = radius;
	}

	public void display(PApplet p, SubPlot plt) {
		p.pushStyle();
		float[] pp = plt.getPixelCoord(pos.x, pos.y);
		float[] r = plt.getDimInPixel(radius, radius);
		p.noStroke();
		p.fill(color);
		p.circle(pp[0], pp[1], 2 * r[0]);
		p.popStyle();
	}
	
	/*
	 * Verifies if the sand is being hit upon landing and allows for the ball to 
	 * bounce back, dampening speed
	 */
	public void checkCollisionWall(Wall wall) {
		if (pos.y - radius <= wall.y) {
	        //Adjust position to sit EXACTLY on the wall
	        pos.y = wall.y + radius;
	        vel.y *= -0.5; //Dampen -> velocity bounce
	    }
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getRadius() {
		return radius;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public float getColor() {
		return color;
	}
}
