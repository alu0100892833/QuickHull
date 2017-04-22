package ull.alu0100892833.pai.quickhull;

import java.awt.Point;

/**
 * Extensión de la clase punto que se adapta para su uso por el algoritmo QuickHull.
 * Implementa dos métodos adicionales.
 * @author oscardp96
 * @since 21-4-2017
 */
public class PointHull extends Point {
	private static final long serialVersionUID = -5050854599403271210L;

	public PointHull() {
		super(); 
	}
	
	public PointHull(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Calcula el producto cruzado entre dos vectores: origin a end y origin a this.
     * @param origin Punto en el que se originan ambos vectores para los que se calcula el producto cruzado.
     * @param end El punto que determina el segundo vector.
     * @return 0, si los puntos son colineares. Un valor menor que cero si este punto se encuentra a la derecha del vector que va de origin a end. 
     * Un valor mayor que cero si se encuentra a la izquierda.
     */
    private double crossProduct(Point origin, Point end) {
        return (end.x - origin.x) * (this.y - origin.y) - (end.y - origin.y) * (this.x - origin.x);
    }

    /**
     * Un punto se considera a la derecha de una línea entre dos puntos origin y end si se encuentra a la derecha de un observador 
     * que mira desde el punto origin al punto end.
     * 
     * Este método utiliza el producto cruzado para determinar si el punto this se encuentra a la izquierda o a la derecha del segmento que une origin y end.
     * Para ello invoca al método crossProduct que calcula el producto cruzado.
     * @param origin Punto en el que se origina el segmento, o punto en el que se sitúa el observador.
     * @param end Punto en el que termina el segmento, o punto hacia el que mira el observador.
     */
    public boolean isRightOfLine(Point origin, Point end) {
    	return (crossProduct(origin, end) < 0); 
    }
    
    /**
     * Método que calcula la distancia entre el punto this y la línea dada por sus dos extremos.
     * @param origin Extremo origen de la línea.
     * @param end Extremo final de la línea.
     * @return Distancia, un valor double.
     */
    public double getDistanceToLine(Point origin, Point end) {  
        return Math.abs((end.getX() - origin.getX()) * (origin.getY() - this.y) - (origin.getX() - this.x) * (end.getY() - origin.getY()))
                / Math.sqrt(Math.pow(end.getX() - origin.getX(), 2) + Math.pow(end.getY() - origin.getY(), 2));
    }
}












//END
