package ull.alu0100892833.pai.quickhull.exceptions;

/**
 * Esta clase representa a las excepciones que deben lanzarse cuando hay algún problema con los puntos en el procedimiento QuickHull.
 * @author oscardp96
 * @since 21-4-2017
 */
public class NoPointsException extends Exception {
	private static final long serialVersionUID = -7555208345606620616L;

	public NoPointsException() {
		super();
	}
	
	public NoPointsException(String descriptor) {
		super(descriptor);
	}
	
	public void printStackTrace() {
		super.printStackTrace();
	}

}
