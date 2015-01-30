package turtle;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

import turtle.util.Direction;
import turtle.util.Pen;
import turtle.util.Position;
import turtle.util.Rotation;

public class TurtleInterpreter {

    private static final int DEFAULT_PAPER_SIZE = 10;

    private Scanner scanner;
    private HashMap<String, Turtle> turtles;
    private Paper paper;
    private PrintStream out;

    public TurtleInterpreter(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
        turtles = new HashMap<String, Turtle>();
        paper = new Paper(DEFAULT_PAPER_SIZE, DEFAULT_PAPER_SIZE);
    }

    /**
     * Processes all the commands from the input source
     * 
     */
    public void process() {
        boolean exit = false;

        // print ready symbol
        System.out.print(">>> ");

        while (scanner.hasNext()) {
            // exit?
            if (exit) {
                return;
            }

            // read token
            String tk = scanner.next();

            // call correct method using switch
            switch (tk) {
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
            case "rotate":
                processRotate();
                break;
            case "show":
                processShow();
                break;
            case "exit": // exits interpreter
                System.out.println("Exiting interpreter");
                exit = true;
                return;
            default: // invalid command
                System.out.println("SKIPPING invalid command: " + tk);
                break;
            }

            // print ready symbol
            System.out.print(">>> ");
        }

        System.out.println();

    }

    private void processPaper() {
        // scan width
        int width = scanInt("paper", "width");
        // error if distance is negative
        if (width < 0) {
            System.out.println("Error in PAPER, parameter WIDTH "
                    + "cannot be negative.");
            width = scanInt("paper", "width");
        }
        // scan height
        int height = scanInt("paper", "height");
        // error if distance is negative
        if (height < 0) {
            System.out.println("Error in PAPER, parameter HEIGHT "
                    + "cannot be negative.");
            height = scanInt("paper", "height");
        }

        if (turtle.Main.DEBUG) {
            System.out.println("process Paper: width=" + width + " height="
                    + height);
        }

        // create the paper
        paper = new Paper(width, height);

        // remove all turtles
        turtles = new HashMap<String, Turtle>();
    }

    private void processNew() {
        // scan type
        String type = scanString("new", "type");

        // scan name
        String name = scanString("new", "name");

        // scan x
        double x = scanDouble("new", "x");

        // scan y
        double y = scanDouble("new", "y");

        if (turtle.Main.DEBUG) {
            System.out.println("process New: type=" + type + " name=" + name
                    + " x=" + x + " y=" + y);
        }

        // print error when turtle name already exists
        if (turtles.containsKey(name)) {
            System.out.println("Turtle " + name.toUpperCase()
                    + " already exists. Overwriting " + "existing one.");
        }

        // create the turtle at position x y
        // with pen UP
        switch (type) {
        case "normal":
            turtles.put(name, new TurtleNormal(new Position(x, y),
                    new Direction(0), Pen.UP, paper));
            break;
        default:
            System.out.println("Error in NEW, invalid TYPE " + type);
            return;
        }

    }

    private void processPen() {
        // scan name
        String name = scanString("pen", "name");

        // scan state
        String stateString = scanString("pen", "state");
        Pen state;

        // print error if turtle not found
        if (!turtles.containsKey(name)) {
            printErrorTurtleNotFound(name);
            return;
        }

        if (turtle.Main.DEBUG) {
            System.out.println("process Pen: name=" + name + " state="
                    + stateString);
        }

        if (stateString.length() == 1) {
            // changes brush
            turtles.get(name).changeBrush(stateString.charAt(0));
        } else if (stateString.equals("up")) {
            turtles.get(name).liftPen();
        } else if (stateString.equals("down")) {
            turtles.get(name).putPen();
        } else {
            System.out.println("PEN command, invalid STATE: " + stateString);
            return;
        }
    }

    private void processMove() {
        // scan name
        String name = scanString("move", "name");

        // scan distance
        int distance = scanInt("move", "distance");
        // error if distance is negative
        if (distance < 0) {
            System.out.println("Error in MOVE, parameter DISTANCE "
                    + "cannot be negative.");
            distance = scanInt("move", "distance");
        }

        // print error if turtle not found
        if (!turtles.containsKey(name)) {
            printErrorTurtleNotFound(name);
            return;
        }

        if (turtle.Main.DEBUG) {
            System.out.println("process Move before: "
                    + turtles.get(name).getPosition());

            System.out.println("process Move: name=" + name + " distance="
                    + distance);
        }

        // perform move
        turtles.get(name).move(distance);

        if (turtle.Main.DEBUG) {
            System.out.println("process Move after: "
                    + turtles.get(name).getPosition());
        }
    }

    private void processRotate() {
        // scan name
        String name = scanString("rotate", "name");

        // scan angle
        double angle = scanDouble("rotate", "angle");

        // print error if turtle not found
        if (!turtles.containsKey(name)) {
            printErrorTurtleNotFound(name);
            return;
        }

        // perform rotation
        turtles.get(name).rotate(angle);
    }

    private void processShow() {
        out.println(paper);
    }

    // scans a string and prints debug if necessary
    private String scanString(String command, String par) {
        if (scanner.hasNext()) {
            return scanner.next();
        } else {
            System.out.println("Error in " + command.toUpperCase()
                    + ", needs parameter " + par.toUpperCase());
            return null;
        }
    }

    // scans an int and prints debug if necessary
    private int scanInt(String command, String par) {
        if (scanner.hasNext()) {
            try {
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException x) {
                System.out.println("Error in " + command.toUpperCase()
                        + ", parameter " + par.toUpperCase()
                        + " must be a number.");
                return scanInt(command, par);
            }
        } else {
            System.out.println("Error in " + command.toUpperCase()
                    + ", needs parameter " + par.toUpperCase());
            return -1;
        }
    }

    // scans a double and prints debug if necessary
    private double scanDouble(String command, String par) {
        if (scanner.hasNext()) {
            try {
                return Double.parseDouble(scanner.next());
            } catch (NumberFormatException x) {
                System.out.println("Error in " + command.toUpperCase()
                        + ", parameter " + par.toUpperCase()
                        + " must be a number.");
                return scanDouble(command, par);
            }
        } else {
            System.out.println("Error in " + command.toUpperCase()
                    + ", needs parameter " + par.toUpperCase());
            return -1;
        }
    }

    // prints an error message when trying to refer to
    // an unexistent turtle name
    private void printErrorTurtleNotFound(String name) {
        System.out.println("Turtle " + name.toUpperCase() + " not found.");
    }

}
