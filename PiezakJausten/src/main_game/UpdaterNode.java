/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_game;

import model.Board;

/**
 *
 * @author SebZeva
 */
public final class UpdaterNode implements Comparable<UpdaterNode>
{

    private final UpdaterFuncEnum action;
    private final long time;

    public UpdaterNode(UpdaterFuncEnum action, long time)
    {
        this.action = action;
        this.time = time;
    }

    public long getTime()
    {
        return time;
    }

    public UpdaterFuncEnum getAction()
    {
        return action;
    }

    public boolean run(Board board)
    {
        return action.update(board);
    }

    @Override
    public int compareTo(UpdaterNode o)
    {
        return time < o.time ? -1 : (time == o.time ? 0 : 1);
    }
}
