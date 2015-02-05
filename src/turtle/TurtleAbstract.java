package turtle;

import turtle.util.Position;
import turtle.util.Direction;
import turtle.util.Pen;
import turtle.util.Rotation;

public abstract class TurtleAbstract implements Turtle
{

    protected static final int ROTATION_STEP = 45;
    protected static final char DEFAULT_CHAR = '*';

    protected Position position;
    protected Direction direction;
    protected Pen penState;
    protected Paper paper;
    protected char brush;
    protected String name;

    public Position getPosition()
    {
        return position;
    }

    public TurtleAbstract(Position position, Direction direction, Pen penState,
        Paper paper, String name)
    {
        this.direction = direction;
        this.penState = penState;
        this.paper = paper;
        this.brush = DEFAULT_CHAR;
        this.name = name;

        if (this.paper.isWithinBounds(position))
        {
            this.position = position;
        }
        else
        {
            System.out.println("Warning: Initial position for turtle "
                + "out of bounds. Setting position to (0,0).");
            this.position = new Position(0, 0);
        }
    }

    public void liftPen()
    {
        penState = Pen.UP;
    }

    public void putPen()
    {
        penState = Pen.DOWN;
    }

    public void rotate(Rotation r, int degrees)
    {
        int steps = Math.abs(degrees / ROTATION_STEP);
        for (int i = 0; i < steps; i++)
        {
            direction = direction.rotate(r);
        }
    }

    public void changeBrush(char c)
    {
        this.brush = c;
    }

    public void writeMark()
    {
        if (!paper.isWithinBounds(position))
        {
            return;
        }
        if (penState == Pen.DOWN)
        {
            paper.writeChar(position.getX(), position.getY(), brush);
        }
    }

    public String status()
    {
        return "[name=" + name + ", position=" + position
            + ", direction=" + direction + ", penState=" + penState
            + ", brush=" + brush + "]";
    }
    
    public String getName()
    {
        return name;
    }

    public void move(int steps)
    {
        while (steps > 0)
        {
            Position deltaPosition = direction.getDeltaPosition();
            Position nextPosition = position.plus(deltaPosition);
            position = nextPosition;
            if (paper.isWithinBounds(position))
            {
                writeMark();
            }
            steps -= 1;
        }
    }

}
