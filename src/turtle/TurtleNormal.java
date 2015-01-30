package turtle;

import turtle.util.Position;
import turtle.util.Direction;
import turtle.util.Pen;
import turtle.util.Rotation;

public class TurtleNormal implements Turtle {

    private static final int ROTATION_STEP = 45;
    private static final char DEFAULT_CHAR = '*';

    private Position position;
    private Direction direction;
    private Pen penState;
    private Paper paper;
    private char brush;

    public Position getPosition() {
        return position;
    }

    public TurtleNormal(Position position, Direction direction, Pen penState,
            Paper paper) {
        this.direction = direction;
        this.penState = penState;
        this.paper = paper;
        this.brush = DEFAULT_CHAR;

        if (this.paper.isWithinBounds(position)) {
            this.position = position;
        } else {
            System.out.println("Warning: Initial position for turtle "
                    + "out of bounds. Setting position to (0,0).");
            this.position = new Position(0, 0);
        }
    }

    public void liftPen() {
        penState = Pen.UP;
    }

    public void putPen() {
        penState = Pen.DOWN;
    }

    public void rotate(double degrees) {
        direction.rotate(degrees);
    }

    public void changeBrush(char c) {
        this.brush = c;
    }

    public void writeMark() {
        if (penState == Pen.DOWN) {
            paper.writeChar(position, brush);
        }
    }

    public void move(int steps) {
        while (steps > 0) {
            Position deltaPosition = direction.getDeltaPosition();
            Position newPosition = position.plus(deltaPosition);
            position = newPosition;
            writeMark();
            steps -= 1;
        }
    }

}
