package turtle;

import turtle.util.Direction;
import turtle.util.Pen;
import turtle.util.Position;

public class TurtleWrapping extends TurtleAbstract
{

    public TurtleWrapping(Position position, Direction direction, Pen penState,
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
            Position nextPosition = position.plus(deltaPosition);
            position = nextPosition;
            wrapPosition(position);
            writeMark();
            steps -= 1;
        }
    }
    
    private void wrapPosition(Position p)
    {
        while (p.getX() < 0)
        {
            p.setX(p.getX() + paper.getWidth());
        }
        while (p.getY() < 0)
        {
            p.setY(p.getY() + paper.getHeight());
        }
        
        p.set(p.getX() % paper.getWidth(), p.getY() % paper.getHeight());
    }
    
    @Override
    public String status()
    {
        return "Wrapping " + super.status();
    }
    
}
