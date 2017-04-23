package ull.alu0100892833.pai.quickhull.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
	private static final int N_ELEMENTS = 5; 
    private static final int COLS = 1;
    private static final int GAP = 50;
    private static final int TOP_BOTTOM_MARGIN_PROPORTION = 7;
    private static final int INITIAL_STEP = 1;
    private static final int ZERO = 0;
    
	private JButton init, execute, pause, step, reset;
	private int timeInterval, currentStep;
	private Timer timer;

	/**
	 * Constructor por parámetros.
	 * @param size Tamaño del panel en un objeto Dimension.
	 * @param nPoints Número de puntos aleatorios a generar en el espacio de puntos.
	 * @param hull Modelo del espacio de puntos.
	 */
	public HullView(Dimension size, int nPoints, QuickHull hull, int timeInterval) {
		super(size, nPoints, hull);
		this.timeInterval = timeInterval;
		this.currentStep = INITIAL_STEP;
		addControlsPanel();
		try {
			getQuickHull().quickHull();
		} catch (NoPointsException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
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
    	this.reset = new JButton("RESET");
    	buttonsPanel.add(init);
    	buttonsPanel.add(execute);
    	buttonsPanel.add(pause);
    	buttonsPanel.add(step);
    	buttonsPanel.add(reset);
    	
    	add(buttonsPanel, BorderLayout.EAST);
    	ButtonsListener listener = new ButtonsListener();
    	addListenerToButtons(listener);
    	timer = new Timer(timeInterval, listener);
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
		getReset().addActionListener(al);
	}

	/**
	 * Obtener el paso actual de la solución.
	 * @return
	 */
	public int getCurrentStep() {
		return currentStep;
	}

	/**
	 * Modifica el paso actual de la solución.
	 * @param currentStep
	 */
	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

	/**
	 * Getter del timer.
	 * @return
	 */
	public Timer getTimer() {
		return timer;
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
	 * Permite obtener una referencia al botón RESET.
	 * @return
	 */
	public JButton getReset() {
		return reset;
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
	
	/**
	 * Obtener el intervalo de tiempo entre cada paso de la solución.
	 * @return
	 */
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
				getTimer().start();
			} else if (e.getSource() == getExecute()) {
				getTimer().stop();
				getQuickHull().setToFinalSolution();
				revalidate();
				repaint();
			} else if (e.getSource() == getPause()) {
				getTimer().stop();
			} else if (e.getSource() == getStep()) {
				getTimer().stop();
				showProcess();
			} else if (e.getSource() == getReset()) {
				resetView();
			} else if (e.getSource() == getTimer()) {
				showProcess();
			} else
				throw new EventException((short) ZERO, "Unexpected Event");
		}
	}
	
	/**
	 * Método que resetea la vista, eliminando la envolvente convexa y reiniciando el programa.
	 */
	private void resetView() {
		getQuickHull().reset();
		try {
			setCurrentStep(INITIAL_STEP);
			getQuickHull().quickHull();
			revalidate();
			repaint();
		} catch (NoPointsException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	/**
	 * Este método toma la solución correspondiente al paso actual del objeto quickHull y recarga la ventana para mostrar un paso de la solución.
	 */
	private void showProcess() {
		getQuickHull().changeActiveSolution(getCurrentStep());
		revalidate();
		repaint();
		setCurrentStep(getCurrentStep() + 1);
	}
}











//END