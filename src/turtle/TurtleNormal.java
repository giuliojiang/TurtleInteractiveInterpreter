package turtle;

import turtle.util.Direction;
import turtle.util.Pen;
import turtle.util.Position;

public class TurtleNormal extends TurtleAbstract
{

    public TurtleNormal(Position position, Direction direction, Pen penState,
        Paper paper, String name)
    {
        super(position, direction, penState, paper, name);
    }

    @Override
    public void move(int steps)
    {
        if (steps == 0)
        {
            return;
        } else
        {
            Position deltaPosition = direction.getDeltaPosition();

            Position nextPosition = position.plus(deltaPosition);
            if (!paper.isWithinBounds(nextPosition.getX(), nextPosition.getY()))
            {
                // write
                writeMark();
            } else
            {
                position = nextPosition;

                // write
                writeMark();
                move(steps - 1);
            }
        }
    }
    
    @Override
    public String status()
    {
        return "Normal " + super.status();
    }
    
}
