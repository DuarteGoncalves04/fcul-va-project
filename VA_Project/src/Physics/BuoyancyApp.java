package Physics;

import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;
import controlP5.*;

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
	private float mass = 80; // kg
	private float radius = 0.5f; // m
	private float waterDensity = 100.0f; // kg/m^3


	//GUI Components
	private ControlP5 cp5;
	private Slider massSlider, radiusSlider, waterDensitySlider;
	private Button resetButton;

	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);
		air = new Air();
		water = new Water(waterDensity, 25.0f, p.color(0, 0, 255, 100));
		ball = new Body(new PVector(dimX / 2, dimY - radius), new PVector(0, 0), mass, radius, p.color(255, 0, 0));
		GUI(p);
	}

	private void GUI(PApplet p) {
		cp5 = new ControlP5(p);

		massSlider = cp5.addSlider("Mass")
				.setPosition(20,50)
				.setSize(150,20)
				.setRange(1, 200)
				.setValue(mass);
		
		radiusSlider = cp5.addSlider("Radius")
				.setPosition(20,80)
				.setSize(150,20)
				.setRange(0, 1)
				.setValue(radius);
		
		waterDensitySlider = cp5.addSlider("Water Density")
				.setPosition(20,110)
				.setSize(150,20)
				.setRange(0, 2000)
				.setValue(waterDensity);
		
		resetButton = cp5.addButton("Reset")
				.setPosition(20, 140)
				.setSize(150, 20)
				.onClick(new CallbackListener() {
					public void controlEvent(CallbackEvent theEvent){
						resetSimulation(p);
					}
				});		
	}

	private void resetSimulation(PApplet p) {
        ball = new Body(new PVector(dimX / 2, dimY - radius), new PVector(0, 0), mass, radius, p.color(255, 0, 0));
    }

	@Override
	public void draw(PApplet p, float dt) {
	    p.background(255);

		// Draw GUI background section
		p.pushStyle();
		p.fill(200, 200, 200);
		p.noStroke();
		p.rect(10, 30, 250, 150);  
		p.popStyle(); 

		mass = massSlider.getValue();
        waterDensity = waterDensitySlider.getValue();
		radius = radiusSlider.getValue();

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
		
		cp5.draw();
	}


	@Override
	public void mousePressed(PApplet p) {
		// TODO Auto-generated method stub
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
