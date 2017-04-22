package ull.alu0100892833.pai.quickhull.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import ull.alu0100892833.pai.quickhull.QuickHull;

public class PointsPanel extends JPanel {
	private static final long serialVersionUID = -6239480177082564469L;
	private static final int GAP = 30;
	
	private QuickHull quickHull;
	
	public PointsPanel(Dimension size, int nPoints, QuickHull hull) {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setSize(size);
		quickHull = hull;
		hull.setRandomPoints(nPoints, getWidth() - getWidth() / 4, getHeight() - GAP);
	}

	public QuickHull getQuickHull() {
		return quickHull;
	}

	public void setQuickHull(QuickHull quickHull) {
		this.quickHull = quickHull;
	}
	
	public void setnPoints(int nPoints) {
		getQuickHull().setRandomPoints(nPoints, getWidth(), getHeight());
	}

	/**
	 * Este procedimiento no dibuja nada en el panel, salvo que el modelo del espacio de puntos esté inicializado. En este caso llama a su procedimiento paintHull().
	 */
	protected void paintComponent(Graphics g) {
		if (quickHull != null)
			quickHull.paintHull(g, getWidth(), getHeight());
	}

}