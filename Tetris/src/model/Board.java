/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author SebZeva
 */
public class Board
{
    private final int WIDTH;
    private final int HEIGHT;
    private final Cell[][] cells;

    public Board(int width, int height)
    {
        this.WIDTH = width;
        this.HEIGHT = height;
        cells = new Cell[width][height];
        for (int top = 0; top < height; top++)
        {
            for (int left = 0; left < width; left++)
            {
                cells[left][top] = Cell.TRANSPARENT;
            }
        }
    }

    public int getWidth()
    {
        return WIDTH;
    }

    public int getHeight()
    {
        return HEIGHT;
    }
    
    public boolean isEmpty(int left, int top)
    {
        if (left < 0 || top < 0 || left >= WIDTH || top >= HEIGHT)
        {
            return false;
        }
        return cells[left][top].isEmpty();
    }
}
