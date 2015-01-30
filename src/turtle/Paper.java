package turtle;

import java.util.HashMap;

import turtle.util.Position;

public class Paper {

    private int width;
    private int height;
    private char[][] grid;

    public Paper(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new char[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isWithinBounds(double x, double y) {
        return (0 <= x && x < width) && (0 <= y && y < height);
    }

    public boolean isWithinBounds(int x, int y) {
        return (0 <= x && x < width) && (0 <= y && y < height);
    }

    public boolean isWithinBounds(Position p) {
        return (0 <= p.getX() && p.getX() < width)
                && (0 <= p.getY() && p.getY() < height);
    }

    public void writeChar(double x, double y, char c) {
        int xn = (int) Math.round(x);
        int yn = (int) Math.round(y);
        if (isWithinBounds(xn, yn)) {
            grid[xn][yn] = c;
        }
    }

    public void writeChar(Position p, char c) {
        int xn = (int) Math.round(p.getX());
        int yn = (int) Math.round(p.getY());
        if (isWithinBounds(xn, yn)) {
            grid[xn][yn] = c;
        }
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int j = height - 1; j >= 0; j--) {
            for (int i = 0; i < width; i++) {
                out.append(grid[i][j]);
            }
            out.append('\n');
        }
        return out.toString();
    }

}
