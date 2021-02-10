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
    FALLFAST,
    MOVE;

    public void update(TetrisJPanel game)
    {
        switch (this)
        {

        }
    }

    private static boolean fall(TetrisJPanel game)
    {
        return game.board.fall() || game.board.fuse();
    }

    private static boolean fallFast(TetrisJPanel game)
    {
        if (!game.board.fall())
        {
            game.ignoreFallFast = true;
            return game.board.fuse();
        }
        return false;
    }
    
    private static boolean move(TetrisJPanel game)
    {
        
    }
}
