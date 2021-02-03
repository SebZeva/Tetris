/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
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

    /**
     * Constructor for board.
     *
     * @param width horizontal length of the board in cells.
     * @param height vertical length of the board in cells.
     */
    public Board(int width, int height)
    {
        this.WIDTH = width;
        this.HEIGHT = height;
        cells = new Cell[width][height];
        for (int top = 0; top < height; top++)
        {
            for (int left = 0; left < width; left++)
            {
                cells[left][top] = Cell.BLACK;
            }
        }
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

    public void fuse(Tetromino t)
    {
        Set<Integer> yCoords = new TreeSet<Integer>();
        int[] coords =
        {
            t.getLeft(), t.getTop()
        };
        int[][] someRelCoords = t.getRelativeCoords();
        for (int[] relCoords : someRelCoords)
        {
            cells[coords[0] + relCoords[0]][coords[1] + relCoords[1]] = t.
                    getCell();
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
    }

    public void paint(Graphics2D g2d, int left, int top, int cellSize)
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
    }
}
