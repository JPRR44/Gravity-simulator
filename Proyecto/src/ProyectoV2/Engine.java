package ProyectoV2;

import java.awt.Color;
import java.util.Random;

import javax.swing.JPanel;

public class Engine extends JPanel {
	
	public double friction = 0.94;//Max valor = 1
	public double gravedad = 0.1;
	
	
	Random rand = new Random();
	float r = rand.nextFloat();
	float g = rand.nextFloat();
	float b = rand.nextFloat();
	Color randomColor = new Color(r, g, b);
	
	public final double getGravedad() {
		return gravedad;
	}

	public final void setGravedad(double gravedad) {
		this.gravedad = gravedad;
	}

	public final double getFriction() {
		return friction;
	}

	public final void setFriction(float friction) {
		this.friction = friction;
	}

}
