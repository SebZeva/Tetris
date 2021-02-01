/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;

/**
 *
 * @author SebZeva
 */
public enum Cell
{
    BLUE(Color.BLUE),
    GREEN(Color.GREEN),
    RED(Color.RED),
    YELLOW(Color.YELLOW),
    WHITE(Color.WHITE),
    ORANGE(Color.ORANGE),
    PINK(Color.PINK),
    TRANSPARENT(new Color(0, 0, 0, 0));

    private Color colour;

    private Cell(Color colour)
    {
        this.colour = colour;
    }

    /**
     * Getter for the current colour.
     * 
     * @return colour of the cell.
     */
    public Color getColour()
    {
        return colour;
    }

    /**
     * Tests for transparent colour.
     * 
     * @return boolean indicating whether colour is transparent.
     */
    public boolean isEmpty()
    {
        return colour.equals(new Color(0, 0, 0, 0));
    }
}
