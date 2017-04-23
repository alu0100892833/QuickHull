package ull.alu0100892833.pai.quickhull;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.LinkedList;

import ull.alu0100892833.pai.quickhull.exceptions.NoPointsException;
import ull.alu0100892833.pai.quickhull.PointHull;

/**
 * Esta clase permite instanciar objetos que calculen la envolvente convexa para un conjunto de puntos utilizando el algoritmo QuickHull.
 * Hace las veces de modelo para el plano, pues almacena los puntos, y calcula la envolvente convexa para los mismos si se aplica el método quickHull().
 * @author oscardp96
 * @since 21-4-2017
 */
public class QuickHull {
	private static final int DEFAULT_POINT_RADIUS = 5;
	private static final int DEFAULT_POINT_DIAMETER = 2 * DEFAULT_POINT_RADIUS;
	private static final int ZERO = 0;
	
	private HashSet<PointHull> points;
	private ArrayList<PointHull> convexHull; 

	/**
	 * Constructor por defecto.
	 */
	public QuickHull() {
		points = new HashSet<>();
		convexHull = new ArrayList<>();
	}
	
	/**
	 * Constructor por parámetros que inicializa el modelo con un número determinado de puntos aleatorios.
	 * @param nPoints Número de puntos.
	 * @param sideLimit Límite de anchura para colocar los puntos. 
	 * @param heightLimit Límite de altura para colocar los puntos.
	 */
	public QuickHull(int nPoints, int sideLimit, int heightLimit) {
		points = new HashSet<>();
		convexHull = new ArrayList<>();
		setRandomPoints(nPoints, sideLimit, heightLimit);
	}
	
	/**
	 * Procedimiento que genera un determinado número de puntos aleatorios.
	 * @param nPoints Número de puntos.
	 * @param sideLimit Límite de anchura para colocar los puntos. 
	 * @param heightLimit Límite de altura para colocar los puntos.
	 */
	public void setRandomPoints(int nPoints, int sideLimit, int heightLimit) {
		Random random = new Random();
		while (this.points.size() < nPoints) {
			int x = random.nextInt(sideLimit);
			int y = random.nextInt(heightLimit);
			this.points.add(new PointHull(x, y));
		}
	}
	
	/**
	 * Getter de los puntos del plano.
	 * @return
	 */
	public HashSet<PointHull> getPoints() {
		return points;
	}

	/**
	 * Getter de la convexHull.
	 * @return
	 */
	public ArrayList<PointHull> getConvexHull() {
		return convexHull;
	} 

	/**
	 * Añade un elemento a la convexHull.
	 * @param point
	 */
	public void addToConvexHull(PointHull point) {
		convexHull.add(point);
	}
	
	/**
	 * Añade toda una colección a la convexHull.
	 * @param collection
	 */
	public void addAllToConvexHull(Collection<PointHull> collection) {
		convexHull.addAll(collection);
	}
	
	/**
	 * Algoritmo QuickHull que encuentra la envolvente convexa para el conjunto de puntos almacenado en el HashSet points.
	 * @throws NoPointsException En el caso de que los puntos no hayan sido definidos.
	 * @throws InterruptedException Si falla el temporizador.
	 */
	public void quickHull() throws NoPointsException {
		if ((getPoints().isEmpty()) || (getPoints().size() == 1))
			throw new NoPointsException("No se puede calcular la envolvente convexa en un espacio sin al menos dos puntos.");
		
		// BUSCA LOS VALORES MÁS A LA DERECHA Y MÁS A LA IZQUIERDA
		PointHull rightMostPoint = new PointHull((int) Double.NEGATIVE_INFINITY, ZERO);
        PointHull leftMostPoint = new PointHull((int) Double.POSITIVE_INFINITY, ZERO);
        for (PointHull point : getPoints()) {
            if (point.getX() > rightMostPoint.getX()) {
                rightMostPoint = point;
            } else if (point.getX() < leftMostPoint.getX()) { 
                leftMostPoint = point;
            }
        }
        
        // DIVIDE LOS PUNTOS RESTANTES EN DOS MITADES SEGÚN ESTÉN A LA DERECHA O A LA IZQUIERDA DEL SEGMENTO QUE UNE rightMostPoint CON leftMostPoint
        List<PointHull> leftOfLine = new LinkedList<>();
        List<PointHull> rightOfLine = new LinkedList<>();
        for (PointHull point : getPoints()) {
            if (point.equals(rightMostPoint) || point.equals(leftMostPoint))
                continue;
            else if (point.isRightOfLine(leftMostPoint, rightMostPoint))
                rightOfLine.add(point);
            else 
                leftOfLine.add(point);
        }
        
        // ENCONTRAR LOS PUNTOS MÁS LEJANOS E IR AÑADIENDO A LA SOLUCIÓN
        addToConvexHull(leftMostPoint);
        addToConvexHull(rightMostPoint);
        
        find(rightOfLine, leftMostPoint, rightMostPoint);
        find(leftOfLine, rightMostPoint, leftMostPoint);
        
	}
	
	/**
	 * Este método recursivo complementa al método quickHull.
	 * Busca los puntos de la envolvente convexa en el conjunto dado por su primer parámetro, que estén a la derecha de la línea orientada desde origin hasta end.
	 * @param points List de puntos entre los que hay que encontrar aquellos pertenecientes a la envolvente convexa que estén a la derecha de la línea orientada desde origin hasta end.
	 * @param origin Origen de la línea o donde se sitúa el observador. La línea se orienta desde este punto hasta end.
	 * @param end Final de la línea o hacia donde mira el observador. La línea se orienta desde origin hasta este punto.
	 * @throws InterruptedException Si falla el temporizador.
	 */
	private void find(List<PointHull> points, PointHull origin, PointHull end) {
		
		// SI LA LISTA DE PUNTOS ESTÁ VACÍA, DEVOLVER UNA LISTA VACÍA
		if (points.isEmpty())
			return;
		// SI LA LISTA TIENE TAMAÑO 1, DEVOLVER ESE ÚNICO ELEMENTO
		else if (points.size() == 1) {
			addToConvexHull(points.get(0));
			return;
		}
		
		// ENCONTRAR EL PUNTO MÁS ALEJADO DEL SEGMENTO origin - end
		PointHull maxDistancePoint = points.get(0);
        double distance = 0.0;
        for (PointHull point : points) {
            if (point.getDistanceToLine(origin, end) > distance) {
                distance = point.getDistanceToLine(origin, end);
                maxDistancePoint = point;
            }
        }
        
        // ESTE TAMBIÉN PUEDE ELIMINARSE YA DEL CONJUNTO DE PUNTOS
        points.remove(maxDistancePoint);
		
        // LOS PUNTOS origin, end Y maxDistancePoint PARTICIONAN EL CONJUNTO DE PUNTOS RESTANTE EN TRES GRUPOS
        // * EL CONJUNTO subSet1 COMPRENDE LOS PUNTOS A LA DERECHA DE LA LÍNEA ORIENTADA DE origin A maxDistancePoint
        // * EL CONJUNTO subSet2 COMPRENDE LOS PUNTOS A LA DERECHA DE LA LÍNEA ORIENTADA DE maxDistancePoint a end
        // * LOS PUNTOS RESTANTES SON AQUELLOS SITUADOS EN EL INTERIOR DEL TRIÁNGULO FORMADO POR origin, maxDistancePoint Y end.
        List<PointHull> subSet1 = new LinkedList<>(); 
        List<PointHull> subSet2 = new LinkedList<>(); 
        for (PointHull point : points) {
            if (point.isRightOfLine(origin, maxDistancePoint))
                subSet1.add(point);
            else if (point.isRightOfLine(maxDistancePoint, end))
                subSet2.add(point);
        }
        
        // AÑADIR LOS NUEVOS PUNTOS
        addToConvexHull(maxDistancePoint);
        addToConvexHull(origin);
        addToConvexHull(end);
        
        // REPETIR EL PROCESO PARA LOS SUBCONJUNTOS EXTERIORES AL TRIÁNGULO
        find(subSet1, origin, maxDistancePoint);
        find(subSet2, maxDistancePoint, end);
	}
	
	/**
	 * Método que dibuja tanto los puntos como la envolvente convexa (si ha sido calculada).
	 * @param g Objeto Graphics sobre el que se dibujará.
	 */
	public void paintHull(Graphics g) {
		// PINTAR LOS PUNTOS
		for (PointHull point : getPoints()) {
			g.fillOval((int) point.getX() - DEFAULT_POINT_RADIUS, (int) point.getY() - DEFAULT_POINT_RADIUS, 
					DEFAULT_POINT_DIAMETER, DEFAULT_POINT_DIAMETER);
		}
		
		// DIBUJAR LA ENVOLVENTE CONVEXA
		drawConvexHull(g);
	}
	
	/**
	 * Este método describe un algoritmo que dibuja la envolvente convexa. 
	 * Va seleccionando en cada iteración los puntos adecuados entre los que debe trazar una línea.
	 * @param graphics Graphics sobre los que se dibuja el trazo.
	 */
	private void drawConvexHull(Graphics graphics) {
		ArrayList<PointHull> hull = new ArrayList<>(getConvexHull());
		boolean connectedRightMostPoint = false; 

		PointHull leftMostPoint = new PointHull(PointHull.getLeftMostPoint(hull));
		PointHull from = PointHull.getLeftMostPoint(hull);
		PointHull to = null;
		while (!hull.isEmpty()) {
			if (!connectedRightMostPoint) {
				List<PointHull> abovePoints = PointHull.getAbovePoints(from, hull);
				if (!abovePoints.isEmpty()) {
					to = PointHull.getLeftMostPoint(abovePoints);
				} else {
					List<PointHull> belowPoints = PointHull.getBelowPoints(from, hull);
					if (!belowPoints.isEmpty()) {
						List<PointHull> rightPoints = PointHull.getPointsOnTheRight(from, belowPoints);
						if (!rightPoints.isEmpty()) {
							to = from.getClosestPointInY(rightPoints);
						}
					}
				}
			} else {
				List<PointHull> belowPoints = PointHull.getBelowPoints(from, hull);
				if (!belowPoints.isEmpty()) {
					to = PointHull.getRightMostPoint(belowPoints);
				} else {
					List<PointHull> abovePoints = PointHull.getAbovePoints(from, hull);
					if (!abovePoints.isEmpty()) {
						to = from.getClosestPointInY(abovePoints);
					}
				}
			}
			if (to == PointHull.getRightMostPoint(getConvexHull()))
				connectedRightMostPoint = true;
			graphics.drawLine(from.x, from.y, to.x, to.y);
			hull.remove(from);
			from = to;
		}
		graphics.drawLine(from.x, from.y, leftMostPoint.x, leftMostPoint.y);
	}
}

















//END