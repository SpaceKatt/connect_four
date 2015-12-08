/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.util.ArrayList;

/**
 *
 * @author Jash
 */

public class Connect4 {

    /**
     * @param args the command line arguments
     */
    
    public int[][] board = new int[6][7];
    
    Connect4() {
        resetBoard();
    }
    
    public void resetBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                this.board[i][j] = 0;
            }
        }
    }

    public void placeChip(int color, int[] coord) {
        this.board[coord[0]][coord[1]] = color;
        boolean win = checkWin(coord);
        System.out.println(win);
        
    }

    private boolean checkWin(int[] coord) {
        int[] column = grabColumn(this.board, coord[1]);
        int[] forwardDiagonal = grabForwardDiagonal(coord);
        int[] backwardDiagonal = grabBackDiagonal(coord);
        
        boolean one = validSequence(this.board[coord[0]]);
//        System.out.print("two");
        boolean two = validSequence(column);
//        System.out.print("three");
        boolean three = validSequence(forwardDiagonal);
//        System.out.print("four\n");
        boolean four = validSequence(backwardDiagonal);
        
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                System.out.print(this.board[i][j]);
            }
            System.out.println();
        }
        
        return (one || two || three || four);
    }
    
    private static int[] grabColumn(int[][] x, int i) {
        int[] ray = new int[x.length];
        for (int j = 0; j < ray.length; j++) {
            ray[j] = x[j][i];
        }
        return ray;
    }

    private boolean validSequence(int[] seq) {
        int color = seq[0];
        int counter = 1;
        for (int i = 1; i < seq.length; i++) {
            if (color == 0 ) {
                counter = 1;
            } else if (seq[i] == color) {
                counter++;
            } else {
                color = seq[i];
                counter = 1;
            }
            if (counter == 4) {
                return true;
            }
        }
        return false;
    }

    private int[] grabForwardDiagonal(int[] coords) {
        int[] coord = {coords[0], coords[1]};
        coord[1] -= coord[0];
        coord[0] -= coord[0];
        while (coord[1] < 0) {
            coord[0]++;
            coord[1]++;
        }
        
        ArrayList<Integer> diagons = new ArrayList<>();
        int index = 0;
        while(coord[0] < this.board.length 
              && coord[1] < this.board[0].length) {
            diagons.add(index, this.board[coord[0]][coord[1]]);
            coord[0]++;
            coord[1]++;
            index++;
        }
        
        int[] diagonalStuff = new int[diagons.size()];
        for (int i = 0; i < diagonalStuff.length; i++) {
            diagonalStuff[i] = diagons.get(i);    
        }
        return diagonalStuff;
    }

    private int[] grabBackDiagonal(int[] coords) {
        int[] coord = {coords[0], coords[1]};
        coord[1] += coord[0];
        coord[0] -= coord[0];
        while (coord[1] > this.board[0].length) {
            coord[0]--;
            coord[1]++;
        }
//        System.out.print(coord[0] + " yup " + coord[1]);
        ArrayList<Integer> diagons = new ArrayList<>();
        int index = 0;
        while(coord[0] < this.board.length 
              && coord[1] > (-1)) {
            diagons.add(index, this.board[coord[0]][coord[1]]);
            coord[0]++;
            coord[1]--;
            index++;
        }
        
        int[] diagonalStuff = new int[diagons.size()];
        for (int i = 0; i < diagonalStuff.length; i++) {
            diagonalStuff[i] = diagons.get(i);    
        }
        return diagonalStuff;
    }
}    

