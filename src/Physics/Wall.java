package Physics;
import processing.core.PApplet;
import processing.core.PVector;

public class Wall {
	
	protected float x;
	protected float y;
	protected float w;
	protected float h;
	
	public Wall(float x, float y, float w, float h) {
		//Necessary but weird, gotta check if the constructor is fine
		//	works tho
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void display(PApplet p, SubPlot plt) {
		p.fill(p.color(211, 169, 108));
		p.noStroke();
		float[] pp = plt.getBox(0, 0, plt.getWindow()[1], w);
		p.rect(pp[0], pp[1], pp[2], pp[3]);
		p.popStyle();
	}


}
