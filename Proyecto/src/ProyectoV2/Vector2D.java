package ProyectoV2;

import java.lang.Math;

public class Vector2D extends Engine {

   protected double dX;
   protected double dY;

public Vector2D() {
      dX = dY = 0.0;
   }

   public Vector2D( double dX, double dY ) {
      this.dX = dX;
      this.dY = dY;
   }

   // Imprimir el vector
     
   public String toString() {
      return "Vector2D(" + dX + ", " + dY + ")";
   }

   // Magnitud del vector
 
   public double length() {
      return Math.sqrt ( dX*dX + dY*dY );
   }

   // Suma de vectores

   public Vector2D suma( Vector2D v1 ) {
       Vector2D v2 = new Vector2D( this.dX + v1.dX, this.dY + v1.dY );
       return v2;
   }
   
   //Resta el vector v1 de v
   public Vector2D resta( Vector2D v1 ) {
       Vector2D v2 = new Vector2D( this.dX - v1.dX, this.dY - v1.dY );
       return v2;
   }

   // Aumenta un vector en una escala dada

   public Vector2D escalar( double FactorEscala ) {
       Vector2D v2 = new Vector2D( this.dX*FactorEscala, this.dY*FactorEscala );
       return v2;
   }

   // Normaliza un vector con su magnitud - length-

   public Vector2D normalizar() {
      Vector2D v2 = new Vector2D();

      double length = Math.sqrt( this.dX*this.dX + this.dY*this.dY );
      if (length != 0) {
        v2.dX = this.dX/length;
        v2.dY = this.dY/length;
      }

      return v2;
   }   

   // Producto punto de dos vectores

   public double productoPunto ( Vector2D v1 ) {
        return this.dX*v1.dX + this.dY*v1.dY;
   }
   
   //Retorna verdadero si this es el vector que esta mas arriba - si tiene el valor de y mas grande-
   public boolean VectorAlto (Vector2D v1) {
	   Vector2D v2 = new Vector2D();
	   v2 = this.resta(v1);
	   if(v2.dY > 0)
		   return true;
	  
	   else
		   return false;
   }
   
  
}	