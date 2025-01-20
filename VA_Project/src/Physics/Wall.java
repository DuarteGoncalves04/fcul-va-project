package Physics;
import processing.core.PApplet;
import processing.core.PVector;

public class Wall {
	
	protected float x;
	protected float y;
	protected float w;
	protected float h;
	
	public Wall(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void display(PApplet p) {
		p.fill(p.color(0, 250, 0));
		p.noStroke();
		p.rectMode(p.CENTER);
		p.rect(x, y, w, h);
	}


}
