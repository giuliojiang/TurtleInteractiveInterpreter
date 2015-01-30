package turtle.util;

public class Position {

	private final double x;
	private final double y;


	/**
	 * Construct a Position from x and y
	 * 
	 * @param x
	 * @param y
	 */
	public Position(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	


	public double getX() {
		return x;
	}




	public double getY() {
		return y;
	}




	/**
	 * Adds a Position p to this Position
	 * In a vectorial way
	 * 
	 * @param p The Position to be added
	 * @return a new Position which is the sum
	 */
	public Position plus(Position p)
	{
		return new Position(this.x + p.getX(), this.y + p.getY());
	}

	public String toString()
	{
		return "(" + x + "," + y + ")";
	}

}
