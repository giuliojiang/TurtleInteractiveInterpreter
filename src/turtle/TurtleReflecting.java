package turtle;

import turtle.util.Direction;
import turtle.util.Pen;
import turtle.util.Position;
import turtle.util.Rotation;

public class TurtleReflecting extends TurtleAbstract
{

    public TurtleReflecting(Position position, Direction direction,
        Pen penState, Paper paper, String name)
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
                if (!paper.isWithinBoundsX(newPosition))
                {
                    direction = direction.flipX();
                } else
                {
                    position.setX(newPosition.getX());
                }
                
                if (!paper.isWithinBoundsY(newPosition))
                {
                    direction = direction.flipY();
                } else
                {
                    position.setY(newPosition.getY());
                }
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
        return "Reflecting " + super.status();
    }

}
