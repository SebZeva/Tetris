/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics2D;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author SebZeva
 */
public class Board
{

    private final int WIDTH;
    private final int HEIGHT;
    private final Cell[][] cells;
    public Tetromino currentTetromino;
    private int left;
    private int top;

    /**
     * Constructor for board.
     *
     * @param width horizontal length of the board in cells.
     * @param height vertical length of the board in cells.
     * @param left distance to left of the screen.
     * @param top distance to top of the screen.
     */
    public Board(int width, int height, int left, int top)
    {
        this.left = left;
        this.top = top;
        this.WIDTH = width;
        this.HEIGHT = height;
        cells = new Cell[width][height];
        for (int j = 0; j < height; j++)
        {
            for (int i = 0; i < width; i++)
            {
                cells[i][j] = Cell.BLACK;
            }
        }
        currentTetromino = new Tetromino();
    }

    /**
     * Getter for the width of the board.
     *
     * @return horizontal length of the board in cells.
     */
    public int getWidth()
    {
        return WIDTH;
    }

    /**
     * Getter for the height of the board.
     *
     * @return vertical length of the board in cells.
     */
    public int getHeight()
    {
        return HEIGHT;
    }

    /**
     * Looks at a cell and tells if it is empty.
     *
     * @param left distance in cells to the left of the board.
     * @param top distance in cells to the top of the board.
     * @return boolean representing whether the cell is empty.
     */
    public boolean isEmpty(int left, int top)
    {
        if (left < 0 || top < 0 || left >= WIDTH || top >= HEIGHT)
        {
            return false;
        }
        return cells[left][top].isEmpty();
    }

    public boolean fuse()
    {
        Set<Integer> yCoords = new TreeSet<>();
        int[] coords =
        {
            currentTetromino.getLeft(), currentTetromino.getTop()
        };
        int[][] someRelCoords = currentTetromino.getRelativeCoords();
        for (int[] relCoords : someRelCoords)
        {
            cells[coords[0] + relCoords[0]][coords[1] + relCoords[1]] =
                    currentTetromino.getCell();
            yCoords.add(coords[1] + relCoords[1]);
        }
        yFor:
        for (Integer yCoord : yCoords)
        {
            for (int xCoord = 0; xCoord < WIDTH; ++xCoord)
            {
                if (cells[xCoord][yCoord].isEmpty())
                {
                    continue yFor;
                }
            }
            for (int j = yCoord; j > 0; --j)
            {
                for (int i = 0; i < WIDTH; ++i)
                {
                    cells[i][j] = cells[i][j - 1];
                }
            }
        }
        currentTetromino = new Tetromino();
        return !currentTetromino.collides(this);
    }
    
    public boolean fall()
    {
        return currentTetromino.fall(this);
    }

    public void paint(Graphics2D g2d, int cellSize)
    {
        for (int x = 0; x < cells.length; ++x)
        {
            for (int y = 0; y < cells[x].length; ++y)
            {
                g2d.setColor(cells[x][y].getColour());
                g2d.fillRoundRect(left + x * cellSize, top + y * cellSize,
                        cellSize, cellSize, cellSize / 3, cellSize / 3);

            }
        }
        currentTetromino.paint(g2d, left, top, cellSize);
    }
}
