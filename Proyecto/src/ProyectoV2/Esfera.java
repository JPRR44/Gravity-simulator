package ProyectoV2;
import java.awt.Color;
import java.awt.Graphics2D;


public class Esfera extends CuerpoSolido {

	public int radio;

	public Lienzo lienzo;

	public Esfera(int x, int y, int xa, int ya, int radio, int masa) { // constructor para pruebas
		super(x,y,xa,ya,30);
		this.radio = radio;
	}
	
	public Esfera(Lienzo lienzo, int x, int y, int xa, int ya, int radio, int masa) {//Constructor BAll
		super(x,y,xa,ya, masa);
		this.lienzo = lienzo;	   //Lo tienes con referencia a game por que lo vas a usar en esta clase
		this.radio = radio/2;

	}

	public final VectorBool Colision(CuerpoSolido o,VectorBool Relacion) { // Retorna true si dos esferas estan por colisionar o estan colisionando, pero no maneja la colision
		// Usar pitagoras para sacar la distancia
		VectorBool RelacionNueva = Relacion;
		
		if(o instanceof Esfera) { // se deja preparado para poder hacer comparaciones con otros tipos de cuerpo solido
			
			float distX = (float) (o.Posicion.dX - super.Posicion.dX);
			float distY = (float) (o.Posicion.dY - super.Posicion.dY);
			double distancia = Math.sqrt(( (distX * distX) + (distY * distY) ));
			double sumaRadio = ((Esfera) o).radio + this.radio;
			// si la distancia es menor a la suma de los radios de los circulos, se estan tocando
			if(distancia < sumaRadio) {
				System.out.println("Colision\n");
				while(RelacionNueva.deteccionColisioni == false && RelacionNueva.deteccionColisionj == false){
				ManejoColision(o);
				RelacionNueva.deteccionColisioni = true; // se pone en true las detecciones de colision una vez que ya se manejo dicha colision en la linea anterior
				RelacionNueva.deteccionColisionj = true; // al hacer esto se evita que las pelotas se fusionen con colisiones muy largas
				
				}
				
			/*	if(this.deteccionColision == false && o.deteccionColision == true) {
					this.deteccionColision = true;
				}
				
				if(this.deteccionColision == true && o.deteccionColision == false) {
					o.deteccionColision = true;
				}
			*/	
				
				return RelacionNueva;
			}
			
		else if(distancia >= sumaRadio) { // si no se estan colisionnando, la colision de deteccion de cada CuerpoSolido se pasara a falso 
				
			//	if((this.deteccionColision == true && o.deteccionColision == false) 
			//			|| (o.deteccionColision == true && this.deteccionColision == false))
			//		return true;
				
			//	else {
					RelacionNueva.deteccionColisioni = false;
					RelacionNueva.deteccionColisionj = false;
			//	}

			}
			return RelacionNueva;
		}
		return RelacionNueva;
		
		
	}
	
	public final void ManejoColision(CuerpoSolido o) { // maneja la colision 
		double e = 0.9; // coeficiente restitucion
		Vector2D n; 
		n = this.Posicion.resta(o.Posicion);
		n = n.normalizar();
		
		double v1p = this.Velocidad.productoPunto(n);
		double v2p = o.Velocidad.productoPunto(n);
		
	double v1pp = ( ((this.masa - e*o.masa)*v1p) + ((1+e)*o.masa*v2p) ) / (this.masa + o.masa);
	double v2pp = ( ((o.masa - e*this.masa)*v2p) + ((1+e)*this.masa*v1p) ) / (this.masa + o.masa);
	
	Vector2D v1Final = this.Velocidad.suma(n.escalar(v1pp - v1p));
	Vector2D v2Final = o.Velocidad.suma(n.escalar(v2pp-v2p));
	
	this.Velocidad.dX = v1Final.dX;
	this.Velocidad.dY = v1Final.dY;
	o.Velocidad.dX = v2Final.dX;
	o.Velocidad.dY = v2Final.dY;
	
	
	}	

	final void mover() {
		
		if(super.Posicion.dY + this.radio + super.Velocidad.dY> lienzo.getHeight())//TODO se pasa medio radio al rebotar
			this.Velocidad.dY = -this.Velocidad.dY * friction;
		
		else if(super.Posicion.dY <= 0)//COMENTAR ESTAS DOS LINEAS PARA PONER O QUITAR EL TOPE SUPERIOR
			this.Velocidad.dY = -this.Velocidad.dY; //* friction;
		
		else
			this.Velocidad.dY += gravedad;
		
		if(super.Posicion.dX + this.radio + this.Velocidad.dX > lienzo.getWidth() || this.Posicion.dX - this.radio <= 0)
			this.Velocidad.dX = -this.Velocidad.dX;
		
		

		//Se comprara con getWidth() y getHeight() que son los metodos para ver el largo y ancho de tu 
		//ventana, así comparas cuando llegas al límite

		//super.Posicion.dX = super.Posicion.dX + super.Velocidad.dX;// X y Y se mueven dependienod de xa y ya
		super.Posicion.dY = super.Posicion.dY + super.Velocidad.dY;	
		super.Velocidad.dY = super.Velocidad.dY;
		super.Velocidad.dX = super.Velocidad.dX;
		super.Posicion.dX = super.Velocidad.dX + super.Posicion.dX + (gravedad*0.4);

	}

	public final void paint(Graphics2D g) {
		g.setColor(Color.black);
		g.drawOval((int)(super.Posicion.dX - (radio)),(int) (super.Posicion.dY - (radio)), radio*2, radio*2);
		g.setColor(randomColor);
		g.fillOval((int)(super.Posicion.dX - (radio)),(int) (super.Posicion.dY - (radio)), radio*2, radio*2);
	};	
}