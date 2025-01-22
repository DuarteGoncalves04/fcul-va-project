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
	private Body ball, ball2, metalBall, corkBall, basketball, rubberBall;
	private Water water;
	private Air air;
	private Wall wall; // Wall object for boundary effect

	// PHYSICS VALUES
	private static final float g = -9.8f; // m/(s^2)
	private float mass = 100; // kg
	private float mass2 = 100; // kg
	private float radius = 0.5f; // m
	private float radius2 = 0.5f; // m
	private int ballColor,ballColor2;
	private float waterDensity = 900.0f; // kg/m^3

	// GUI Components
	private ControlP5 ball1Property, ball2Property, simProperty;
	private Slider massSlider, radiusSlider, waterDensitySlider;
	private Slider massSlider2, radiusSlider2;
	private ColorPicker cp1, cp2;

	@Override
	public void setup(PApplet p) {
		plt = new SubPlot(window, viewport, p.width, p.height);
		air = new Air();
		initBalls(p);
		water = new Water(waterDensity, 25.0f, p.color(0, 181, 204, 100));
		wall = new Wall(dimX / 2, 0, 5, 400); // Wall to stop the ball from falling
		GUI(p);
	}

	private void initBalls(PApplet p) {
		ballColor = p.color(255, 0, 0);
		ballColor2 = p.color(0, 255, 0);
		ball = new Body(new PVector(dimX - 10 / 2, dimY - radius), new PVector(0, 0), mass, radius, ballColor);
		ball2 = new Body(new PVector(dimX / 2, 10), new PVector(0, 0), mass2, radius2, ballColor2);

		//Sprites
		//metalBall = new Body(new PVector(dimX - 10 / 2, dimY), new PVector(0, 0),10,0.3f,p.color(181,184,177));
		//corkBall = new Body(new PVector(dimX / 2, dimY), new PVector(0, 0),0.2f,0.4f,p.color(156,111,62));
		//basketball = new Body(new PVector(dimX / 3, dimY), new PVector(0, 0),0.6f,0.24f,p.color(97,51,35));
		//rubberBall = new Body(new PVector(dimX / 4, dimY), new PVector(0, 0),1.2f,0.3f,p.color(58,54,59));
	}

	private void GUI(PApplet p) {
		// Ball 1 Properties
		ball1Property = new ControlP5(p);
		ball1Property.addLabel("BALL 1")
				.setPosition(20, 40)
				.setColorValue(0);
		massSlider = ball1Property.addSlider("Mass")
				.setPosition(20, 60)
				.setSize(150, 20)
				.setRange(1, 200)
				.setValue(mass);
		radiusSlider = ball1Property.addSlider("Radius")
				.setPosition(20, 85)
				.setSize(150, 20)
				.setRange(0.1f, 1)
				.setValue(radius);
		cp1 = ball1Property.addColorPicker("Color")
				.setPosition(20, 110)
				.setColorValue(ballColor);

		// Ball 2 Properties
		ball2Property = new ControlP5(p);
		ball2Property.addLabel("BALL 2")
				.setPosition(20, 180)
				.setColorValue(0);
		massSlider2 = ball2Property.addSlider("Mass")
				.setPosition(20, 200)
				.setSize(150, 20)
				.setRange(1, 200)
				.setValue(mass2);
		radiusSlider2 = ball2Property.addSlider("Radius")
				.setPosition(20, 225)
				.setSize(150, 20)
				.setRange(0.1f, 1)
				.setValue(radius2);
		cp2 = ball2Property.addColorPicker("Color")
				.setPosition(20, 250)
				.setColorValue(ballColor2);

		// Simulation Properties
		simProperty = new ControlP5(p);
		simProperty.addLabel("SIMULATION PROPERTIES")
				.setPosition(20, 320)
				.setColorValue(0);
		waterDensitySlider = simProperty.addSlider("Water Density")
				.setPosition(20, 340)
				.setSize(150, 20)
				.setRange(0, 2000)
				.setValue(waterDensity);

		simProperty.addButton("Reset")
				.setPosition(20, 365)
				.setSize(150, 20)
				.onClick(new CallbackListener() {
					public void controlEvent(CallbackEvent theEvent) {
						resetSimulation(p);
					}
				});
		
		simProperty.addButton("Metal Ball")
				.setPosition(20, 390)
				.setSize(70, 20)
				.onClick(new CallbackListener() {
					public void controlEvent(CallbackEvent theEvent) {
						metalBall = new Body(new PVector(dimX - 10 / 2, dimY), new PVector(0, 0),10,0.3f,p.color(181,184,177));
					}
				});
		
		simProperty.addButton("Cork Ball")
				.setPosition(20, 415)
				.setSize(70, 20)
				.onClick(new CallbackListener() {
					public void controlEvent(CallbackEvent theEvent) {
						corkBall = new Body(new PVector(dimX / 2, dimY), new PVector(0, 0),0.2f,0.4f,p.color(156,111,62));
					}
				});

		simProperty.addButton("Basketball")
				.setPosition(100, 390)
				.setSize(70, 20)
				.onClick(new CallbackListener() {
					public void controlEvent(CallbackEvent theEvent) {
						basketball = new Body(new PVector(dimX / 3, dimY), new PVector(0, 0),0.6f,0.24f,p.color(97,51,35));
					}
				});

		simProperty.addButton("Rubber Ball")
				.setPosition(100, 415)
				.setSize(70, 20)
				.onClick(new CallbackListener() {
					public void controlEvent(CallbackEvent theEvent) {
						rubberBall = new Body(new PVector(dimX / 4, dimY), new PVector(0, 0),1.2f,0.3f,p.color(58,54,59));
					}
				});
	}

	private void resetSimulation(PApplet p) {
		initBalls(p);
	}

	@Override
	public void draw(PApplet p, float dt) {
		p.background(255);

		// GUI Background
		p.pushStyle();
		p.fill(200, 200, 200);
		p.noStroke();
		p.rect(10, 30, 280, 420);
		p.popStyle();

		waterDensity = waterDensitySlider.getValue();
		water.display(p, plt);
		water.setDensity(waterDensity);
		System.out.println("Water Density: " + waterDensity);
		wall.display(p, plt); // Displays the sand box (kinda ugly but wtv)

		if (ball != null) {
			ballProperties(p, dt, ball, mass, "Ball 1");
			realTimeChanges(p, ball, mass, massSlider, radius, radiusSlider, ballColor, cp1);
		}

		if (ball2 != null) {
			ballProperties(p, dt, ball2, mass2, "Ball 2");
			realTimeChanges(p, ball2, mass2, massSlider2, radius2, radiusSlider2, ballColor2, cp2);
		}

		//Sprites
		if(metalBall != null) {
			ballProperties(p, dt, metalBall, 10, "Metal Ball");
		}
		if(corkBall != null) {
			ballProperties(p, dt, corkBall, 0.2f, "Cork Ball");
		}
		if(basketball != null) {
			ballProperties(p, dt, basketball, 0.6f, "Basketball");
		}
		if(rubberBall != null) {
			ballProperties(p, dt, rubberBall, 1.2f, "Rubber Ball");
		}
	}

	private void realTimeChanges(PApplet p, Body ball, float ballmass, Slider massSlider, float ballradius,
			Slider radiusSlider, int ballcolor, ColorPicker cp) {
		ballmass = massSlider.getValue();
		ball.setMass(ballmass);
		ballradius = radiusSlider.getValue();
		ball.setRadius(ballradius);
		ballcolor = (int) cp.getValue();
		ball.setColor(ballcolor);
	}

	private void ballProperties(PApplet p, float dt, Body ball, float ballmass, String ballName) {
		// Apply gravity force
		PVector gravity = new PVector(0, ballmass * g);
		ball.applyForce(gravity);

		// Apply buoyant force if the ball is in water
		if (water.isInside(ball)) {
			PVector buoyancy = water.buoyantForce(ball, g);
			ball.applyForce(buoyancy);
			ball.checkCollisionWall(wall); // Verifies if the ball is hitting the sand
		}

		PVector drag = water.isInside(ball) ? water.drag(ball) : air.drag(ball);
		ball.applyForce(drag);
		ball.move(dt);
		ball.display(p, plt);

		// Draw name Ball
		drawBallName(p, ball, ballName);
	}

	private void drawBallName(PApplet p, Body ball, String name) {
		double[] ballPos = { (double) ball.getPos().x, (double) ball.getPos().y };
		float[] ballPixelPos = plt.getPixelCoord(ballPos);
		p.pushStyle();
		p.fill(0); // Black text
		p.textAlign(p.CENTER, p.CENTER);
		p.textSize(12);
		p.text(name, ballPixelPos[0], ballPixelPos[1]);
		p.popStyle();
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
