package turtle;

import gui.MainWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main
{

    // public static final boolean DEBUG = false;

    /**
     * Main class. If 1 argument is passed, that's the input If 0 arguments are
     * passed, input and output are read and written from stdIO.
     * 
     * @param args
     *            <input> <output>
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException
    {


        try
        {
            // Set System L&F
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e)
        {
            // handle exception
        }
        catch (ClassNotFoundException e)
        {
            // handle exception
        }
        catch (InstantiationException e)
        {
            // handle exception
        }
        catch (IllegalAccessException e)
        {
            // handle exception
        }

        Scanner scanner = null;
        PrintStream out = null;

        MainWindow gui;
        gui = MainWindow.main();
        gui.setWindowTitle("Turtle Interactive");
        
        if (args.length == 0)
        {
        	gui.catchPrintStream();
            scanner = new Scanner(System.in);
            out = System.out;
        } else if (args.length == 1)
        {
            gui.catchPrintStream();
            scanner = new Scanner(new File(args[0]));
            out = System.out;
        } else if (args.length == 2)
        {
        	gui.setVisible(false);
            scanner = new Scanner(new File(args[0]));
            out = new PrintStream((args[1]));
        } else
        {
            System.out.println("Usage: turtle.Main <input> <output>");
        }

        // create Interpreter
        TurtleInterpreter interpreter = new TurtleInterpreter(scanner, out, gui);

        // print welcome message
        Help.welcome();
        
        // process commands
        interpreter.process();
        
        // close
        if (args.length == 2)
        {
        	gui.closeAndExit();
        }

    }
}
