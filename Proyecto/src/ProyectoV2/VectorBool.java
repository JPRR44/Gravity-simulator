package ProyectoV2;

public class VectorBool {

	public boolean deteccionColisioni;
	public boolean deteccionColisionj;
	
	public VectorBool() {
		deteccionColisioni = false;
		deteccionColisionj = false;
	}
	
	
	public VectorBool(boolean valori, boolean valorj) {
		this.deteccionColisioni = valori;
		this.deteccionColisionj = valorj;
	}
	
	public String toString() {
		return "{ " + this.deteccionColisioni + "," + this.deteccionColisionj + " }";
	}
}