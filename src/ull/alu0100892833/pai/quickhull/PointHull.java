package ull.alu0100892833.pai.quickhull;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.sun.glass.ui.TouchInputSupport;

/**
 * Extensión de la clase punto que se adapta para su uso por el algoritmo QuickHull.
 * Implementa dos métodos adicionales.
 * @author oscardp96
 * @since 21-4-2017
 */
public class PointHull extends Point {
	private static final long serialVersionUID = -5050854599403271210L;
	private static final int INFINITY = 9999;
	private static final int MINUS_INFINITY = -9999;
	private static final int ZERO = 0;

	public PointHull() {
		super(); 
	}
	
	public PointHull(int x, int y) {
		super(x, y);
	}
	
	public PointHull(PointHull other) {
		super(other.x, other.y);
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
    	return (crossProduct(origin, end) > 0); 
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
    
    @Override
    public String toString() {
    	return "[" + x + ", " + y + "]";
    }
    
    /**
     * Este método permite obtener el punto situado más a la izquierda de entre un conjunto de puntos.
     * @param points
     * @return
     */
    public static PointHull getLeftMostPoint(List<PointHull> points) {
    	PointHull leftMostPoint = new PointHull(INFINITY, ZERO);
    	for (PointHull point : points)
    		if (point.getX() < leftMostPoint.getX())
                leftMostPoint = point;
        return leftMostPoint;
    }
    
    /**
     * Este método permite obtener el punto situado más a la derechca de entre un conjunto de puntos.
     * @param points
     * @return
     */
    public static PointHull getRightMostPoint(List<PointHull> points) {
    	PointHull rightMostPoint = new PointHull(MINUS_INFINITY, ZERO);
    	for (PointHull point : points)
    		if (point.getX() > rightMostPoint.getX())
                rightMostPoint = point;
        return rightMostPoint;
    }
    
    /**
     * Este método permite obtener el punto que se encuentre más arriba de entre la lista de puntos dada.
     * @param points
     * @return
     */
    public static PointHull getHighestPoint(List<PointHull> points) {
    	PointHull highestPoint = new PointHull(ZERO, INFINITY);
    	for (PointHull point : points)
    		if (point.getY() < highestPoint.getY())
                highestPoint = point;
        return highestPoint;
    }
    
    /**
     * Este método permite obtener el punto que se encuentre más arriba de entre la lista de puntos dada.
     * @param points
     * @return
     */
    public static PointHull getLowestPoint(List<PointHull> points) {
    	PointHull lowestPoint = new PointHull(ZERO, MINUS_INFINITY); 
    	for (PointHull point : points)
    		if (point.getY() > lowestPoint.getY())
                lowestPoint = point;
        return lowestPoint;
    }
    
    /**
     * Este método permite obtener una lista de puntos que se encuentren en coordenadas superiores a un
     * punto de referencia, de entre una lista de puntos.
     * @param reference Punto de referencia.
     * @param points Lista de puntos donde buscar los que se encuentran en coordenadas superiores.
     * @return
     */
    public static List<PointHull> getAbovePoints(PointHull reference, List<PointHull> points) {
    	List<PointHull> abovePoints = new ArrayList<PointHull>();
    	for (PointHull point : points)
    		if (point.getY() <= reference.getY())
    			abovePoints.add(point);
    	return abovePoints;
    }
    
    /**
     * Este método permite obtener una lista de puntos que se encuentren en coordenadas inferiores a un
     * punto de referencia, de entre una lista de puntos.
     * @param reference Punto de referencia.
     * @param points Lista de puntos donde buscar los que se encuentran en coordenadas inferiores.
     * @return
     */
    public static List<PointHull> getBelowPoints(PointHull reference, List<PointHull> points) {
    	List<PointHull> belowPoints = new ArrayList<PointHull>();
    	for (PointHull point : points) 
    		if (point.getY() >= reference.getY())
    			belowPoints.add(point);
    	return belowPoints;
    }
    
    /**
     * Este método permite obtener una lista de puntos que se encuentren a la izquierda de un
     * punto de referencia, de entre una lista de puntos.
     * @param reference Punto de referencia.
     * @param points Lista de puntos donde buscar los que se encuentran a la izquierda de la referencia.
     * @return
     */
    public static List<PointHull> getPointsOnTheLeft(PointHull reference, List<PointHull> points) {
    	List<PointHull> leftPoints = new ArrayList<PointHull>();
    	for (PointHull point : points)
    		if (point.getX() < reference.getX())
    			leftPoints.add(point);
    	return leftPoints;
    }
    
    /**
     * Este método permite obtener una lista de puntos que se encuentren a la derecha de un
     * punto de referencia, de entre una lista de puntos.
     * @param reference Punto de referencia.
     * @param points Lista de puntos donde buscar los que se encuentran a la derecha de la referencia.
     * @return
     */
    public static List<PointHull> getPointsOnTheRight(PointHull reference, List<PointHull> points) {
    	List<PointHull> abovePoints = new ArrayList<PointHull>();
    	for (PointHull point : points)
    		if (point.getX() > reference.getX())
    			abovePoints.add(point);
    	return abovePoints;
    }
    
    /**
     * Este método permite seleccionar el punto más cercano de entre una lista de puntos.
     * @param points
     * @return
     */
    public PointHull getClosestPoint(List<PointHull> points) {
    	double distance = INFINITY;
    	PointHull closestPoint = null;
    	for (PointHull pointHull : points)
    		if (distance(pointHull) < distance) {
    			distance = distance(pointHull);
    			closestPoint = pointHull;
    		}
    	return closestPoint;
    }
    
    public PointHull getClosestPointInY(List<PointHull> points) {
    	double distance = INFINITY;
    	PointHull closest = null;
    	for (PointHull pointHull : points)
    		if (Math.abs(getY() - pointHull.getY()) < distance) {
    			closest = pointHull;
    			distance = Math.abs(getY() - pointHull.getY());
    		}
    	return closest;
    }
    
    public PointHull getClosestPointInX(List<PointHull> points) {
    	double distance = INFINITY;
    	PointHull closest = null;
    	for (PointHull pointHull : points)
    		if (Math.abs(getX() - pointHull.getX()) < distance) {
    			closest = pointHull;
    			distance = Math.abs(getX() - pointHull.getX());
    		}
    	return closest;
    }
}












//END
