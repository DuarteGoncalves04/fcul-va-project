package Physics;

import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;

public class BuoyancyApp implements IProcessingApp {

	// WINDOW SETTINGS
	public static final float dimY = 50; // meters
	public static final float dimX = 20; // meters
	private final double window[] = { 0, dimX, 0, dimY };
	private float viewport[] = { 0f, 0f, 1f, 1f };

	// GENERAL VARIABLES
	private SubPlot plt;
	private Body ball;
	private Water water;
	private Air air;

	// PHYSICS VALUES
	private static final float g = -9.8f; // m/(s^2)
	private static final float mass = 80; // kg
	private static final float radius = 0.5f; // m

	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);
		air = new Air();
		water = new Water(10.0f, p.color(0, 0, 255, 100));

		ball = new Body(new PVector(dimX / 2, dimY - radius), new PVector(0, 0), mass, radius, p.color(255, 0, 0));

	}

	@Override
	public void draw(PApplet p, float dt) {
	    p.background(255);
	    water.display(p, plt);

	    if (ball != null) {
	        // Apply gravity force
	        PVector gravity = new PVector(0, mass * g);
	        ball.applyForce(gravity);

	        // Apply buoyant force if the ball is in water
	        if (water.isInside(ball)) {
	            PVector buoyancy = water.buoyantForce(ball, g);
	            ball.applyForce(buoyancy);
	        }

	        PVector drag = water.isInside(ball) ? water.drag(ball) : air.drag(ball);
	        ball.applyForce(drag);
	        ball.move(dt);
	        ball.display(p, plt);
	    }    
	}


	@Override
	public void mousePressed(PApplet p) {
		double[] worldPos = plt.getWorldCoord(p.mouseX, p.mouseY);
		PVector pos = new PVector((float)worldPos[0], (float)worldPos[1]);
		ball = new Body(pos, new PVector(0, 0), (float) Math.random()*101, radius, p.color(p.random(255), p.random(255), p.random(255)));

		if (water.isInside(ball)) {
	        PVector initialBuoyancy = water.buoyantForce(ball, g);
	        ball.applyForce(initialBuoyancy);
	    }
	}

	@Override
	public void keyPressed(PApplet p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(PApplet p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(PApplet p) {
		// TODO Auto-generated method stub

	}

}
