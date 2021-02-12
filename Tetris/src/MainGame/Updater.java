/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainGame;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author SebZeva
 */
public class Updater
{

    private final Queue<UpdaterNode> pq;
    public long clockFall = 250000000;
    public long clockMove = 80000000;
    public long clockFallFast = 50000000;
    private long fallen;
    private long moved;
    private long fastFallen;

    public Updater()
    {
        fastFallen = moved = fallen = System.nanoTime();

        pq = new PriorityQueue<>(3);
        pq.add(new UpdaterNode(UpdaterFuncEnum.FALL_FAST, fastFallen +=
                clockFallFast));
        pq.add(new UpdaterNode(UpdaterFuncEnum.FALL, fallen += clockFall));
        pq.add(new UpdaterNode(UpdaterFuncEnum.MOVE, moved += clockMove));
    }


}
