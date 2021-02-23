/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layoutak.hainbatPantaila;

import MainGame.TetrisJPanel;
import TwoPlayers.TetrisJPanel2P;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Nagusia {

    public static final int CANVAS_WIDTH = 916;
    public static final int CANVAS_HEIGHT = 840;
    /** Hainbat pantaila. CardLayout. Hainbat fitxategitan.
     * 
     */
    public static void createAndShowGUI() {

        final String TITLE = "Tetris";
        JFrame nagusia = new JFrame(TITLE);
        CardLayout laiauta = new CardLayout();
        JPanel kartulinak = new JPanel(laiauta);

        kartulinak.add(new Menu(laiauta, kartulinak), "m");
        kartulinak.add(new TetrisJPanel(), "o");
        kartulinak.add(new TetrisJPanel2P(), "t");

        nagusia.add(kartulinak);

        //Display the window.
        nagusia.pack();
        nagusia.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        nagusia.setVisible(true);
        nagusia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

    }

    public static void main(String[] args) {

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }
}
