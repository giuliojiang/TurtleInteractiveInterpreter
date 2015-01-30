package turtle.util;

public enum Pen {

	UP,
	DOWN
	;


	/**
	 * Converts from string
	 * to Pen
	 * 
	 * @param s Input String
	 * @return the corresponding Pen status
	 */
	public static Pen fromString(String s)
	{
		switch (s.toLowerCase())
		{
		case "up":
			return Pen.UP;
		case "down":
			return Pen.DOWN;
		default:
			System.out.println(
					"Pen.fromString invalid input string: " +
							s
					);
			return Pen.UP;
		}
	}
}
