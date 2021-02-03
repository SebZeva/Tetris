package MainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Board;
import model.Tetromino;

@SuppressWarnings("serial")
public class TetrisJPanel
        extends JPanel
{

    final int BOARD_TOP = 60;
    final int BOARD_LEFT = 600;
    final int CANVAS_WIDTH = 1600;
    final int CANVAS_HEIGHT = 900;
    final int BOARD_WIDTH = 10;
    final int BOARD_HEIGHT = 20;
    final int CELL_SIZE = 40;
    long clockNanoUpdate = 200000000;
    long clockNanoMove = 80000000;
    public static final String TITLE = "Tetris";
    private Tetromino currentTetromino = new Tetromino();
    final Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
    private boolean fallFast = true;
    private boolean moveLeft = false;
    private boolean moveRight = false;

    public TetrisJPanel()
    {
        KeyListener listener = new MyKeyListener();
        addKeyListener(listener);
        setFocusable(true);
    }

    private int update(long nanosUpdate, long nanosMove)
    {
        int ret = 0;
        if (nanosUpdate > clockNanoUpdate)
        {
            if (!currentTetromino.fall(board))
            {
                board.fuse(currentTetromino);
                currentTetromino = new Tetromino();
                if (currentTetromino.collides(board))
                {
                    return 4;
                }
            }
            ret += 1;
        }
        if (nanosMove > clockNanoMove && (moveRight ^ moveLeft))
        {
            currentTetromino.move(board, moveRight);
            ret += 2;
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
        board.paint(g2d, BOARD_LEFT, BOARD_TOP, CELL_SIZE);
        currentTetromino.paint(g2d, BOARD_LEFT, BOARD_TOP, CELL_SIZE);
    }

    public static void main(String[] args)
            throws InterruptedException
    {
        JFrame frame = new JFrame(TITLE);
        TetrisJPanel game = new TetrisJPanel();
        frame.add(game);
        frame.setSize(game.CANVAS_WIDTH, game.CANVAS_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        long nanos = System.nanoTime();
        long currTime = nanos;
        long nanosMove = nanos;
        mainLoop:
        while (true)
        {
            currTime = System.nanoTime();
            int result = game.update(currTime - nanos, currTime - nanosMove);
            if ((result & 1) == 1)
            {
                nanos = currTime;
            }
            if ((result & 2) == 2)
            {
                nanosMove = currTime;
            }
            else if (result == 4)
            {
                break mainLoop;
            }
            game.repaint();
            Thread.sleep(10);
        }
        System.out.println("Done");
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
                case KeyEvent.VK_UP:
                    currentTetromino.rotate(board, true);
                    break;
                case KeyEvent.VK_DOWN:
                    fallFast = true;
                    break;
                case KeyEvent.VK_LEFT:
                    moveLeft = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    moveRight = true;
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            int keyCode = e.getKeyCode();
            switch (keyCode)
            {
                case KeyEvent.VK_DOWN:
                    fallFast = false;
                    break;
                case KeyEvent.VK_LEFT:
                    moveLeft = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    moveRight = false;
                    break;
            }
        }
    }
}
