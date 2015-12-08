/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
/**
 *
 * @author Thomas
 */
class ConnectFourPanel extends JPanel{
    
    private final Connect4 game;
    private int columnIndex;
    private final KeyListener listener;
    private final JPanel top;
    private final JPanel bottom;
    private final JPanel center;
    
    ConnectFourPanel() {
        setSize(1000, 800);
        setLayout(new BorderLayout());
        
        game = new Connect4();
        listener = new KeybListener();
        top = createTopPanel();
        center = createCenterPanel();
        bottom = createBottomPanel();
        
        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        this.addKeyListener(listener);
        this.setFocusable(true);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(1000, 100));
        return topPanel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(1000, 700));
        return centerPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(1000, 40));
        
        JButton buttonReset = createResetButton();
        JButton buttonExit = createExitButton();
        bottomPanel.setLayout(new GridLayout(0,2));
        bottomPanel.add(buttonReset);
        bottomPanel.add(buttonExit);
        
        return bottomPanel;
    }

    private JButton createResetButton() {
        JButton reset = new JButton("Reset Game");
        reset.addActionListener(new ResetListener());
        return reset;
    }

    private JButton createExitButton() {
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ExitListener());
        return exit;
    }
    
    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.resetBoard();
        }   
    }
    
    class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }   
    }
    
    class KeybListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent event) {
            String key = KeyStroke.getKeyStrokeForEvent(event).toString();
            key = key.replace("pressed ", "");
            if (key.equals("LEFT")) {
                columnIndex--;
                center.setBackground(Color.RED);
                while (game.chipsInX[columnIndex] > 5) {
                    columnIndex--;
                }
            } else if (key.equals("RIGHT")) {
                columnIndex++;
                center.setBackground(Color.BLUE);
                while (game.chipsInX[columnIndex] > 5) {
                    columnIndex++;
                }
            } else if (key.equals("SPACE")) {
                if (game.isRedTurn == true) {
                    game.redTurn(columnIndex);
                } else {
                    game.blackTurn(columnIndex);
                }
            }
            if (columnIndex < 0) {
                columnIndex = 6;
            }
            columnIndex = columnIndex % game.board[0].length;
            System.out.println(columnIndex);
            if (!(game.gameWinRed || game.gameWinBlack || game.tieGame)) {
                while (game.chipsInX[columnIndex] > 5) {
                    columnIndex++;
                    columnIndex = columnIndex % game.chipsInX.length;
                }
            } else {
                gameOver();    
            }
        }
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyReleased(KeyEvent e) {}

        private void gameOver() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
