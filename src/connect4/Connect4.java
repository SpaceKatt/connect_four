/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.util.ArrayList;
//import java.util.Scanner;

/**
 *
 * @author Jash
 */

public class Connect4 {

    /**
     * @param args the command line arguments
     */
    
    public int[][] board = new int[6][7];
    public int[] chipsInX = new int[7];
    public boolean gameWinRed;
    public boolean gameWinBlack;
    public boolean tieGame;
    public boolean isRedTurn;
    //private Scanner in;
    
    Connect4() {
        resetBoard();
        gameWinRed = false;
        gameWinBlack = false;
        tieGame = false;
        startGame();
    }
    
    public void startGame() {
        //in = new Scanner(System.in);
        isRedTurn = true;
//        while (!gameWinRed && !gameWinBlack & !tieGame) {
////            if (isRedTurn) {
////                redTurn();
////            } else {
////                blackTurn();
////            }
//            if(gameWinRed) {
//                System.out.println("Red player wins!");
//            }
//            else if(gameWinBlack) {
//                System.out.println("Black player wins!");
//            }
//            else if(tieGame) {
//                System.out.println("Tie game!");
//            }
//        }
    }
    
    public void redTurn(int xRed) {
//        System.out.println("Black player's turn. Enter a coloumn: ");
//        int xRed = in.nextInt();
        int[] chipsInX1 = getChipsInX();
        int[] moveBlack = {(5-chipsInX1[xRed]), xRed};
        placeChip(1, moveBlack);
        isRedTurn = false;
    }
    
    public void blackTurn(int xBlack) {
//        System.out.println("Black player's turn. Enter a coloumn: ");
//        int xBlack = in.nextInt();
        int[] chipsInX2 = getChipsInX();
        int[] moveBlack = {(5-chipsInX2[xBlack]), xBlack};
        placeChip(2, moveBlack);
        isRedTurn = true;
    }
       
    public void resetBoard() {
        for (int i = 0; i < this.chipsInX.length; i++) {
            chipsInX[i] = 0;
        } 
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                this.board[i][j] = 0;
            }
        }
    }

    public int[] getChipsInX() {
        return this.chipsInX;
    }
    
    public void placeChip(int color, int[] coord) {
        this.board[coord[0]][coord[1]] = color;
        chipsInX[coord[1]] += 1; 
        boolean win = checkWin(coord);
        System.out.println(win);
        if (win && color == 1) {
            gameWinRed = true;
        }
        else if (win && color == 2) {
            gameWinBlack = true;  
        }
        else if (checkTie()){
            tieGame = true;
        }
        
    }

    private boolean checkWin(int[] coord) {
        int[] column = grabColumn(this.board, coord[1]);
        int[] forwardDiagonal = grabForwardDiagonal(coord);
        int[] backwardDiagonal = grabBackDiagonal(coord);
        
        boolean one = validSequence(this.board[coord[0]]);
        System.out.print("two");
        boolean two = validSequence(column);
        System.out.print("three");
        boolean three = validSequence(forwardDiagonal);
        System.out.print("four\n");
        boolean four = validSequence(backwardDiagonal);
        
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                System.out.print(this.board[i][j]);
            }
            System.out.println();
        }
        boolean result = (one || two || three || four);
        return result;
    }
    
    private static int[] grabColumn(int[][] x, int i) {
        int[] ray = new int[x.length];
        for (int j = 0; j < ray.length; j++) {
            ray[j] = x[j][i];
        }
        return ray;
    }

    private boolean validSequence(int[] seq) {
        System.out.print("DO: ");
        for (int e : seq) {
            System.out.print(e);
        }
        int color = seq[0];
        int counter = 1;
        boolean result = true;
        for (int i = 1; i < seq.length; i++) {
            if (seq[i] == 0 ) {
            } else if (seq[i] == color) {
                counter++;
            } else {
                color = seq[i];
                counter = 1;
            }
            if (counter == 4) {
                return result;
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
        while (coord[1] >= this.board[0].length) {
            coord[0]++;
            coord[1]--;
        }
//        System.out.print(coord[0] + " yup " + coord[1]);
        ArrayList<Integer> diagons = new ArrayList<>();
        int index = 0;
        while((coord[0] < this.board.length)
              && (this.board[0].length > coord[1] && coord[1] > (-1))) {
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
    private boolean checkTie() {
        boolean tie = true;
        for(int i = 0; i < chipsInX.length; i ++) {
            if (chipsInX[i] != 6) {
                tie = false;   
            }
        }
        return tie;
    }
}    