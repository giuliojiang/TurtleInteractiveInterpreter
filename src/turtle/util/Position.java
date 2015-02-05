package turtle.util;

public class Position
{

    private int x;
    private int y;

    /**
     * Construct a Position from x and y
     * 
     * @param x
     * @param y
     */
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void set(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Adds a Position p to this Position In a vectorial way
     * 
     * @param p
     *            The Position to be added
     * @return a new Position which is the sum
     */
    public Position plus(Position p)
    {
        return new Position(this.x + p.getX(), this.y + p.getY());
    }
    
    public Position minus(Position p)
    {
        return new Position(this.x - p.getX(), this.y - p.getY());
    }

    public String toString()
    {
        return "(" + x + "," + y + ")";
    }

}
