package turtle.util;

public class Direction {

	private double d; // angle in degrees
	
	public Direction(double d)
	{
		this.d = d;
		normalize();
	}
	
	private void normalize()
	{
		if (d < 0)
		{
			d += 360;
			normalize();
		}
		if (d > 360)
		{
			d -= 360;
			normalize();
		}
	}
	
	/**
	 * Rotates CLOCKWISE by given angle
	 * 
	 * @param angle to be added, clockwise
	 */
	public void rotate(double angle)
	{
		d -= angle;
		normalize();
	}
	
	public Position getDeltaPosition()
	{
		return new Position(
				Math.cos(rad(d)),
				Math.sin(rad(d))
				);
	}
	
	private static double rad(double x)
	{
		return x * 0.0174532925;
	}
}
