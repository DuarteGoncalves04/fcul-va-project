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
	private Body ball, ball_1; // Ball_1 is the submerged ball 
	private Water water;
	private Air air;
	private Wall wall; // Wall object for boundary effect

	// PHYSICS VALUES
	private static final float g = -9.8f; // m/(s^2)
	private float mass = 80; // kg
	private float radius = 0.5f; // m
	private float radius_1 = 0.5f; // m
	private float waterDensity = 100.0f; // kg/m^3
	private float ballColor;
	private float ballColor_1;
	private float mass_1 = 100; // kg
	private float waterDensity_1 = 100.0f; // kg/m^3


	//GUI Components
	private ControlP5 cp5;
	private ControlP5 cp5_1;
	private Slider massSlider, radiusSlider, waterDensitySlider, colorSlider;
	private Slider massSlider_1, radiusSlider_1, waterDensitySlider_1, colorSlider_1;
	private Button resetButton, resetButton_1;
	private ColorPicker cp, cp_1;

	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);
		air = new Air();
		water = new Water(waterDensity, 25.0f, p.color(0, 0, 255, 100));
		ball = new Body(new PVector(dimX / 2, dimY - radius), new PVector(0, 0), mass, radius, p.color(255, 0, 0));
		ball_1 = new Body(new PVector(dimX/2, 10), new PVector(0, 0), mass_1, radius_1, p.color(255, 0, 0));
		wall = new Wall(dimX/2, dimY, 10, 400); // Wall to stop the ball from falling
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
				.setValue(ballColor);
		
		colorSlider = cp5.addSlider("Ball 1 Color")
				.setPosition(20,140)
				.setSize(150,20)
				.setRange(0, 250)
				.setValue(waterDensity);
		
			
		
		cp5_1 = new ControlP5(p);
		massSlider_1 = cp5_1.addSlider("Mass")
				.setPosition(900,50)
				.setSize(150,20)
				.setRange(1, 200)
				.setValue(mass_1);
		
		radiusSlider_1 = cp5_1.addSlider("Radius")
				.setPosition(900,80)
				.setSize(150,20)
				.setRange(0, 1)
				.setValue(radius_1);
		
		waterDensitySlider_1 = cp5_1.addSlider("Water Density")
				.setPosition(900,110)
				.setSize(150,20)
				.setRange(0, 2000)
				.setValue(waterDensity_1);
		
		colorSlider_1 = cp5.addSlider("Ball 2 Color")
				.setPosition(900,140)
				.setSize(150,20)
				.setRange(0, 250)
				.setValue(ballColor_1);
		
		
		// Reset the simulation
		resetButton = cp5.addButton("Reset")
				.setPosition(520, dimY)
				.setSize(150, 20)
				.onClick(new CallbackListener() {
					public void controlEvent(CallbackEvent theEvent){
						resetSimulation(p);
					}
				});	
		
		
	}
	

	private void resetSimulation(PApplet p) {
        ball = new Body(new PVector(dimX / 2, dimY - radius), new PVector(0, 0), mass, radius, p.color(colorSlider.getValue(),0,0));
        ball_1 = new Body(new PVector(dimX/2, 10), new PVector(0, 0), mass_1, radius_1, p.color(255, 0, 0));
    }

	@Override
	public void draw(PApplet p, float dt) {
	    p.background(255);
	    p.pushStyle();
		p.fill(200, 200, 200);
		p.noStroke();
		p.rect(10, 30, 250, 150);  
		p.popStyle(); 
		
	    p.pushStyle();
		p.fill(200, 200, 200);
		p.noStroke();
		p.rect(890, 30, 250, 150);  
		p.popStyle();
		// Draw GUI background section
		
		
		// Values for the dropped ball
		mass = massSlider.getValue();
        waterDensity = waterDensitySlider.getValue();
		radius = radiusSlider.getValue();
		ballColor = p.color(colorSlider.getValue(),0,0);
		
		// Values for the submerged ball
		mass_1 = massSlider_1.getValue();
		waterDensity_1 = waterDensitySlider_1.getValue();
		radius_1 = radiusSlider_1.getValue();

	    water.display(p, plt);
	    wall.display(p);

	    if (ball != null) {
	        // Apply gravity force
	        PVector gravity = new PVector(0, mass * g);
	        ball.applyForce(gravity);

	        // Apply buoyant force if the ball is in water
	        if (water.isInside(ball)) {
	            PVector buoyancy = water.buoyantForce(ball, g);
	            ball.applyForce(buoyancy);
	            ball.checkCollisionWall(wall);
	        }

	        PVector drag = water.isInside(ball) ? water.drag(ball) : air.drag(ball);
	        ball.applyForce(drag);
	        ball.move(dt);
	        ball.display(p, plt);
	    }
	    
	    if (ball_1 != null) {
	        // Apply gravity force
	        PVector gravity = new PVector(0, mass_1 * g);
	        ball_1.applyForce(gravity);

	        // Apply buoyant force if the ball is in water
	        if (water.isInside(ball_1)) {
	            PVector buoyancy = water.buoyantForce(ball_1, g);
	            ball_1.applyForce(buoyancy);
	        }

	        PVector drag = water.isInside(ball) ? water.drag(ball) : air.drag(ball);
	        ball_1.applyForce(drag);
	        ball_1.move(dt);
	        ball_1.display(p, plt);
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
