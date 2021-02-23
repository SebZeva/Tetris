package TwoPlayers;

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

    public static final int CANVAS_WIDTH = 916;
    public static final int CANVAS_HEIGHT = 840;
    private final int cellSize = 40;
    private final Board board1;
    private final Board board2;
    private final TwoBoardUpdater bu;

    public TetrisJPanel2P()
    {
        bu = new TwoBoardUpdater();
        final int BOARD_TOP = 0;
        final int BOARD_LEFT = 0;
        final int BOARD_WIDTH = 10;
        final int BOARD_HEIGHT = 20;
        final int BOARD2_TOP = 0;
        final int BOARD2_LEFT = 500;
        final int BOARD2_WIDTH = 10;
        final int BOARD2_HEIGHT = 20;
        board1 = new Board(BOARD_WIDTH, BOARD_HEIGHT, BOARD_LEFT, BOARD_TOP);
        board2 = new Board(BOARD2_WIDTH, BOARD2_HEIGHT, BOARD2_LEFT, BOARD2_TOP);
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
                    board2.rotateRight = true;
                    break;
                case KeyEvent.VK_UP:
                    board2.rotateLeft = true;
                    break;
                case KeyEvent.VK_LEFT:
                    board2.moveLeft = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    board2.moveRight = true;
                    break;
                case KeyEvent.VK_DOWN:
                    board2.fallFast = true;
                    break;
                case KeyEvent.VK_W:
                    board1.rotateRight = true;
                    break;
                case KeyEvent.VK_SHIFT:
                    board1.rotateLeft = true;
                    break;
                case KeyEvent.VK_A:
                    board1.moveLeft = true;
                    break;
                case KeyEvent.VK_D:
                    board1.moveRight = true;
                    break;
                case KeyEvent.VK_S:
                    board1.fallFast = true;
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
                    board2.moveLeft = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    board2.moveRight = false;
                    break;
                case KeyEvent.VK_DOWN:
                    board2.fallFast = false;
                    board2.ignoreFallFast = false;
                    break;
                case KeyEvent.VK_A:
                    board1.moveLeft = false;
                    break;
                case KeyEvent.VK_D:
                    board1.moveRight = false;
                    break;
                case KeyEvent.VK_S:
                    board1.fallFast = false;
                    board1.ignoreFallFast = false;
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
        board1.paint(g2d, cellSize);
        board2.paint(g2d, cellSize);
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
        run(game);
    }
    public static void run(TetrisJPanel2P game) throws InterruptedException {
        byte loserData;

        mainLoop:
        while (true)
        {
            loserData = game.bu.update(game.board1, game.board2);
            switch (loserData)
            {
                case 0:
                    break;
                default:
                    break mainLoop;
            }
            game.repaint();
        }
        game.repaint();
        boolean transp = false;
        {
            for (int i = 0; i < 6; ++i)
            {
                TimeUnit.MILLISECONDS.sleep(500);
                switch (loserData)
                {
                    case 1:
                        game.board1.currentTetromino.setTransparent(transp ^=
                                true);
                        break;
                    case 3:
                        game.board1.currentTetromino.setTransparent(transp ^=
                                true);
                        game.board2.currentTetromino.setTransparent(transp);
                        break;
                    case 2:
                        game.board2.currentTetromino.setTransparent(transp ^=
                                true);
                        break;
                    case 0:
                        System.out.println("Error");
                        break;
                }
                game.repaint();
            }
        }
        System.out.println("Done");
    }
}
