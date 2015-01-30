package turtle;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.File;

public class Main {

	public static final boolean DEBUG = false;

	/**
	 * Main class.
	 * If 1 argument is passed, that's the input
	 * If 0 arguments are passed, input and output
	 * are read and written from stdIO.
	 * 
	 * @param args <input> <output>
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) {

		printWelcome();

		Scanner scanner = null;
		PrintStream out = null;

		if (args.length == 0)
		{
			scanner = new Scanner(System.in);
			out = System.out;
		}
		else if (args.length == 1)
		{
			try
			{
				scanner = new Scanner(new File(args[0]));
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
				return;
			}
			out = System.out;
		}
		else if (args.length == 2)
		{
			try
			{
				scanner = new Scanner(new File(args[0]));
				out = new PrintStream((args[1]));
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
				return;
			}
		}
		else
		{
			System.out.println("Usage: turtle.Main <input> <output>");
		}

		// create Interpreter
		TurtleInterpreter interpreter = new TurtleInterpreter(scanner, out);

		// process commands
		interpreter.process();

	}

	private static void printWelcome()
	{
		System.out.println(
				"Welcome to the Simple Text Turtle Interpreter!\n" +
						"Created by Giulio Jiang.\t\t2015\tApache License 2.0\n\n" +
						"Optional parameters: <input commands> <output file>\n\n" +
						"Commands:\n" +
						"paper <width> <height>\t\tCreates new clean paper, removes all turtles.\n" +
						"new <type> <name> <x> <y>\tAdds turtle of type 'type', 'name' and at position 'x' 'y'. Currently type can only be normal.\n" +
						"pen <name> <down|up>\t\tLifts or Puts down the pen of turtle 'name'.\n" +
						"move <name> <distance>\t\tMoves turtle name by 'distance' steps.\n" +
						"rotate <name> <angle>\t\tRotates turtle 'name' by 'angle' clockwise.\n" +
						"show\t\t\t\tDisplays or appends to file the paper.\n" +
						"exit\t\t\t\tExits interpreter, or ends commands sequence\n\n\n"
						);
	}
}
