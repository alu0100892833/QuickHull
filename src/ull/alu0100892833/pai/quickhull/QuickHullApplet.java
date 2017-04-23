package ull.alu0100892833.pai.quickhull;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JApplet;
import javax.swing.JFrame;


import ull.alu0100892833.pai.quickhull.view.HullView;

public class QuickHullApplet extends JApplet {
	private static final long serialVersionUID = 8575224608354210947L;
	private static final int WIDTH_PROPORTION = 2;
	private static final int HEIGHT_PROPORTION = 2;
	
	boolean isStandalone = false;
	int nPoints, timer;
	
	@Override
	public void init() {
		try {
			if (!isStandalone) {
				nPoints = Integer.parseInt(getParameter("NUMBER-OF-POINTS"));
				timer = Integer.parseInt(getParameter("TIMER"));
				QuickHull quickHull = new QuickHull();
				HullView view = new HullView(getSize(), nPoints, quickHull, timer);
				add(view);
			}
		} catch(Exception e) {
			System.err.println("Algo ha ido mal. Debe introducir correctamente los parámetros necesarios: ");
			System.err.println("\t - Señale primero el número de puntos que desea que se generen.");
			System.err.println("\t - Especifique también el temporizador para la evolución del algoritmo.");
			System.err.println();
			System.err.println("Ambos parámetros deben ser números enteros.");
			e.printStackTrace();
		}
	}
	
	public boolean getCommandLineParameters(String[] args) {
		try {
			if (args.length != 2)
				throw new Exception();
			nPoints = Integer.parseInt(args[0]);
			timer = Integer.parseInt(args[1]);
			return true;
		} catch (Exception e) {
			System.err.println("Algo ha ido mal. Debe introducir correctamente los parámetros necesarios: ");
			System.err.println("\t - Señale primero el número de puntos que desea que se generen.");
			System.err.println("\t - Especifique también el temporizador para la evolución del algoritmo.");
			System.err.println();
			System.err.println("Ambos parámetros deben ser números enteros.");
			e.printStackTrace();
			return false;
		}
	}

	
	public static void main(String[] args) {
		JFrame quickHullFrame = new JFrame("QUICK HULL");
		QuickHullApplet app = new QuickHullApplet();
		app.isStandalone = true;
		boolean correctParameters = app.getCommandLineParameters(args);
		
		if (correctParameters) {
			quickHullFrame.add(app, BorderLayout.CENTER);
			Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
	        quickHullFrame.setSize(new Dimension((int) fullScreen.getWidth() / WIDTH_PROPORTION, (int) fullScreen.getHeight() / HEIGHT_PROPORTION));
	        
	        QuickHull quickHull = new QuickHull();
			HullView view = new HullView(quickHullFrame.getSize(), app.nPoints, quickHull, app.timer); 
			quickHullFrame.add(view);
	        
	        quickHullFrame.setLocationRelativeTo(null); 
	        quickHullFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        quickHullFrame.setVisible(true);
		}
	}
}











//END