package ull.alu0100892833.pai.quickhull.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import ull.alu0100892833.pai.quickhull.QuickHull;

public class QuickHullTest {
	private final int N_POINTS = 20;
	private final int LIMIT = 200;
	
	private QuickHull hull;

	@Before
	public void setUp() throws Exception {
		hull = new QuickHull(N_POINTS, LIMIT, LIMIT);
	}

	@Test
	public void testCorrectInitialization() {
		assertThat(hull.getPoints().size() == N_POINTS);
		assertThat(hull.getnSteps() > 0);
	}
	
	@Test
	public void testReset() {
		hull.reset();
		assertThat(hull.getConvexHull().size() == 0);
		assertThat(hull.getSteps().size() == 0);
	}

}
