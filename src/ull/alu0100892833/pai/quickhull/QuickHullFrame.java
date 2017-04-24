package ull.alu0100892833.pai.quickhull;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import ull.alu0100892833.pai.quickhull.view.HullView;

public class QuickHullFrame extends JFrame {
	private static final long serialVersionUID = -8966296162428709933L;
	private static final int WIDTH_PROPORTION = 2;
	private static final int HEIGHT_PROPORTION = 2;
	
	private HullView view;
	
	public QuickHullFrame(int nPoints, int timer) {
		setTitle("QUICKHULL");
		
		Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension((int) fullScreen.getWidth() / WIDTH_PROPORTION, (int) fullScreen.getHeight() / HEIGHT_PROPORTION));
        QuickHull quickHull = new QuickHull();
		view = new HullView(getSize(), nPoints, quickHull, timer); 
		view.setName("mainPanel");
		add(view);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public HullView getView() {
		return view;
	}

	public void setView(HullView view) {
		this.view = view;
	}

	
}
