package ull.alu0100892833.pai.quickhull.test;

import org.junit.Test;

import ull.alu0100892833.pai.quickhull.QuickHullFrame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;


public class HullViewTest extends AssertJSwingJUnitTestCase {
	private final int N_POINTS = 50;
	private final int TIME_INTERVAL = 500;
	
	private FrameFixture frame;
	private QuickHullFrame program;

	@Override
	protected void onSetUp() {
		program = GuiActionRunner.execute(() -> new QuickHullFrame(N_POINTS, TIME_INTERVAL));
		frame = new FrameFixture(robot(), program);
		frame.show();
	}
	
	@Test 
	public void testTimerChanges() {
		frame.panel("mainPanel").button("INIT").click();
		assertTrue(program.getView().getTimer().isRunning());
		frame.panel("mainPanel").button("PAUSE").click();
		assertFalse(program.getView().getTimer().isRunning());
		frame.panel("mainPanel").button("PAUSE").click();
		assertTrue(program.getView().getTimer().isRunning());
		frame.panel("mainPanel").button("RESET").click();
		assertTrue(program.getView().getCurrentStep() == 1);
		assertFalse(program.getView().getTimer().isRunning());
	}
	
	@Test
	public void testExecuteAndReset() {
		frame.panel("mainPanel").button("EXECUTE").click();
		assertTrue(program.getView().getQuickHull().getConvexHull() == 
				program.getView().getQuickHull().getSteps().get(program.getView().getQuickHull().getnSteps() - 1));
		assertTrue(program.getView().getCurrentStep() == 1);
		assertFalse(program.getView().getTimer().isRunning());
	}

	@Test
	public void testStepByStep() {
		for (int i = 1; i <= program.getView().getQuickHull().getnSteps(); i++) {
			assertThat(program.getView().getCurrentStep() == i);
			frame.panel("mainPanel").button("STEP").click();
		}
		frame.panel("mainPanel").button("RESET").click();
		assertTrue(program.getView().getCurrentStep() == 1);
	}
	
	@Test 
	public void testProgressiveShowing() throws InterruptedException {
		for (int i = 1; i <= program.getView().getQuickHull().getnSteps(); i++) {
			assertThat(program.getView().getCurrentStep() == i);
			TimeUnit.MILLISECONDS.sleep(TIME_INTERVAL);
		}
	}
}












//	end