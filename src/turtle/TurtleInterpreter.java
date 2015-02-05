package turtle;

import gui.MainWindow;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

import turtle.util.Direction;
import turtle.util.Pen;
import turtle.util.Position;
import turtle.util.Rotation;

public class TurtleInterpreter
{

    private static final int DEFAULT_PAPER_SIZE = 10;

    private Scanner scanner;
    private HashMap<String, Turtle> turtles;
    private Paper paper;
    private PrintStream out;
    private MainWindow gui;

    public TurtleInterpreter(Scanner scanner, PrintStream out, MainWindow gui)
    {
        this.scanner = scanner;
        this.out = out;
        turtles = new HashMap<String, Turtle>();
        paper = new Paper(DEFAULT_PAPER_SIZE, DEFAULT_PAPER_SIZE);
        this.gui = gui;
    }

    /**
     * Processes all the commands from the input source
     * 
     */
    public void process()
    {
        // print ready symbol
        gui.setTxtCommandsText(">>> ");
        // System.out.print(">>> ");

        while (scanner.hasNext())
        {
            // read token
            String tk = scanner.next();

            // call correct method using switch
            switch (tk)
            {
                case ">>>":
                    break;
                case "help":
                    System.out.println(Help.getHelp());
                    break;
                case "paper":
                    processPaper();
                    break;
                case "new":
                    processNew();
                    break;
                case "pen":
                    processPen();
                    break;
                case "move":
                    processMove();
                    break;
                case "right":
                    processRight();
                    break;
                case "left":
                    processLeft();
                    break;
                case "show":
                    processShow();
                    break;
                case "status":
                    processStatus();
                    break;
                case "exit": // exits interpreter
                    System.out.println("Exiting interpreter");
                    gui.closeAndExit();
                    return;
                default: // invalid command
                    System.out.println("SKIPPING invalid command: " + tk);
                    break;
            }

            // print ready symbol
            gui.setTxtCommandsText(">>> ");
        }

        System.out.println();

    }

    /**
     * Processes the paper command
     */
    private void processPaper()
    {
        int width = 0;

        // scan width
        try
        {
            width = scanInt("paper", "width");
        }
        catch (NumberFormatException x)
        {
            System.out.println("Invalid number format. Skipping command");
            return;
        }
        // error if distance is negative
        if (width < 0)
        {
            System.out.println("Error in PAPER, parameter WIDTH "
                + "cannot be negative.\nSkipping..");
            return;
        }

        int height = 0;

        // scan height
        try
        {
            height = scanInt("paper", "height");
        }
        catch (NumberFormatException x)
        {
            System.out.println("Invalid number format. Skipping command");
            return;
        }
        // error if distance is negative
        if (height < 0)
        {
            System.out.println("Error in PAPER, parameter HEIGHT "
                + "cannot be negative.\nSkipping..");
        }

        // create the paper
        paper = new Paper(width, height);

        // remove all turtles
        turtles = new HashMap<String, Turtle>();
        
        gui.printDone();
    }

    /**
     * Processes the new command
     */
    private void processNew()
    {
        // scan type
        String type = scanString("new", "type");

        // scan name
        String name = scanString("new", "name");

        // print error when turtle name already exists
        if (turtles.containsKey(name))
        {
            System.out.println("Turtle " + name.toUpperCase()
                + " already exists. Overwriting " + "existing one.");
        }

        // create the turtle at position x y
        // with pen UP
        turtles.put(name, createNewTurtle(type, name, ""));

    }

    /**
     * Creates a new turtle
     * 
     * @param type
     *            the type of the turtle
     * @param name
     *            the name of the turtle
     * @param superName
     *            the name of the parent turtle
     * @return the new created turtle
     */
    private Turtle createNewTurtle(String type, String name, String superName)
    {

        int x = 0;
        // scan x or cluster size
        try
        {
            x = scanInt("new", "x");
        }
        catch (NumberFormatException m)
        {
            System.out.println("Invalid number format. Skipping command");
            return createNewTurtle(type,name,superName);
            //x = scanInt("new", "x");
        }

        int y = 0;
        if (!type.equals("cluster"))
        {
            // scan y or cluster size
            try
            {
                y = scanInt("new", "y");
            }
            catch (NumberFormatException m)
            {
                System.out.println("Invalid number format. Skipping command");
                return createNewTurtle(type,name,superName);
                //y = scanInt("new", "y");
            }
        }
        
        // if there is a parent cluster,
        // parentname. is added in front of the cluster name
        String fullName = name;
        if (superName.length() > 0)
        {
            fullName = superName + "." + name;
        }

        switch (type)
        {
            case "cluster":
                gui.printDone(); return createTurtleCluster(x, name, superName);
            case "normal":
                gui.printDone(); return new TurtleNormal(new Position(x, y),
                    Direction.NORTH, Pen.UP, paper, fullName);
            case "continuous":
                gui.printDone(); return new TurtleContinuous(new Position(x, y),
                    Direction.NORTH, Pen.UP, paper, fullName);
            case "bouncy":
                gui.printDone(); return new TurtleBouncy(new Position(x, y),
                    Direction.NORTH, Pen.UP, paper, fullName);
            case "reflecting":
                gui.printDone(); return new TurtleReflecting(new Position(x, y),
                    Direction.NORTH, Pen.UP, paper, fullName);
            case "wrapping":
                gui.printDone(); return new TurtleWrapping(new Position(x, y),
                    Direction.NORTH, Pen.UP, paper, fullName);
            default:
                System.out.println("Error in NEW, invalid TYPE " + type);
                return null;
        }
    }

    /**
     * Creates a cluster turtle
     * 
     * @param n
     *            the size of the cluster
     * @param name
     *            the name of the cluster
     * @param superName
     *            the name of the parent cluster (if any)
     * @return the new created cluster
     */
    private TurtleCluster createTurtleCluster(int n, String name,
        String superName)
    {

        // if there is a parent cluster,
        // parentname. is added in front of the cluster name
        String fullName = name;
        if (superName.length() > 0)
        {
            fullName = superName + "." + name;
        }

        TurtleCluster out = new TurtleCluster(turtles, fullName);

        for (int i = 0; i < n; i++)
        {
            gui.setTxtCommandsText(">>> (" + (i+1) + ") >> ");
            //System.out.print(">>> (" + (i+1) + ") >> ");
            
            // scan command
            String command;
            do
            {
                command = scanString("newCluster", "command");
                if (command.equals("exit"))
                {
                    return null;
                }
                
            } while (!command.equals("new"));

            // scan type
            String type = scanString("newCluster", "type");

            // scan name
            String tname = scanString("newCluster", "name");

            out.put(tname, createNewTurtle(type, tname, fullName));
        }

        return out;
    }

    /**
     * Processes the pen command
     */
    private void processPen()
    {
        // scan name
        String name = scanString("pen", "name");

        // scan state
        String stateString = scanString("pen", "state");

        // print error if turtle not found
        if (!turtles.containsKey(name))
        {
            printErrorTurtleNotFound(name);
            return;
        }

        if (stateString.length() == 1)
        {
            // changes brush
            turtles.get(name).changeBrush(stateString.charAt(0));
            gui.printDone();
        }
        else if (stateString.equals("up"))
        {
            turtles.get(name).liftPen();
            gui.printDone();
        }
        else if (stateString.equals("down"))
        {
            turtles.get(name).putPen();
            gui.printDone();
        }
        else
        {
            System.out.println("PEN command, invalid STATE: " + stateString);
            return;
        }
    }

    /**
     * Processes the move command
     */
    private void processMove()
    {
        // scan name
        String name = scanString("move", "name");

        int distance = 0;
        // scan distance
        try
        {
            distance = scanInt("move", "distance");
        }
        catch (NumberFormatException x)
        {
            System.out.println("Invalid number format. Skipping command");
            return;
        }

        // error if distance is negative
        if (distance < 0)
        {
            System.out.println("Error in MOVE, parameter DISTANCE "
                + "cannot be negative.\nSkipping command.");
            return;
        }

        // print error if turtle not found
        if (!turtles.containsKey(name))
        {
            printErrorTurtleNotFound(name);
            return;
        }

        // perform move
        turtles.get(name).move(distance);
        gui.printDone();
    }

    /**
     * Processes the rotate right command
     */
    private void processRight()
    {
        // scan name
        String name = scanString("right", "name");

        int angle = 0;
        // scan angle
        try
        {
            angle = scanInt("right", "angle");
        }
        catch (NumberFormatException x)
        {
            System.out.println("Invalid number format. Skipping command");
            return;
        }
        // is angle correct?
        if ((angle % 45) != 0)
        {
            System.out.println("Error in RIGHT, parameter ANGLE must be "
                + "a multiple of 45.");
            return;
        }

        // print error if turtle not found
        if (!turtles.containsKey(name))
        {
            printErrorTurtleNotFound(name);
            return;
        }

        // perform rotation
        turtles.get(name).rotate(Rotation.RIGHT, angle);
        gui.printDone();
    }

    /**
     * Processes the rotate left command
     */
    private void processLeft()
    {
        // scan name
        String name = scanString("left", "name");

        int angle = 0;
        // scan angle
        try
        {
            angle = scanInt("left", "angle");
        }
        catch (NumberFormatException x)
        {
            System.out.println("Invalid number format. Skipping command");
            return;
        }
        // is angle correct?
        if ((angle % 45) != 0)
        {
            System.out.println("Error in LEFT, parameter ANGLE must be "
                + "a multiple of 45.");
            return;
        }

        // print error if turtle not found
        if (!turtles.containsKey(name))
        {
            printErrorTurtleNotFound(name);
            return;
        }

        // perform rotation
        turtles.get(name).rotate(Rotation.LEFT, angle);
        gui.printDone();
    }

    /**
     * Prints the paper on selected printstream
     */
    private void processShow()
    {
        out.println(paper);
    }

    /**
     * Scans a string from input stream scanner. Prints an error message if
     * necessary
     * 
     * @param command
     *            Error message, which command called it
     * @param par
     *            Error message, which parameter is being read
     * @return the token
     */
    private String scanString(String command, String par)
    {
        if (scanner.hasNext())
        {
            return scanner.next();
        }
        else
        {
            System.out.println("Error in " + command.toUpperCase()
                + ", needs parameter " + par.toUpperCase());
            return null;
        }
    }

    /**
     * Scans the next int from input stream
     * 
     * @param command
     *            Error message, which command is being called
     * @param par
     *            Error message, which parameter is being read
     * @return the next token
     */
    private int scanInt(String command, String par)
        throws NumberFormatException
    {
        if (scanner.hasNext())
        {
            return Integer.parseInt(scanner.next());

        }
        else
        {
            System.out.println("Error in " + command.toUpperCase()
                + ", needs parameter " + par.toUpperCase());
            return -1;
        }
    }

    /**
     * Prints an error message for turtle not found problem
     * 
     * @param name
     */
    private void printErrorTurtleNotFound(String name)
    {
        System.out.println("Turtle " + name.toUpperCase() + " not found.");
    }
    
    private void processStatus()
    {
        out.println(paper.status());
        int n = 0;
        for (Turtle t : turtles.values())
        {
            out.println(t.status());
            n += 1;
        }
        out.println("There are " + n + " active turtles.");
    }

}
