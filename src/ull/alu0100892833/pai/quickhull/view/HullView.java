package ull.alu0100892833.pai.quickhull.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import ull.alu0100892833.pai.quickhull.QuickHull;

public class HullView extends PointsPanel {
	private static final long serialVersionUID = 7788499771694586039L;
	private static final int N_ELEMENTS = 4; 
    private static final int COLS = 1;
    private static final int GAP = 50;
    private static final int TOP_BOTTOM_MARGIN_PROPORTION = 7;
    
	private JButton init, execute, pause, step;

	public HullView(Dimension size, int nPoints, QuickHull hull) {
		super(size, nPoints, hull);
		addControlsPanel();
		System.out.println(getHeight());
	}
	
	private void addControlsPanel() {
		JPanel buttonsPanel = new JPanel(new GridLayout(N_ELEMENTS, COLS, GAP, GAP));
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(getHeight() / TOP_BOTTOM_MARGIN_PROPORTION, GAP, getHeight() / TOP_BOTTOM_MARGIN_PROPORTION, GAP));
		this.init = new JButton("INIT");
    	this.execute = new JButton("EXECUTE");
    	this.pause = new JButton("PAUSE");
    	this.step = new JButton("STEP BY STEP");
    	buttonsPanel.add(init);
    	buttonsPanel.add(execute);
    	buttonsPanel.add(pause);
    	buttonsPanel.add(step);
    	
    	add(buttonsPanel, BorderLayout.EAST);
	}
	
	public JButton getInit() {
		return init;
	}

	public JButton getExecute() {
		return execute;
	}

	public JButton getPause() {
		return pause;
	}

	public JButton getStep() {
		return step;
	}

}
