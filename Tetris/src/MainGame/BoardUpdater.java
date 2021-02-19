/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainGame;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Board;

public class BoardUpdater
{

    private final Queue<UpdaterNode> pq;
    public long clockFall = 250000000;
    public long clockMove = 80000000;
    public long clockFallFast = 50000000;
    public long clockRotate = 40000000;

    public BoardUpdater()
    {
        long time = System.nanoTime();

        pq = new PriorityQueue<>(3);
        pq.
                add(new UpdaterNode(UpdaterFuncEnum.FALL_FAST, time +=
                        clockFallFast));
        pq.add(new UpdaterNode(UpdaterFuncEnum.FALL, time += clockFall));
        pq.add(new UpdaterNode(UpdaterFuncEnum.MOVE, time += clockMove));
        pq.add(new UpdaterNode(UpdaterFuncEnum.ROTATE, time += clockRotate));
    }

    public boolean update(Board board)
    {
        UpdaterNode un = pq.remove();
        UpdaterFuncEnum act = un.getAction();
        long extraTime = 0;
        switch (act)
        {
            case FALL:
                extraTime = clockFall;
                break;
            case FALL_FAST:
                extraTime = clockFallFast;
                break;
            case MOVE:
                extraTime = clockMove;
                break;
            case ROTATE:
                extraTime += clockRotate;
                break;
            default:
                extraTime = 0;
                break;
        }
        long time = un.getTime();
        pq.offer(new UpdaterNode(act, time + extraTime));
        try
        {
            TimeUnit.NANOSECONDS.sleep(time - System.nanoTime());
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(BoardUpdater.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return act.update(board);
    }
}
