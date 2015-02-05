package turtle.util;

public enum Direction
{

    // IMPORTANT
    // the order counts for rotate to work!!!!!!!!!!!!!!!
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    /**
     * Rotates this Direction LEFT or RIGHT
     * 
     * 
     * @param rotation
     *            Left or Right rotation
     * @return the new rotated Direction
     */
    public Direction rotate(Rotation rotation)
    {
        if (rotation == Rotation.LEFT)
        {
            return Direction.values()[(this.ordinal() - 1 + Direction.values().length)
                % Direction.values().length];
        }
        else
        {
            return Direction.values()[(this.ordinal() + 1)
                % Direction.values().length];
        }
    }

    public Position getDeltaPosition()
    {
        switch (this)
        {
            case NORTH:
                return new Position(0, 1);
            case NORTH_EAST:
                return new Position(1, 1);
            case EAST:
                return new Position(1, 0);
            case SOUTH_EAST:
                return new Position(1, -1);
            case SOUTH:
                return new Position(0, -1);
            case SOUTH_WEST:
                return new Position(-1, -1);
            case WEST:
                return new Position(-1, 0);
            case NORTH_WEST:
                return new Position(-1, 1);
            default:
                return new Position(0, 0); // impossible!
        }
    }
    
    public Direction flipX()
    {
        switch (this)
        {
            case NORTH:
                return Direction.NORTH;
            case NORTH_EAST:
                return Direction.NORTH_WEST;
            case EAST:
                return Direction.WEST;
            case SOUTH_EAST:
                return Direction.SOUTH_WEST;
            case SOUTH:
                return Direction.SOUTH;
            case SOUTH_WEST:
                return Direction.SOUTH_EAST;
            case WEST:
                return Direction.EAST;
            case NORTH_WEST:
                return Direction.NORTH_EAST;
            default:
                return null; // impossible!
        }
    }
    
    public Direction flipY()
    {
        switch (this)
        {
            case NORTH:
                return Direction.SOUTH;
            case NORTH_EAST:
                return Direction.SOUTH_EAST;
            case EAST:
                return Direction.EAST;
            case SOUTH_EAST:
                return Direction.NORTH_EAST;
            case SOUTH:
                return Direction.NORTH;
            case SOUTH_WEST:
                return Direction.NORTH_WEST;
            case WEST:
                return Direction.WEST;
            case NORTH_WEST:
                return Direction.SOUTH_WEST;
            default:
                return null; // impossible!
        }
    }
    
}
