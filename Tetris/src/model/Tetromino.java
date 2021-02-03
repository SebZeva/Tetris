/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics2D;
import java.util.Collections;
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
    private final Cell colour;
    
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
            "..0..",
            "..00.",
            "...0.",
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
            ".....",
            "..0..",
            "..0..",
            "..0..",
            "..0.."
        },
        {
            ".....",
            ".....",
            ".....",
            "0000.",
            "....."
        }
    };
    private static final String[][] O_SHAPE =
    {
        {
            ".....",
            ".00..",
            ".00..",
            ".....",
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
    private static final Map<ShapeEnum, String[][]> SHAPES_STRING;
    
    static
    {
        Map<ShapeEnum, String[][]> aMap = getShapes();
        SHAPES_STRING = Collections.
                unmodifiableMap(aMap);
    }
    
    private static final Map<ShapeEnum, int[][][]> SHAPES_COORD;
    
    static
    {
        Map<ShapeEnum, int[][][]> aMap = getShapesCoord(SHAPES_STRING);
        SHAPES_COORD = Collections.unmodifiableMap(aMap);
    }

    public int getLeft()
    {
        return left;
    }
    
    public int getTop()
    {
        return top;
    }
    
    public int[][] getRelativeCoords()
    {
        return SHAPES_COORD.get(shape)[rotation];
    }
    
    public Cell getCell()
    {
        return colour;
    }

    /**
     * Colours the Tetromino may take.
     */
    static final Map<ShapeEnum, Cell> COLOURS;
    
    static
    {
        Map<ShapeEnum, Cell> aMap = new HashMap<ShapeEnum, Cell>();
        aMap.put(ShapeEnum.O, Cell.YELLOW);
        aMap.put(ShapeEnum.S, Cell.RED);
        aMap.put(ShapeEnum.L, Cell.ORANGE);
        aMap.put(ShapeEnum.J, Cell.BLUE);
        aMap.put(ShapeEnum.I, Cell.CYAN);
        aMap.put(ShapeEnum.T, Cell.PINK);
        aMap.put(ShapeEnum.Z, Cell.GREEN);
        COLOURS = Collections.unmodifiableMap(aMap);
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
                rotation:
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
                                break rotation;
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
        shape = ShapeEnum.values()[Random.randomInt(0, ShapeEnum.
                values().length - 1)];
        
        rotation = 0;
        left = 2;
        top = -1;
        colour = COLOURS.get(shape);
    }
    
    public boolean collides(Board board)
    {
        int[][] current = SHAPES_COORD.get(shape)[rotation];
        for (int[] coords : current)
        {
            if (!board.isEmpty(left + coords[0], top + coords[1]))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Makes a tetromino fall.
     *
     * @param board Board object on which the tetromino is.
     * @return boolean representing success.
     */
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

    /**
     * Makes a tetromino move either to the right or to the left.
     *
     * @param board Board object on which the tetromino is.
     * @param right Whether the tetromino will move right (do left if false).
     * @return boolean representing success.
     */
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

    /**
     *
     *
     * @param board Board object on which the tetromino is.
     * @param right Whether the tetromino will rotate right (do left if false).
     * @return boolean representing success.
     */
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
    
    public void paint(Graphics2D g2d, int boardLeft, int boardTop, int cellSize)
    {
        g2d.setColor(COLOURS.get(shape).getColour());
        int[][] coordsGroup = SHAPES_COORD.get(shape)[rotation];
        for (int[] coords : coordsGroup)
        {
            g2d.fillRoundRect(boardLeft + (left + coords[0]) * cellSize,
                    boardTop + (top + coords[1]) * cellSize, cellSize, cellSize,
                    cellSize / 3, cellSize / 3);
        }
    }
    
    public static void main(String[] args)
    {
        for (ShapeEnum sh : SHAPES_COORD.keySet())
        {
            System.out.println(sh);
            for (int[][] rot : SHAPES_COORD.get(sh))
            {
                System.out.println("\trot:");
                for(int[] coords : rot)
                {
                    System.out.println("\t\tcoords:");
                    System.out.println("\t\t\t" + coords[0]);
                    System.out.println("\t\t\t" + coords[1]);
                }
            }
        }
    }
}
