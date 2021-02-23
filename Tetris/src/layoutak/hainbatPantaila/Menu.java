/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layoutak.hainbatPantaila;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author IMadariaga
 */
public class Menu extends JPanel {

    public Menu(CardLayout cl, JPanel cards) {
        JButton botoi1 = new JButton("One Player");
        botoi1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(cards, "o");
                
            }
        });
        this.add(botoi1);

        JButton botoi2 = new JButton("Two Players");
        botoi2.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(cards, "t");
            }
        });
        this.add(botoi2);
    }
}
