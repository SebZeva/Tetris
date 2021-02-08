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
import model.DifficultyEnum;
import model.Tetromino;

@SuppressWarnings("serial")
public class TetrisJPanel
        extends JPanel
{

    final int BOARD_TOP = 50;
    final int BOARD_LEFT = 600;
    final int CANVAS_WIDTH = 1600;
    final int CANVAS_HEIGHT = 900;
    final int BOARD_WIDTH = 10;
    final int BOARD_HEIGHT = 20;
    final int CELL_SIZE = 40;
    long clockNanoFall = 250000000;
    long clockNanoMove = 80000000;
    public static final String TITLE = "Tetris";
    private Tetromino currentTetromino = new Tetromino();
    final Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
    private boolean rotateRight = false;
    private boolean left = false;
    private boolean right = false;
    private boolean rotateLeft = false;
    private boolean fallFast = false;

    public TetrisJPanel()
    {
        KeyListener listener = new MyKeyListener();
        addKeyListener(listener);
        setFocusable(true);
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
                    rotateRight = true;
                    break;
                case KeyEvent.VK_DOWN:
                    fallFast = true;
                    break;
                case KeyEvent.VK_LEFT:
                    left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            int keyCode = e.getKeyCode();
            switch (keyCode)
            {
                case KeyEvent.VK_UP:
                    rotateRight = false;
                    break;
                case KeyEvent.VK_DOWN:
                    fallFast = false;
                    break;
                case KeyEvent.VK_LEFT:
                    left = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = false;
                    break;
            }
        }
    }

    private int update(long nanosFall, long nanosMove)
    {
        int ret = 0;
        if (nanosFall > clockNanoFall || fallFast && nanosFall > clockNanoMove)
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
        if (nanosMove > clockNanoMove && (left ^ right))
        {
            currentTetromino.move(board, right);
            ret += 2;
        }
        if (rotateRight)
        {
            currentTetromino.rotate(board, true);
            rotateRight = false;
        }
        if (rotateLeft)
        {
            currentTetromino.rotate(board, false);
            rotateLeft = false;
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

        long nanosFall = System.nanoTime();
        long currTime = nanosFall;
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
        game.currentTetromino.setBrighter(true);
        game.repaint();
        for (int i = 0; i < 3; ++i)
        {
            TimeUnit.MILLISECONDS.sleep(500);
            game.currentTetromino.setTransparent(true);
            game.repaint();
            TimeUnit.MILLISECONDS.sleep(500);
            game.currentTetromino.setTransparent(false);
            game.repaint();
        }
        System.out.println("Done");
    }

}
