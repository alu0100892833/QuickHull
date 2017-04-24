package ull.alu0100892833.pai.quickhull.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import ull.alu0100892833.pai.quickhull.QuickHull;

/**
 * La clase PointsPanel permite instanciar paneles preparados para mostrar en pantalla una representación de la información almacenada en los objetos QuickHull.
 * No es un panel funcional, simplemente representa el modelo sobre un fondo blanco. La interacción debe añadirse por separado.
 * @author oscardp96
 * @since 21-4-2017
 */
public class PointsPanel extends JPanel {
	private static final long serialVersionUID = -6239480177082564469L;
	
	private QuickHull quickHull;
	
	/**
	 * Constructor por parámetros.
	 * @param size Tamaño del panel en un objeto Dimension.
	 * @param nPoints Número de puntos aleatorios a generar en el espacio de puntos.
	 * @param hull Modelo del espacio de puntos.
	 */
	public PointsPanel(Dimension size, int nPoints, QuickHull hull) {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setSize(size);
		quickHull = hull;
		hull.setRandomPoints(nPoints, getWidth(), getHeight() - getHeight() / 4);
	}

	public QuickHull getQuickHull() {
		return quickHull;
	}

	public void setQuickHull(QuickHull quickHull) {
		this.quickHull = quickHull;
	}

	/**
	 * Este procedimiento no dibuja nada en el panel, salvo que el modelo del espacio de puntos esté inicializado. En este caso llama a su procedimiento paintHull().
	 */
	protected void paintComponent(Graphics g) {
		if (quickHull != null)
			quickHull.paintHull(g);
	}

}
