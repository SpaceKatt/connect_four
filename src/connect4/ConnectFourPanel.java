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
    private boolean gameOver;
    private ChipComponent chip;
    int currentTurn;
    private GridComponent grid;
    
    ConnectFourPanel() {
        setSize(1000, 700);
        setLayout(new BorderLayout());
        
        game = new Connect4();
        listener = new KeybListener();
        top = createTopPanel();
        center = createCenterPanel();
        bottom = createBottomPanel();
        gameOver = false;
        
        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        this.addKeyListener(listener);
        this.setFocusable(true);
        repaint();
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(700, 120));
        this.chip = new ChipComponent(1, 0, 0, 100);
        topPanel.setLayout(null);
        this.chip.setBounds(100*columnIndex, 0, chip.size, chip.size);
        topPanel.add(this.chip);
        topPanel.repaint();
        return topPanel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(700, 700));
        this.grid = new GridComponent(0, 0, 0, 700);
        centerPanel.setLayout(null);
        this.grid.setBounds(0, 0, grid.size, grid.size - 100);
        centerPanel.add(this.grid);
        for (int i = 0; i < game.board.length; i++) {
            for (int j = 0; j < game.board[0].length; j++) {
                ChipComponent chipper = 
                        new ChipComponent(game.board[i][j], i*100, j*100, 100);
                chipper.setBounds(100*i, 100*j, chipper.size, chipper.size);
                centerPanel.add(chipper);
                centerPanel.repaint();
            }
        }
        centerPanel.repaint();
        return centerPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(700, 40));
        
        JButton buttonReset = createResetButton();
        JButton buttonExit = createExitButton();
        bottomPanel.setLayout(new GridLayout(0,2));
        bottomPanel.add(buttonReset);
        bottomPanel.add(buttonExit);
        
        bottomPanel.setFocusable(false);
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
            gameOver = false;
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
                if (columnIndex < 0) {
                    columnIndex = 6;
                }
                center.setBackground(Color.RED);
                while (game.chipsInX[columnIndex] > 5) {
                    columnIndex--;
                    if (columnIndex < 0) {
                        columnIndex = 6;
                    }
                }
            } else if (key.equals("RIGHT")) {
                columnIndex++;
                columnIndex = columnIndex % game.board[0].length;
                center.setBackground(Color.BLUE);
                while (game.chipsInX[columnIndex] > 5) {
                    columnIndex++;
                    columnIndex = columnIndex % (game.board[0].length - 1);
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
                gameOverM();    
            }
            int chipPos = columnIndex;
            chip.setBounds(100*chipPos, 0, chip.size, chip.size);
            if (game.isRedTurn == true) {
                currentTurn = 1;
            } else {
                currentTurn = 2;
            }
            chip.color = currentTurn;
            repaint();
        }
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyReleased(KeyEvent e) {}

        private void gameOverM() {
            gameOver = true;
        }
    }
}
