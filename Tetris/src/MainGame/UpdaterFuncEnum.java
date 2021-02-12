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
    MOVE;

    public boolean update(Board board)
    {
        switch (this)
        {
            case FALL:
                return fall(board);
            case FALL_FAST:
                return !board.fallFast || fallFast(board);
            case MOVE:
                return move(board);
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
}
