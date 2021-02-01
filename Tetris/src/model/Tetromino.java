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
    private int left;
    private int top;
    private final Color colour;

    private static final String[][] S_SHAPE =
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
    private static final String[][] Z_SHAPE =
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
    private static final String[][] I_SHAPE =
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
    private static final String[][] O_SHAPE =
    {
        {
            ".....",
            ".....",
            ".00..",
            ".00..",
            "....."
        }
    };
    private static final String[][] J_SHAPE =
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
    private static final String[][] L_SHAPE =
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
    private static final String[][] T_SHAPE =
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
    private static final Map<ShapeEnum, String[][]> SHAPES_STRING = getShapes();

    private static final Map<ShapeEnum, int[][][]> SHAPES_COORD =
            getShapesCoord(SHAPES_STRING);

    static final Color[] colours =
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

    private static Map<ShapeEnum, int[][][]> getShapesCoord(
            Map<ShapeEnum, String[][]> stringShapes)
    {
        Map<ShapeEnum, int[][][]> shapes = new HashMap<ShapeEnum, int[][][]>();
        for (ShapeEnum s : stringShapes.keySet())
        {
            String[][] shape = stringShapes.get(s);
            int[][][] val = new int[shape.length][4][2];
            for (int rot = 0; rot < shape.length; ++rot)
            {
                String[] currentRot = shape[rot];
                int count = 0;
                rotations:
                for (int row = 0; row < currentRot.length; ++row)
                {
                    String currentRow = currentRot[row];
                    for (int col = 0; col < currentRow.length(); ++col)
                    {
                        if (currentRow.charAt(col) == '0')
                        {
                            val[rot][count][0] = col;
                            val[rot][count][1] = row;
                            ++count;
                            if (count == 4)
                            {
                                break rotations;
                            }
                        }
                    }
                }
            }
            shapes.put(s, val);
        }
        return shapes;
    }

    public Tetromino()
    {
        ShapeEnum shape = ShapeEnum.values()[Random.randomInt(0, ShapeEnum.
                values().length - 1)];

        rotation = 0;
        left = 0;
        top = 0;
        colour = colours[Random.randomInt(0, colours.length - 1)];
    }

    private boolean collides(Board board)
    {
        int[][] current = SHAPES_COORD.get(shape)[rotation];
        for (int[] coords : current)
        {
            if (!board.isEmpty(coords[0], coords[1]))
            {
                return true;
            }
        }
        return false;
    }

    public boolean fall(Board board)
    {
        ++top;
        if (collides(board))
        {
            --top;
            return false;
        }
        return true;
    }

    public boolean move(Board board, boolean right)
    {
        int delta;
        if (right)
        {
            delta = 1;
        }
        else
        {
            delta = -1;
        }
        left += delta;
        if (collides(board))
        {
            left -= delta;
            return false;
        }
        return true;
    }
    

    public boolean rotate(Board board, boolean right)
    {
        int delta;
        if (right)
        {
            delta = -1;
        }
        else
        {
            delta = 1;
        }
        rotation = (rotation + delta + 4) % SHAPES_COORD.get(shape).length;
        if (collides(board))
        {
            if (move(board, true))
            {
                return true;
            }
            else if (move(board, false))
            {
                return true;
            }
            rotation = (rotation + 4 - delta) % SHAPES_COORD.get(shape).length;
            return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
        for (ShapeEnum s : SHAPES_COORD.keySet())
        {
            System.out.println(s);
            for (int[][] currentRot : SHAPES_COORD.get(s))
            {
                System.out.println("\trot:");
                for (int[] currentPiece : currentRot)
                {
                    System.out.println("\t\tpiece:");
                    for (int coord : currentPiece)
                    {
                        System.out.println("\t\t\t" + coord);
                    }
                }
            }
        }
    }
}
