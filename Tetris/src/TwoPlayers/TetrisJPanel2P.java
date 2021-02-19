package TwoPlayers;

import MainGame.*;
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
public class TetrisJPanel2P
        extends JPanel
{

    public static final int CANVAS_WIDTH = 416;
    public static final int CANVAS_HEIGHT = 840;
    private final int cellSize = 40;
    private final Board board;
    private final BoardUpdater bu;

    public TetrisJPanel2P()
    {
        bu = new BoardUpdater();
        final int BOARD_TOP = 0;
        final int BOARD_LEFT = 0;
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
                case KeyEvent.VK_NUMPAD0:
                    board.rotateRight = true;
                    break;
                case KeyEvent.VK_UP:
                    board.rotateLeft = true;
                    break;
                case KeyEvent.VK_LEFT:
                    board.moveLeft = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    board.moveRight = true;
                    break;
                case KeyEvent.VK_DOWN:
                    board.fallFast = true;
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
                    board.moveLeft = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    board.moveRight = false;
                    break;
                case KeyEvent.VK_DOWN:
                    board.fallFast = false;
                    board.ignoreFallFast = false;
                    break;
            }
        }
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
        TetrisJPanel2P game = new TetrisJPanel2P();
        frame.add(game);
        frame.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true)
        {
            if (!game.bu.update(game.board))
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
