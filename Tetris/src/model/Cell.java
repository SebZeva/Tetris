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
    CYAN(Color.CYAN),
    ORANGE(Color.ORANGE),
    PINK(Color.PINK),
    BLACK(Color.BLACK);

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
        return new Color(colour.getRed(), colour.getGreen(), colour.getBlue(),
                colour.getAlpha());
    }

    

    /**
     * Tests for transparent colour.
     *
     * @return boolean indicating whether colour is transparent.
     */
    public boolean isEmpty()
    {
        return colour.equals(Color.BLACK);
    }
}
