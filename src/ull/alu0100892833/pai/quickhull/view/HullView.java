package ull.alu0100892833.pai.quickhull.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.w3c.dom.events.EventException;

import ull.alu0100892833.pai.quickhull.QuickHull;
import ull.alu0100892833.pai.quickhull.exceptions.NoPointsException;

/**
 * La clase HullView extiende a la clase PointsPanel para añadir los botones de control y los listeners correspondientes.
 * Los botones de control los sitúa en la sección EAST del BorderLayout y recluye los puntos a CENTER (resto de la ventana).
 * @author oscardp96
 * @since 21-4-2017
 */
public class HullView extends PointsPanel {
	private static final long serialVersionUID = 7788499771694586039L;
	private static final int N_ELEMENTS = 4; 
    private static final int COLS = 1;
    private static final int GAP = 50;
    private static final int TOP_BOTTOM_MARGIN_PROPORTION = 7;
    
	private JButton init, execute, pause, step;
	private int timeInterval;

	/**
	 * Constructor por parámetros.
	 * @param size Tamaño del panel en un objeto Dimension.
	 * @param nPoints Número de puntos aleatorios a generar en el espacio de puntos.
	 * @param hull Modelo del espacio de puntos.
	 */
	public HullView(Dimension size, int nPoints, QuickHull hull, int timeInterval) {
		super(size, nPoints, hull);
		this.timeInterval = timeInterval;
		addControlsPanel();
	}
	
	/**
	 * Este método añade la estructura de botones para controlar la representación definida por la clase madre PointsPanel.
	 * Establece un borde y añade los botones en la sección EAST del BorderLayout.
	 */
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
    	addListenerToButtons(new ButtonsListener());
	}
	
	/**
	 * Esta clase añade a todos los botones de control el ActionListener especificado como parámetro.
	 * @param al ActionListener que define el comportamiento de los botones ante eventos.
	 */
	private void addListenerToButtons(ActionListener al) {
		getInit().addActionListener(al);
		getExecute().addActionListener(al);
		getPause().addActionListener(al);
		getStep().addActionListener(al);
	}
	
	/**
	 * Permite obtener una referencia al botón INIT.
	 * @return
	 */
	public JButton getInit() {
		return init;
	}

	/**
	 * Permite obtener una referencia al botón EXECUTE.
	 * @return
	 */
	public JButton getExecute() {
		return execute;
	}

	/**
	 * Permite obtener una referencia al botón PAUSE.
	 * @return
	 */
	public JButton getPause() {
		return pause;
	}

	/**
	 * Permite obtener una referencia al botón STEP.
	 * @return
	 */
	public JButton getStep() {
		return step;
	}
	
	public int getTimeInterval() {
		return timeInterval;
	}



	/**
	 * Clase que implementa la interfaz ActionListener. Define las acciones a realizar por los botones de la clase en la que se engloba. 
	 * Dependiendo de la fuente del evento, lleva a cabo una acción u otra.
	 * @author oscardp96
	 * @since 22-4-2017
	 */
	class ButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == getInit()) {
				try {
					getQuickHull().quickHull();
					revalidate();
					repaint();
				} catch (NoPointsException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				} 
			} else if (e.getSource() == getExecute()) {
				try {
					getQuickHull().quickHull();
					revalidate();
					repaint();
				} catch (NoPointsException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				} 
			} else if (e.getSource() == getPause()) {
				
			} else if (e.getSource() == getStep()) {
				
			} else
				throw new EventException((short) 0, "Unexpected Event");
		}
	}
}











//END