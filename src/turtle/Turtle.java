package turtle;

import turtle.util.Position;
import turtle.util.Rotation;

public interface Turtle
{

    public void changeBrush(char c);

    public void liftPen();

    public void move(int n);

    public void putPen();

    public void rotate(Rotation r, int x);

    public void writeMark();
    
    public String status();
    
    public String getName();

    // public Position getPosition();

}
