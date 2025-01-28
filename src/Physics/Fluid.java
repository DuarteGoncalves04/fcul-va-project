package Physics;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Fluid {

	private float density;

	protected Fluid(float density) {
		this.density = density;
	}

	public PVector drag(Body b) {
		float area = PApplet.pow(b.radius, 2.0f) * PApplet.PI;
		float mag = b.vel.mag();
		return PVector.mult(b.vel, -0.5f * density * area * mag * 0.47f);
	}
	
	
	
	/*
	 * Drag force of a sphere according to the physics of water and air
	 */
	/*public PVector drag(Body b) {
		float Cd = 0.47f; //Drag coefficient of an airborne sphere (EXPERIMENTAL ->da NASA!!)
		float area = PApplet.pow(b.radius, 2.0f) * PApplet.PI;
		float mag = b.vel.mag();
		return PVector.mult(b.vel, -0.5f * density * area * Cd * mag);
	}*/
}
