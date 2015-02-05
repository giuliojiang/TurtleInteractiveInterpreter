# TurtleInteractiveInterpreter

```
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

```

