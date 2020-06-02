package ProyectoV2;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Lienzo extends JPanel{
	
	public static int ancho = 1200;
	public static int alto = 600;
	static String numero = JOptionPane.showInputDialog("¿Cuántas esferas quieres?").trim(); 
	static int cantidad = Integer.parseInt(numero);
	public static int radio = 15;
//	public  static ArrayList<ArrayList<VectorBool>> RelacionesColision = new ArrayList<>();
	
	//Esfera esfera1 = new Esfera(this, 150,75,3,1,25);
	//Esfera esfera2 = new Esfera(this,50,70,3,1,25);
	Random aleatorio = new Random();


	static Esfera ArregloEsferas[] = new Esfera[cantidad];//CAMBIAR ESTO CUANDO AGREGAS ESFERAS
	static Esfera ArregloE[] = new Esfera[ cantidad ];
	public static VectorBool[][] RelacionColisiones = new VectorBool[ArregloEsferas.length][ArregloEsferas.length];
	
	
	private void añadirArreglo() {
		//ArregloEsferas[0] = new Esfera(this,30,30,1,-1,10,10);
		//ArregloEsferas[1] = new Esfera(this,40,20,-1,1,20,20);
		
		for(int i = 0 ; i<= cantidad-1 ; i++) {
		
			ArregloEsferas[i] = new Esfera(this,(radio+aleatorio.nextInt( (ancho-radio+1) - (2*radio))) ,(radio+aleatorio.nextInt( (alto-radio+1) - (2*radio))),(1+aleatorio.nextInt( (3+1) - 1)),(1+aleatorio.nextInt( (3+1) - 1)),(20+aleatorio.nextInt( (30+1) - 20)),(10+aleatorio.nextInt( (50+1) - 10)));
		}
		//ArregloEsferas[0] = esfera1;
		//ArregloEsferas[1] = esfera2;

	}
	
	
	private void RelacionesColision() {
		for(int i = 0; i < ArregloEsferas.length; i++) {
			for(int j = 0; j < ArregloEsferas.length; j++) {
				if(j > i)
					RelacionColisiones[i][j] = new VectorBool();
			}
		}
	}

	private void move() {
		for(Esfera esfera: ArregloEsferas) {
			esfera.mover();
		}
		//ArregloEsferas[0].Colision(ArregloEsferas[1]);
		//ArregloEsferas[1].Colision(ArregloEsferas[2]);
		//ArregloEsferas[2].Colision(ArregloEsferas[0]);
		int maximo = ArregloEsferas.length;
		for(int i = 0; i < maximo; i++) {
			for(int j = 0; j < maximo; j++) {
				if(j > i) {
				//	ArregloEsferas[i].mover();
				//	ArregloEsferas[j].mover();
				
					RelacionColisiones[i][j] = ArregloEsferas[i].Colision(ArregloEsferas[j], RelacionColisiones[i][j]);
				
				}
			}
		}

	}

	public void paint(Graphics g) {
		super.paint(g);//Con esto pones en blanco la pantalla, para empezar de 0 NOT SURE IF THIS IS NECESARY TODO
		//Si no lo borra, se va recorriendo el circulo dejando los demas pintados
		Graphics2D g2d = (Graphics2D) g;//Aqui solo haces un cast de un objeto Graphics a uno Graphics2D
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//Esto es un render, lo puedes quitar pero es para que se quiten los bordes de la pelota, se ve me
		for(Esfera esfera: ArregloEsferas) {//Para pintar la pelota, el metodo esta en la clase ball, lo llamas
			try {
				esfera.paint(g2d);

			}catch(NullPointerException ex){//Había un error que arreglamos con un TRY CATCH 
				
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {

		JFrame frame = new JFrame("MOTOR");//Creas tu ventana con todas sus especificaciones
		Lienzo motor = new Lienzo();
		frame.add(motor);
		frame.setSize(ancho,alto);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		motor.añadirArreglo();
		motor.RelacionesColision();
		motor.repaint();
		while(true) { //Loop infinito para que siga recorriendo el circulo
			motor.move();//Usar el metodo que creaste para mover la pelota
			motor.repaint();//Repintar la pelota cada vez que la mueves para que se vea 
			Thread.sleep(10);/* Todo el codigo se usa en diferentes hilos, el main es uno que va siemre
						     ya que lo pusimos infinito, pero en algún momento lo tenemos que parar para
						     llamar al paint y que se ponga en blanco, se pinte otra vez, en blanco, otra vez
						     y asi... entonces lo "dormimos" 10 milisegundos para que le de tiempo a el otro hilo
						     "paint" a tomar el procesador y usar el metodo paint
			 */
		}
	}

}