/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainGame;

import model.Board;

/**
 *
 * @author SebZeva
 */
public enum UpdaterFuncEnum
{
    FALL,
    FALL_FAST,
    ROTATE,
    MOVE;

    public boolean update(Board board)
    {
        switch (this)
        {
            case FALL:
                return fall(board);
            case FALL_FAST:
            // do fallFast if (board.fallFast AND NOT board.ignoreFallFast)
                return !board.fallFast || board.ignoreFallFast ||
                        fallFast(board);
            case MOVE:
                return move(board);
            case ROTATE:
                return rotate(board);

        }
        return false;
    }

    private static boolean fall(Board board)
    {
        return board.fall() || board.fuse();
    }

    private static boolean fallFast(Board board)
    {
        if (!board.fall())
        {
            board.ignoreFallFast = true;
            if (!board.fallFast)
            {
                board.ignoreFallFast = false;
            }
            return board.fuse();
        }
        return true;
    }

    private static boolean move(Board board)
    {
        if (board.moveLeft ^ board.moveRight)
        {
            board.move(board.moveRight);
        }
        return true;
    }

    private static boolean rotate(Board board)
    {
        if (board.rotateLeft ^ board.rotateRight)
        {
            board.rotate(board.rotateRight);
        }
        if (board.rotateRight)
        {
            board.rotateRight = false;
        }
        if (board.rotateLeft)
        {
            board.rotateLeft = false;
        }
        return true;
    }
}
