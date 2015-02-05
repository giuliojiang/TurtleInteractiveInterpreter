package turtle;

import turtle.util.Direction;
import turtle.util.Pen;
import turtle.util.Position;
import turtle.util.Rotation;

public class TurtleBouncy extends TurtleAbstract
{

    public TurtleBouncy(Position position, Direction direction, Pen penState,
        Paper paper, String name)
    {
        super(position, direction, penState, paper, name);
    }

    @Override
    public void move(int steps)
    {
        while (steps > 0)
        {
            Position deltaPosition = direction.getDeltaPosition();
            Position newPosition = position.plus(deltaPosition);
            
            if (!paper.isWithinBounds(newPosition))
            {
                rotate(Rotation.RIGHT, 180);
            }
            else
            {
                position = newPosition;
            }
            
            writeMark();
            
            steps -= 1;
        }
    }
    
    @Override
    public String status()
    {
        return "Bouncy " + super.status();
    }
    
}
