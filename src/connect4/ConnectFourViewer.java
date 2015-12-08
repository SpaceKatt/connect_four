/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import javax.swing.JFrame;

/**
 *
 * @author Thomas
 */
public class ConnectFourViewer {
   /**      Creates and displays the application frame.   */   
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        ConnectFourPanel cp = new ConnectFourPanel();
        frame.add(cp);
        frame.pack();
        frame.setVisible(true);
    }
}