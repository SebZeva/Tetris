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
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OnePlayer extends JPanel {

    public OnePlayer(CardLayout cl, JPanel cards) {
        JLabel label1 = new JLabel("1 Player"); 
        this.add(label1);
    }
}
