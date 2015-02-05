package turtle;

import turtle.util.Direction;
import turtle.util.Pen;
import turtle.util.Position;

public class TurtleContinuous extends TurtleAbstract
{

    public TurtleContinuous(Position position, Direction direction,
        Pen penState, Paper paper, String name)
    {
        super(position, direction, penState, paper, name);
    }
    
    @Override
    public String status()
    {
        return "Continuous " + super.status();
    }

}
