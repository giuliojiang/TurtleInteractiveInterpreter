package turtle;

import java.awt.Image;
import java.awt.image.BufferedImage;

import turtle.util.Position;

public class Paper
{

    private int width;
    private int height;
    private char[][] grid;

    public Paper(int width, int height)
    {
        this.width = width;
        this.height = height;
        grid = new char[width][height];
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                grid[i][j] = ' ';
            }
        }
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public boolean isWithinBounds(int x, int y)
    {
        return (0 <= x && x < width) && (0 <= y && y < height);
    }

    public boolean isWithinBounds(Position p)
    {
        return (0 <= p.getX() && p.getX() < width)
            && (0 <= p.getY() && p.getY() < height);
    }
    
    public boolean isWithinBoundsX(Position p)
    {
        return (0 <= p.getX() && p.getX() < width);
    }
    
    public boolean isWithinBoundsY(Position p)
    {
        return (0 <= p.getY() && p.getY() < height);
    }

    public void writeChar(int x, int y, char c)
    {
        if (isWithinBounds(x, y))
        {
            grid[x][y] = c;
        }
    }

    public void writeChar(Position p, char c)
    {
        if (isWithinBounds(p.getX(), p.getY()))
        {
            grid[p.getX()][p.getY()] = c;
        }
    }

    public String status()
    {
        return "Paper [width=" + width + ", height=" + height + "]";
    }

    public String toString()
    {
        StringBuilder out = new StringBuilder();
        for (int j = height - 1; j >= 0; j--)
        {
            for (int i = 0; i < width; i++)
            {
                out.append(grid[i][j]);
            }
            out.append('\n');
        }
        return out.toString();
    }

    public Image toImage()
    {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                if (grid[x][y] != ' ')
                {
                    out.setRGB(x, y, 0);
                } else
                {
                    out.setRGB(x, y, 
                        (255) + 
                        (255 + 256) + 
                        (255 + 256 * 256));
                }
            }
        }
        
        return out;
    }
    

}
