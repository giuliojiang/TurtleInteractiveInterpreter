package turtle;

public class Help
{

    public static void welcome()
    {
        String s="\n"+
            "Welcome to the Turtle Interpreter\n"+
            "written by Giulio Jiang\n"+
            "2015\n"+
            "\n"+
            "Type\n"+
            "help\n"+
            "to display instructions.\n"+
            "";
        System.out.println(s);
    }
    
    public static String getHelp()
    {
        return 
            "Usage:\nturtle.jar <input> <output>\ninput and output are optional. If unspecified,\ncommands will be read from and written to stdIO.\n\nCommands:\nhelp                            Print help\npaper <width> <height>          Creates new empty paper, removes all turtles\nnew <type> <name> <x> <y>       Creates new turtle at (x,y) location\nnew cluster <name> <n>          Create a cluster with n turtles\npen <name> <up|down|c>          Lifts or puts down pen, or sets brush to c char\nmove <name> <distance>          Moves turtle in current direction\nright <name> <angle>            Rotates turtle right\nleft <name> <angle>             Rotates turtle left\nshow                            Prints paper to selected printStream\nstatus                          Displays status of paper and all turtles\nexit                            Exit interpreter\n\nSupported turtle types:\nnormal          stops moving if edge reached\ncontinuous      keeps moving if out of paper\nbouncy          bounces back if edge reached\nreflecting      reflects back if edge reached\nwrapping        wraps around if edge reached\n\nIt is possible to address turtles in a cluster using the standard\ndot notation.\nExample:\n  move clu.a 10\nmoves the turtle \'a\' in cluster \'clu\' by 10 units.\n  move clu 10\nwill move all turtles in cluster \'clu\' by 10 units.\nNested turtles are supported.";
    }
    
}

/*

Welcome to the Turtle Interpreter
written by Giulio Jiang
2015

Type
help
to display instructions.

Usage:
turtle.jar <input> <output>
input and output are optional. If unspecified,
commands will be read from and written to stdIO.

Commands:
help                            Print help
paper <width> <height>          Creates new empty paper, removes all turtles
new <type> <name> <x> <y>       Creates new turtle at (x,y) location
new cluster <name> <n>          Create a cluster with n turtles
pen <name> <up|down|c>          Lifts or puts down pen, or sets brush to c char
move <name> <distance>          Moves turtle in current direction
right <name> <angle>            Rotates turtle right
left <name> <angle>             Rotates turtle left
show                            Prints paper to selected printStream
status                          Displays status of paper and all turtles
exit                            Exit interpreter

Supported turtle types:
normal          stops moving if edge reached
continuous      keeps moving if out of paper
bouncy          bounces back if edge reached
reflecting      reflects back if edge reached
wrapping        wraps around if edge reached

It is possible to address turtles in a cluster using the standard
dot notation.
Example:
  move clu.a 10
moves the turtle 'a' in cluster 'clu' by 10 units.
  move clu 10
will move all turtles in cluster 'clu' by 10 units.
Nested turtles are supported.

*/