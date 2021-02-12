package MainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Board;

@SuppressWarnings("serial")
public class TetrisJPanel
        extends JPanel
{
    public static final int CANVAS_WIDTH = 1600;
    public static final int CANVAS_HEIGHT = 900;
    private int cellSize;
    long clockFall = 250000000;
    long clockMove = 80000000;
    long clockFallFast = 40000000;
    final Board board;
    boolean left = false;
    boolean right = false;
    boolean leftL = false;
    boolean rightL = false;
    boolean rotateLeft = false;
    boolean rotateLeftL = false;
    boolean rotateRight = false;
    boolean rotateRightL = false;
    boolean fallFast = false;
    boolean fallFastL = false;
    boolean ignoreFallFast = false;

    public TetrisJPanel()
    {
        cellSize = 40;
        final int BOARD_TOP = 50;
        final int BOARD_LEFT = 600;
        final int BOARD_WIDTH = 10;
        final int BOARD_HEIGHT = 20;
        board = new Board(BOARD_WIDTH, BOARD_HEIGHT, BOARD_LEFT, BOARD_TOP);
        KeyListener listener = new MyKeyListener();
        super.addKeyListener(listener);
        super.setFocusable(true);
    }

    public class MyKeyListener
            implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e)
        {
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            int keyCode = e.getKeyCode();
            switch (keyCode)
            {
                case KeyEvent.VK_INSERT:
                case KeyEvent.VK_0:
                    rotateRight = true;
                case KeyEvent.VK_E:
                    rotateRightL = true;
                    break;
                case KeyEvent.VK_UP:
                    rotateLeft = true;
                case KeyEvent.VK_R:
                    rotateLeftL = true;
                    break;
                case KeyEvent.VK_LEFT:
                    left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;
                case KeyEvent.VK_D:
                    leftL = true;
                    break;
                case KeyEvent.VK_F:
                    rightL = true;
                    break;
                case KeyEvent.VK_DOWN:
                    fallFast = true;
                    break;
                case KeyEvent.VK_SPACE:
                    fallFastL = true;
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            int keyCode = e.getKeyCode();
            switch (keyCode)
            {
                case KeyEvent.VK_LEFT:
                    left = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = false;
                    break;
                case KeyEvent.VK_D:
                    leftL = false;
                    break;
                case KeyEvent.VK_F:
                    rightL = false;
                    break;
                case KeyEvent.VK_DOWN:
                    fallFast = false;
                    ignoreFallFast = false;
                    break;
                case KeyEvent.VK_SPACE:
                    fallFastL = false;
                    ignoreFallFast = false;
                    break;
            }
        }
    }

    private int update(long nanosFall, long nanosMove)
    {
        boolean localLeft = left || leftL;
        boolean localRight = right || rightL;
        boolean localFallFast = !ignoreFallFast && (fallFast || fallFastL);
        int ret = 0;
        if (localFallFast && nanosFall > clockFallFast ||
                nanosFall > clockFall)
        {
            if (!board.fall())
            {
                if (!board.fuse())
                {
                    return 4;
                }
                if (fallFast || fallFastL)
                {
                    ignoreFallFast = true;
                }
            }
            ret |= 1;
        }
        if (nanosMove > clockMove && (localLeft != localRight))
        {
            board.currentTetromino.move(board, localRight);
            ret |= 2;
        }
        if (rotateRight || rotateRightL)
        {
            board.currentTetromino.rotate(board, true);
            rotateRight = false;
            rotateRightL = false;
        }
        if (rotateLeft || rotateLeftL)
        {
            board.currentTetromino.rotate(board, false);
            rotateLeft = false;
            rotateLeftL = false;
        }
        return ret;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        board.paint(g2d, cellSize);
    }

    public static void main(String[] args)
            throws InterruptedException
    {
        final String TITLE = "Tetris";
        JFrame frame = new JFrame(TITLE);
        TetrisJPanel game = new TetrisJPanel();
        frame.add(game);
        frame.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        long currTime;
        long nanosFall = System.nanoTime();
        long nanosMove = nanosFall;
        while (true)
        {
            currTime = System.nanoTime();
            int result = game.update(currTime - nanosFall, currTime - nanosMove);
            if ((result & 1) == 1)
            {
                nanosFall = currTime;
            }
            if ((result & 2) == 2)
            {
                nanosMove = currTime;
            }
            else if (result == 4)
            {
                break;
            }
            game.repaint();
            TimeUnit.MILLISECONDS.sleep(10);
        }
        game.repaint();
        boolean transp = false;
        for (int i = 0; i < 6; ++i)
        {
            TimeUnit.MILLISECONDS.sleep(500);
            game.board.currentTetromino.setTransparent(transp ^= true);
            game.repaint();
        }
        System.out.println("Done");
    }

}