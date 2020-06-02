package ProyectoV2;


public abstract class CuerpoSolido extends Vector2D {

	public Vector2D Posicion = new Vector2D();//x, y
	public Vector2D Velocidad = new Vector2D();//dx, dy 
	public Vector2D Fuerza = new Vector2D(0,-9.81);//Acumulador de fuerzas en X = 0, en Y = -9.81 
	public int masa;//Masa del cuerpo sólido

	
	
	public CuerpoSolido(int x, int y, int xa, int ya ,int masa){
		this.Posicion.dX = x;
		this.Posicion.dY = y;
		this.Velocidad.dX = xa;
		this.Velocidad.dY = ya;
		this.masa = masa;
	}

	
	public abstract VectorBool Colision (CuerpoSolido c,VectorBool Relacion);
}