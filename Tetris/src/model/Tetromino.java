/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SebZeva
 */
public class Tetromino
{

    private ShapeEnum shape;
    private int rotation;
    private int posX;
    private int posY;
    private Color kolorea;

    static final String[][] S_SHAPE =
    {
        {
            ".....",
            ".....",
            "..00.",
            ".00..",
            "....."
        },
        {
            ".....",
            "..O..",
            "..OO.",
            "...O.",
            "....."
        }
    };
    static final String[][] Z_SHAPE =
    {
        {
            ".....",
            ".....",
            ".00..",
            "..00.",
            "....."
        },
        {
            ".....",
            "..0..",
            ".00..",
            ".0...",
            "....."
        }
    };
    static final String[][] I_SHAPE =
    {
        {
            "..0..",
            "..0..",
            "..0..",
            "..0..",
            "....."
        },
        {
            ".....",
            ".....",
            "0000.",
            ".....",
            "....."
        }
    };
    static final String[][] O_SHAPE =
    {
        {
            ".....",
            ".....",
            ".00..",
            ".00..",
            "....."
        }
    };
    static final String[][] J_SHAPE =
    {
        {
            ".....",
            ".0...",
            ".000.",
            ".....",
            "....."
        },
        {
            ".....",
            "..00.",
            "..0..",
            "..0..",
            "....."
        },
        {
            ".....",
            ".....",
            ".000.",
            "...0.",
            "....."
        },
        {
            ".....",
            "..0..",
            "..0..",
            ".00..",
            "....."
        }
    };
    static final String[][] L_SHAPE =
    {
        {
            ".....",
            "...0.",
            ".000.",
            ".....",
            "....."
        },
        {
            ".....",
            "..0..",
            "..0..",
            "..00.",
            "....."
        },
        {
            ".....",
            ".....",
            ".000.",
            ".0...",
            "....."
        },
        {
            ".....",
            ".00..",
            "..0..",
            "..0..",
            "....."
        }
    };
    static final String[][] T_SHAPE =
    {
        {
            ".....",
            "..0..",
            ".000.",
            ".....",
            "....."
        },
        {
            ".....",
            "..0..",
            "..00.",
            "..0..",
            "....."
        },
        {
            ".....",
            ".....",
            ".000.",
            "..0..",
            "....."
        },
        {
            ".....",
            "..0..",
            ".00..",
            "..0..",
            "....."
        }
    };
    static final Map<ShapeEnum, String[][]> SHAPES = getShapes();

    static final Color[] colors =
    {
        Color.BLUE, Color.GREEN, Color.RED,
        Color.YELLOW, Color.WHITE, Color.ORANGE, Color.PINK
    };

    private static HashMap<ShapeEnum, String[][]> getShapes()
    {
        HashMap<ShapeEnum, String[][]> shapes =
                new HashMap<ShapeEnum, String[][]>();
        shapes.put(ShapeEnum.S, S_SHAPE);
        shapes.put(ShapeEnum.Z, Z_SHAPE);
        shapes.put(ShapeEnum.J, J_SHAPE);
        shapes.put(ShapeEnum.L, L_SHAPE);
        shapes.put(ShapeEnum.I, I_SHAPE);
        shapes.put(ShapeEnum.O, O_SHAPE);
        shapes.put(ShapeEnum.T, T_SHAPE);
        return shapes;
    }

    public Tetromino()
    {
        ShapeEnum shape = ShapeEnum.values()[Random.randomInt(0, ShapeEnum.
                values().length - 1)];
        rotation = 0;
        posX = 0;
        posY = 0;
        kolorea = colors[Random.randomInt(0, colors.length - 1)];
    }

    
}
