package Physics;

import processing.core.PApplet;
import processing.core.PVector;

public class Water extends Fluid {

	private float waterHeight;
	private int color;
	private static float density = 100.0f;

	public Water(float waterHeight, int color) {
		super(density); //Density of Water in Kg/m³
		this.waterHeight = waterHeight;
		this.color = color;
	}

	public boolean isInside(Mover m) {
		return (m.getPos().y <= waterHeight);
	}
	
	public PVector buoyantForce(Body b, float g) {
		//F_b = - p + g * V
		// p = Density of Fluid
		// g = Gravity
		// V = Volume of Fluid ???
			 
		float Fb = this.density * Math.abs(g);
		
		return new PVector(0,Fb) ;
		
	}

	public void display(PApplet p, SubPlot plt) {
		p.pushStyle();
		p.noStroke();
		p.fill(color);
		float[] pp = plt.getBox(0, 0, plt.getWindow()[1], waterHeight);
		p.rect(pp[0], pp[1], pp[2], pp[3]);
		p.popStyle();
	}
}
