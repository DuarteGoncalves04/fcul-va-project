package Physics;

import processing.core.PApplet;
import processing.core.PVector;

public class Water extends Fluid {

	private float waterHeight;
	private int color;
	private float density;

	public Water(float density, float waterHeight, int color) {
		super(density); //Density of Water in Kg/m³
		this.density = density;
		this.waterHeight = waterHeight;
		this.color = color;
	}

	public boolean isInside(Mover m) {
		return (m.getPos().y <= waterHeight);
	}
	
	public void setDensity(float density) {
		this.density = density;
	}
	
	public PVector buoyantForce(Body b, float g) {
		//F_b = - p + g * V
		// p = Density of Fluid
		// g = Gravity
		// V = Volume of Fluid ???
			 
		float Fb = this.density * Math.abs(g);
		
		return new PVector(0,Fb) ;
		
	}
	
	/*public PVector buoyantForce(Body b, float g) {
		//How much of sphere is submerged
		float depth = Math.max(0, waterHeight - (b.pos.y - b.radius));
		depth = Math.min(depth, b.radius * 2);
		
		//Vol of submerged sphere is 
		// V = PI * h^2 * (3*r-h) / 3
		// where h - depth of submerged part ; r - radius of sphere
		float submergedVolume = (float)(Math.PI * 
				Math.pow(depth, 2)*
				(3*b.radius-depth)/
				(3*b.radius));
		
		//Buoyance force
		float Fb = this.density * g * submergedVolume;
		
		return new PVector(0, Fb); //Upwards force is returned at the end
	}*/

	public void display(PApplet p, SubPlot plt) {
		p.pushStyle();
		p.noStroke();
		p.fill(color);
		float[] pp = plt.getBox(0, 0, plt.getWindow()[1], waterHeight);
		p.rect(pp[0], pp[1], pp[2], pp[3]);
		p.popStyle();
		p.pushStyle();
	}
}
