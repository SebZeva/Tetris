package MainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Board;
import model.Tetromino;

@SuppressWarnings("serial")
public class TetrisJPanel
        extends JPanel
{

    final int BOARD_TOP = 100;
    final int BOARD_LEFT = 600;
    final int CANVAS_WIDTH = 1600;
    final int CANVAS_HEIGHT = 900;
    final int BOARD_WIDTH = 10;
    final int BOARD_HEIGHT = 20;
    final int CELL_SIZE = 40;
    public static final String TITLE = "Tetris";
    private Tetromino currentTetromino = new Tetromino();
    final Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT);

    private int update(long nanos)
    {
        if (nanos > 100000000)
        {
            if (!currentTetromino.fall(board))
            {
                board.fuse(currentTetromino);
                currentTetromino = new Tetromino();
                if (currentTetromino.collides(board))
                {
                    return 2;
                }
            }
            return 1;
        }
        return 0;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        board.paint(g2d, BOARD_LEFT, BOARD_TOP, CELL_SIZE);
        g2d.setColor(currentTetromino.getCell().getColour());
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
        mainLoop:
        while (true)
        {
            currTime = System.nanoTime();
            switch (game.update(currTime - nanos))
            {
                case 1:
                   nanos = currTime;
                   break;
                case 2:
                    break mainLoop;
                default:
                    break;
            }
            game.repaint();
            Thread.sleep(10);
        }
        System.out.println("Done");
    }
}
